package com.moqod.android.rxutils;

import rx.Observer;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 25/04/16
 * Time: 19:59
 */
public class ObserverAdapter<T> implements Observer<T> {

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
    }

    @Override
    public void onNext(T t) {
    }

    private static final ObserverAdapter<Object> EMPTY = new ObserverAdapter<>();

    @SuppressWarnings("unchecked")
    public static <R> ObserverAdapter<R> empty() {
        return (ObserverAdapter<R>) EMPTY;
    }

}
