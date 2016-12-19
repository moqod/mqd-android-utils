package com.moqod.android.base.contents;

import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 06/12/2016
 * Time: 15:39
 */

public class ContentSubscriber<T> extends AtomicReference<Subscription> implements Subscriber<T>, Disposable {

    public static <T> ContentSubscriber<T> create(ContentView<T> contentView, ErrorHandler errorHandler) {
        return new ContentSubscriber<>(contentView, errorHandler, true);
    }

    public static <T> ContentSubscriber<T> create(ContentView<T> contentView, ErrorHandler errorHandler, boolean showLoading) {
        return new ContentSubscriber<>(contentView, errorHandler, showLoading);
    }

    private ContentView<T> mContentView;
    private ErrorHandler mErrorHandler;
    private boolean mEmpty = true;
    private boolean mShowLoading;

    private ContentSubscriber(ContentView<T> contentView, ErrorHandler errorHandler, boolean showLoading) {
        mContentView = contentView;
        mErrorHandler = errorHandler;
        mShowLoading = showLoading;
    }

    @Override
    public void onSubscribe(Subscription s) {
        if (SubscriptionHelper.setOnce(this, s)) {
            try {
                s.request(Integer.MAX_VALUE);
                if (mShowLoading) {
                    mContentView.showLoading();
                }
            } catch (Exception e) {
                Exceptions.throwIfFatal(e);
                s.cancel();
                onError(e);
            }
        }
    }

    @Override
    public void onNext(T t) {
        if (!isDisposed()) {
            mEmpty = false;
            try {
                mContentView.showContent(t);
            } catch (Exception e) {
                Exceptions.throwIfFatal(e);
                get().cancel();
                onError(e);
            }
        }
    }

    @Override
    public void onError(Throwable t) {
        if (get() != SubscriptionHelper.CANCELLED) {
            lazySet(SubscriptionHelper.CANCELLED);
            try {
                mErrorHandler.handleError(t, mContentView);
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                RxJavaPlugins.onError(new CompositeException(t, e));
            }
        } else {
            RxJavaPlugins.onError(t);
        }
    }

    @Override
    public void onComplete() {
        if (get() != SubscriptionHelper.CANCELLED) {
            lazySet(SubscriptionHelper.CANCELLED);
            try {
                if (mEmpty) {
                    mContentView.showEmpty();
                }
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                RxJavaPlugins.onError(e);
            }
        }
    }

    @Override
    public void dispose() {
        SubscriptionHelper.cancel(this);
    }

    @Override
    public boolean isDisposed() {
        return get() == SubscriptionHelper.CANCELLED;
    }
}
