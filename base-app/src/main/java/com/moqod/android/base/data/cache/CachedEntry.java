package com.moqod.android.base.data.cache;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 26/11/2016
 * Time: 19:08
 */

public class CachedEntry<E> {

    private E mEntry;
    private long mCreateTime;

    public CachedEntry(E entry, long createTime) {
        mEntry = entry;
        mCreateTime = createTime;
    }

    public E getEntry() {
        return mEntry;
    }

    long getCreateTime() {
        return mCreateTime;
    }
}
