package com.moqod.android.base.data.query;

import android.text.TextUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 12/12/2016
 * Time: 14:56
 */

public class PageCursor<T> {

    private T mResult;
    private String mCursor;

    public PageCursor(T result, String cursor) {
        mResult = result;
        mCursor = cursor;
    }

    public T getResult() {
        return mResult;
    }

    public String getCursor() {
        return mCursor;
    }

    public boolean isFull() {
        return TextUtils.isEmpty(mCursor);
    }
}
