package com.moqod.android.rxutils;

import rx.functions.Action1;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 05/10/2016
 * Time: 16:00
 */
public final class EmptyActions {

    public static <T> Action1<T> action1() {
        return new Action1<T>() {
            @Override
            public void call(T t) {

            }
        };
    }

}
