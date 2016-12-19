package com.moqod.android.base.contents;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 30/11/2016
 * Time: 16:07
 */

public interface ContentView<T> extends ErrorView {
    void showLoading();
    void showContent(T content);
    void showError(String message);
    void showEmpty();
}
