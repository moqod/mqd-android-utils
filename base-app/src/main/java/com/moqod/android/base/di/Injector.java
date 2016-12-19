package com.moqod.android.base.di;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 23/11/2016
 * Time: 21:13
 */

public class Injector {
    @SuppressWarnings("unchecked")
    public static <C> C getComponent(Object o, Class<C> component) {
        return component.cast(((HasComponent)o).getComponent());
    }
}
