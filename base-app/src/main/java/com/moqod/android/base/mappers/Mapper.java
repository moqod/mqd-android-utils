package com.moqod.android.base.mappers;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 13/09/2017
 * Time: 14:04
 */

public interface Mapper<F, T> {
    T map(F var1);
}