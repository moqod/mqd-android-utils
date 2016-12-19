package com.moqod.android.base.mappers;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 01/12/2016
 * Time: 14:20
 */

public final class Mappers {

    public static <F, T> List<T> mapCollection(@Nullable List<F> list, Mapper<F, T> mapper) {
        if (list == null) {
            return Collections.emptyList();
        }
        int size = list.size();
        ArrayList<T> result = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            result.add(mapper.map(list.get(i)));
        }
        return result;
    }

}
