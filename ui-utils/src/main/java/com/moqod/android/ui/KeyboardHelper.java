package com.moqod.android.ui;

import android.content.Context;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KeyboardHelper {

	public static void hideKeyboard(Context context, IBinder view) {
		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(view, 0);
	}
	
	public static void showKeyboard(Context context, View view) {
		InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, 0);
	}

    public static void showKeyboard(Context context, IBinder view) {
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInputFromInputMethod(view, 0);
    }

    public static void hideKeyboard(Fragment fragment) {
        View view = fragment.getView();
        if (view != null) {
            hideKeyboard(fragment.getContext(), view.getWindowToken());
        }
    }
}
