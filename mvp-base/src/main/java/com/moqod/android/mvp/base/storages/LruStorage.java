package com.moqod.android.mvp.base.storages;

import android.util.LruCache;
import rx.AsyncEmitter;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 23/09/16
 * Time: 16:56
 */

public class LruStorage<K, V> implements Storage<K, V> {

    private LruCache<K, V> mLruCache;
    private AsyncEmitter.BackpressureMode mBackpressureMode = AsyncEmitter.BackpressureMode.LATEST;

    public LruStorage(int maxSize) {
        mLruCache = new LruCache<>(maxSize);
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
                    V v = mLruCache.get(k);
                    if (v != null) {
                        asyncEmitter.onNext(v);
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
                    asyncEmitter.onNext(v);
                    asyncEmitter.onCompleted();
                } catch (Exception e) {
                    asyncEmitter.onError(e);
                }
            }
        }, mBackpressureMode);

    }

    @Override
    public void putBlocking(K k, V v) {
        mLruCache.put(k, v);
    }

    @Override
    public Observable<Void> clear() {
        return Observable.fromEmitter(new Action1<AsyncEmitter<Void>>() {
            @Override
            public void call(AsyncEmitter<Void> asyncEmitter) {
                try {
                    mLruCache.evictAll();
                    asyncEmitter.onCompleted();
                } catch (Exception e) {
                    asyncEmitter.onError(e);
                }
            }
        }, mBackpressureMode);
    }
}
