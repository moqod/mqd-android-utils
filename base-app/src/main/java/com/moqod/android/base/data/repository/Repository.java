package com.moqod.android.base.data.repository;

import android.support.annotation.Nullable;
import com.moqod.android.base.data.query.Query;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 26/11/2016
 * Time: 16:01
 */

public interface Repository<Q extends Query, R> {

    Flowable<R> get(Q query);
    Flowable<R> get(Q query, @Nullable Refresh refresh);
    Maybe<R> cache(Q query);

}
