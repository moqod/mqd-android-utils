package com.moqod.android.mvp.base.storages;


import rx.Observable;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 23/09/16
 * Time: 13:58
 */

public interface Storage<K, V> {

    Observable<V> get(K k);
    Observable<V> put(K k, V v);
    void putBlocking(K k, V v);
    Observable<Void> clear();

}
