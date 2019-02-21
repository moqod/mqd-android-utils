package com.moqod.android.rxutils;

import rx.Emitter;
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
        return Observable.create(new Action1<Emitter<T>>() {
            @Override
            public void call(Emitter<T> asyncEmitter) {
                if (mRef != null) {
                    T t = mRef.get();
                    if (t != null) {
                        asyncEmitter.onNext(t);
                    }
                }
                asyncEmitter.onCompleted();
            }
        }, Emitter.BackpressureMode.LATEST);
    }

    public void put(T t) {
        mRef = new WeakReference<>(t);
    }

}
