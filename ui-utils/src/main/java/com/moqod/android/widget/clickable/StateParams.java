package com.moqod.android.widget.clickable;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.moqod.android.ui.R;


/**
 * Created by zenkefer(zenkefer@gmail.com) on 19.07.2017
 */

class StateParams {

    static StateParams parseAttrs(Context context, AttributeSet attrs) {
        int pressedFilterColor = 0x40000000;
        int disabledAlpha = 128;
        boolean useRipple = true;

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClickableWidget);
        if (typedArray != null) {
            pressedFilterColor = typedArray.getColor(R.styleable.ClickableWidget_cwPressedFilter, pressedFilterColor);
            disabledAlpha = typedArray.getInt(R.styleable.ClickableWidget_cwDisabledAlpha, disabledAlpha);
            useRipple = typedArray.getBoolean(R.styleable.ClickableWidget_cwUseRipple, useRipple);
            typedArray.recycle();
        }

        return new StateParams(pressedFilterColor, disabledAlpha, useRipple);
    }

    private int mPressedFilter;
    private int mDisabledAlpha;
    private boolean mUseRipple;

    private StateParams(int pressedFilter, int disabledAlpha, boolean useRipple) {
        mPressedFilter = pressedFilter;
        mDisabledAlpha = disabledAlpha;
        mUseRipple = useRipple;
    }

    int getPressedFilter() {
        return mPressedFilter;
    }

    int getDisabledAlpha() {
        return mDisabledAlpha;
    }

    boolean isUseRipple() {
        return mUseRipple;
    }
}
