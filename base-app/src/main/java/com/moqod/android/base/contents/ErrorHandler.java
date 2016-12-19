package com.moqod.android.base.contents;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 30/11/2016
 * Time: 19:05
 */

public interface ErrorHandler {
    void handleError(Throwable throwable);
    void handleError(Throwable throwable, ErrorView view);
}
