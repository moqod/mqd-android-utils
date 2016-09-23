package com.moqod.android.mvp.base.storages;

import io.paperdb.Book;
import rx.AsyncEmitter;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 23/09/16
 * Time: 14:09
 */

public class PaperStorage<K, V> implements Storage<K, V> {

    private Book mPaperBook;
    private AsyncEmitter.BackpressureMode mBackpressureMode = AsyncEmitter.BackpressureMode.LATEST;

    public PaperStorage(Book paperBook) {
        mPaperBook = paperBook;
    }

    public void setBackpressureMode(AsyncEmitter.BackpressureMode backpressureMode) {
        mBackpressureMode = backpressureMode;
    }

    @Override
    public Observable<V> get(final K k) {
        return Observable.fromEmitter(new Action1<AsyncEmitter<V>>() {
            @Override
            public void call(AsyncEmitter<V> asyncEmitter) {
                try {
                    V value = mPaperBook.read(k.toString());
                    if (value != null) {
                        asyncEmitter.onNext(value);
                    }
                    asyncEmitter.onCompleted();
                } catch (Exception e) {
                    asyncEmitter.onError(e);
                }
            }
        }, mBackpressureMode);
    }

    @Override
    public Observable<V> put(final K k, final V v) {
        return Observable.fromEmitter(new Action1<AsyncEmitter<V>>() {
            @Override
            public void call(AsyncEmitter<V> asyncEmitter) {
                try {
                    putBlocking(k, v);
                    V value = mPaperBook.read(k.toString());
                    asyncEmitter.onNext(value);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                asyncEmitter.onCompleted();
            }
        }, mBackpressureMode);
    }

    @Override
    public void putBlocking(K k, V v) {
        mPaperBook.write(k.toString(), v);
    }

    @Override
    public Observable<Void> clear() {
        return Observable.fromEmitter(new Action1<AsyncEmitter<Void>>() {
            @Override
            public void call(AsyncEmitter<Void> asyncEmitter) {
                mPaperBook.destroy();
                asyncEmitter.onCompleted();
            }
        }, mBackpressureMode);
    }
}
