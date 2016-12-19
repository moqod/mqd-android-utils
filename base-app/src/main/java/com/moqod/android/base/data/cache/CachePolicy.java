package com.moqod.android.base.data.cache;

import io.reactivex.functions.Predicate;

import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 26/11/2016
 * Time: 19:09
 */

public class CachePolicy implements Predicate<CachedEntry<?>> {

    public static CachePolicy create(int time, TimeUnit timeUnit) {
        return new CachePolicy(time, timeUnit);
    }

    public static CachePolicy infinite() {
        return new CachePolicy(Integer.MAX_VALUE, TimeUnit.DAYS);
    }

    public static <T> CachedEntry<T> createEntry(T t) {
        return new CachedEntry<>(t, getTime());
    }

    private int mTime;
    private TimeUnit mTimeUnit;

    private CachePolicy(int time, TimeUnit timeUnit) {
        mTime = time;
        mTimeUnit = timeUnit;
    }

    @Override
    public boolean test(CachedEntry<?> entry) throws Exception {
        long currentTime = getTime();
        return currentTime - entry.getCreateTime() < mTimeUnit.toMillis(mTime);
    }

    private static long getTime() {
        return System.currentTimeMillis();
    }
}
