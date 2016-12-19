package com.moqod.android.base.data.query;

import android.support.annotation.Nullable;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 26/11/2016
 * Time: 16:04
 */

public abstract class CollectionQuery implements Query {

    @Nullable private String mCursor;
    private int mLimit;

    protected CollectionQuery(@Nullable String cursor, int limit) {
        mCursor = cursor;
        mLimit = limit;
    }

    @Nullable
    public String getCursor() {
        return mCursor;
    }

    public int getLimit() {
        return mLimit;
    }
}
