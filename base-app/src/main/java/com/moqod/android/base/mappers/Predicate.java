package com.moqod.android.base.mappers;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 12/01/2018
 * Time: 15:29
 */

public interface Predicate<T> {

    boolean filter(T t);

}
