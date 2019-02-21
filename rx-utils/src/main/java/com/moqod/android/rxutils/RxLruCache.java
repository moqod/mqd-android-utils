package com.moqod.android.rxutils;

import android.util.LruCache;
import rx.Emitter;
import rx.Observable;
import rx.functions.Action1;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 12/08/16
 * Time: 14:05
 */
public class RxLruCache<K,V> {

    private LruCache<K, V> mLruCache;

    public RxLruCache(int maxSize) {
        mLruCache = new LruCache<>(maxSize);
    }

    public Observable<V> get(final K key) {
        return Observable.create(new Action1<Emitter<V>>() {
            @Override
            public void call(Emitter<V> asyncEmitter) {
                V value = mLruCache.get(key);
                if (value != null) {
                    asyncEmitter.onNext(value);
                }
                asyncEmitter.onCompleted();
            }
        }, Emitter.BackpressureMode.LATEST);
    }

    public void put(K key, V value) {
        mLruCache.put(key, value);
    }

    public void remove(K key) {
        mLruCache.remove(key);
    }

    public void clear() {
        mLruCache.evictAll();
    }

}
