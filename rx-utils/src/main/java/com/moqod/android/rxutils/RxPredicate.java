package com.moqod.android.rxutils;

import rx.functions.Func0;
import rx.functions.Func1;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 14/07/16
 * Time: 17:07
 */
public class RxPredicate {

    public static <T> Func1<T, Boolean> nonNull() {
        return new Func1<T, Boolean>() {
            @Override
            public Boolean call(T o) {
                return o != null ? Boolean.TRUE : Boolean.FALSE;
            }
        };
    }

    public static Func0<Boolean> nonNull(final Object o) {
        return new Func0<Boolean>() {
            @Override
            public Boolean call() {
                return o != null ? Boolean.TRUE : Boolean.FALSE;
            }
        };
    }

    public static <T> Func1<List<T>, Boolean> notEmpty() {
        return new Func1<List<T>, Boolean>() {
            @Override
            public Boolean call(List<T> collection) {
                return !collection.isEmpty();
            }
        };
    }

}
