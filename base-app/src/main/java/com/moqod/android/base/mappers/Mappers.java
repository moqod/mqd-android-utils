package com.moqod.android.base.mappers;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 02/03/2017
 * Time: 11:57
 */

public final class Mappers {

    private Mappers() {
    }

    public static <F, T> List<T> mapCollection(@Nullable List<F> list, Mapper<F, T> mapper) {
        if (list == null) {
            return Collections.emptyList();
        } else {
            int size = list.size();
            ArrayList<T> result = new ArrayList<>(size);

            T entity;
            for (int i = 0; i < size; ++i) {
                entity = mapper.map(list.get(i));
                if (entity != null) {
                    result.add(entity);
                }
            }

            return result;
        }
    }

    public static <F, T> List<F> reverseMapCollection(@Nullable List<T> list, BiMapper<F, T> mapper) {
        if (list == null) {
            return Collections.emptyList();
        } else {
            int size = list.size();
            ArrayList<F> result = new ArrayList<>(size);

            F entity;
            for (int i = 0; i < size; ++i) {
                entity = mapper.reverseMap(list.get(i));
                if (entity != null) {
                    result.add(entity);
                }
            }

            return result;
        }
    }

    public static <F, T> List<T> mapCollection(@Nullable Iterable<F> iterable, Mapper<F, T> mapper) {
        if (iterable == null) {
            return Collections.emptyList();
        } else {
            ArrayList<T> result = new ArrayList<>();

            T entity;
            for (F f : iterable) {
                entity = mapper.map(f);
                if (entity != null) {
                    result.add(entity);
                }
            }
            return result;
        }
    }

    public static <F, T> List<F> reverseMapCollection(@Nullable Iterable<T> iterable, BiMapper<F, T> mapper) {
        if (iterable == null) {
            return Collections.emptyList();
        } else {
            ArrayList<F> result = new ArrayList<>();

            F entity;
            for (T t : iterable) {
                entity = mapper.reverseMap(t);
                if (entity != null) {
                    result.add(entity);
                }
            }
            return result;
        }
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        int size = list.size();
        ArrayList<T> result = new ArrayList<>(size);

        T entity;
        for (int i = 0; i < size; ++i) {
            entity = list.get(i);
            if (predicate.filter(entity)) {
                result.add(entity);
            }
        }

        return result;
    }

    public static <T> String join(List<T> list, String divider, ToStringFunction<T> func) {
        int size = list.size();
        StringBuilder builder = new StringBuilder();

        T entity;
        for (int i = 0; i < size; ++i) {
            entity = list.get(i);
            if (builder.length() > 0) {
                builder.append(divider);
            }
            builder.append(func.apply(entity));
        }

        return builder.toString();
    }

}
