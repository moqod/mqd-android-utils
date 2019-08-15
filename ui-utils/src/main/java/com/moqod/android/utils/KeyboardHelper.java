package com.moqod.android.utils;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.fragment.app.Fragment;

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
            hideKeyboard(fragment.requireContext(), view.getWindowToken());
        }
    }
}
