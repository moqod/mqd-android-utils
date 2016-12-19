package com.moqod.android.base.mappers;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 01/12/2016
 * Time: 14:19
 */

public interface Mapper<F, T> {
    T map(F from);
}
