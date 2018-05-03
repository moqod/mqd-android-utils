package com.moqod.android.base.mappers;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 02/03/2017
 * Time: 11:56
 */

public interface BiMapper<From, To> extends Mapper<From, To> {
    From reverseMap(To to);
}
