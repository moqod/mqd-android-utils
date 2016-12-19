package com.moqod.android.base.data.utils;

import android.util.LruCache;
import io.reactivex.Maybe;
import io.reactivex.MaybeEmitter;
import io.reactivex.MaybeOnSubscribe;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 26/11/2016
 * Time: 16:17
 */

public class RxLruCache<K, V> {

    private LruCache<K, V> mCache;

    public RxLruCache(int maxSize) {
        mCache = new LruCache<>(maxSize);
    }

    public Maybe<V> get(final K key) {
        return Maybe.create(new MaybeOnSubscribe<V>() {
            @Override
            public void subscribe(MaybeEmitter<V> e) throws Exception {
                V v = mCache.get(key);
                if (v != null) {
                    e.onSuccess(v);
                }
                e.onComplete();
            }
        });
    }

    public void put(K key, V value) {
        mCache.put(key, value);
    }

    public void remove(K key) {
        mCache.remove(key);
    }

    public void clear() {
        mCache.evictAll();
    }

}
