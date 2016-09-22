package com.moqod.android.rxutils;

import rx.AsyncEmitter;
import rx.Observable;
import rx.functions.Action1;

import java.lang.ref.WeakReference;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 11/08/16
 * Time: 17:44
 */
public class RxWeakReference<T> {

    private WeakReference<T> mRef;

    public Observable<T> get() {
        return Observable.fromEmitter(new Action1<AsyncEmitter<T>>() {
            @Override
            public void call(AsyncEmitter<T> asyncEmitter) {
                if (mRef != null) {
                    T t = mRef.get();
                    if (t != null) {
                        asyncEmitter.onNext(t);
                    }
                }
                asyncEmitter.onCompleted();
            }
        }, AsyncEmitter.BackpressureMode.LATEST);
    }

    public void put(T t) {
        mRef = new WeakReference<>(t);
    }

}
