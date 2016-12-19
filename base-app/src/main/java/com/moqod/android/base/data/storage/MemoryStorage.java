package com.moqod.android.base.data.storage;

import com.moqod.android.base.data.cache.CachePolicy;
import com.moqod.android.base.data.cache.CachedEntry;
import com.moqod.android.base.data.query.Query;
import com.moqod.android.base.data.utils.RxLruCache;
import io.reactivex.Maybe;
import io.reactivex.functions.Function;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 26/11/2016
 * Time: 19:57
 */

public class MemoryStorage<Q extends Query, R> implements Storage<Q, R> {

    private RxLruCache<String, CachedEntry<R>> mCache;

    public MemoryStorage(int maxSize) {
        mCache = new RxLruCache<>(maxSize);
    }

    @Override
    public Maybe<R> get(Q query, CachePolicy cachePolicy) {
        return mCache.get(query.getKey())
                .filter(cachePolicy)
                .map(new Function<CachedEntry<R>, R>() {
                    @Override
                    public R apply(CachedEntry<R> rCachedEntry) throws Exception {
                        return rCachedEntry.getEntry();
                    }
                });
    }

    @Override
    public void put(Q query, R r) {
        mCache.put(query.getKey(), CachePolicy.createEntry(r));
    }

    @Override
    public void remove(Q query) {
        mCache.remove(query.getKey());
    }
}
