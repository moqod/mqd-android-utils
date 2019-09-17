package com.moqod.android.rxutils;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Maybe;
import io.reactivex.MaybeSource;
import io.reactivex.MaybeTransformer;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.SingleTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import org.reactivestreams.Publisher;

import java.util.HashMap;

/**
 * Created by zenkefer on 13.09.2019
 */

public class RxUtils {

    public static  Action emptyAction() {
        return () -> {};
    }

    public static <T> Consumer<T> emptyConsumer() {
        return t -> {};
    }


    public static <T> ObservableTransformer<T, T> applySchedulersObservable() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> FlowableTransformer<T, T> applySchedulersFlowable() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> SingleTransformer<T, T> applySchedulersSingle() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> MaybeTransformer<T, T> applySchedulersMaybe() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public static CompletableTransformer applySchedulersCompletable() {
        return upstream -> upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @SuppressWarnings("unchecked")
    public static <T> SchedulersTransformer<T> applySchedulers() {
        return (SchedulersTransformer<T>) sSchedulersTransformer;
    }

    private static SchedulersTransformer<Object> sSchedulersTransformer = new SchedulersTransformer<>();

    public static class SchedulersTransformer<T> implements ObservableTransformer<T, T>, FlowableTransformer<T, T>, SingleTransformer<T, T>, MaybeTransformer<T, T>, CompletableTransformer {

        @Override
        public Publisher<T> apply(Flowable<T> upstream) {
            return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }

        @Override
        public ObservableSource<T> apply(Observable<T> upstream) {
            return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }

        @Override
        public SingleSource<T> apply(Single<T> upstream) {
            return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }

        @Override
        public MaybeSource<T> apply(Maybe<T> upstream) {
            return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }

        @Override
        public CompletableSource apply(Completable upstream) {
            return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    }


    private static final HashMap<Object, CompositeDisposable> sDisposables = new HashMap<>();

    public static void manage(Object tag, Disposable disposable) {
        CompositeDisposable disposables = sDisposables.get(tag);
        if (disposables == null) {
            disposables = new CompositeDisposable();
            sDisposables.put(tag, disposables);
        }
        disposables.add(disposable);
    }

    public static void dispose(Object tag) {
        CompositeDisposable disposables = sDisposables.get(tag);
        if (disposables != null) {
            disposables.dispose();
            sDisposables.remove(tag);
        }
    }

}
