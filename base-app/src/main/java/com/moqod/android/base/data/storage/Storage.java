package com.moqod.android.base.data.storage;

import com.moqod.android.base.data.cache.CachePolicy;
import com.moqod.android.base.data.query.Query;
import io.reactivex.Maybe;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 26/11/2016
 * Time: 19:57
 */

public interface Storage<Q extends Query, R> {

    Maybe<R> get(Q query, CachePolicy cachePolicy);
    void put(Q query, R r);
    void remove(Q query);

}
