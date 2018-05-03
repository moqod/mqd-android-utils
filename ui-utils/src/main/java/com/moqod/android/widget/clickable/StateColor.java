package com.moqod.android.widget.clickable;

import android.content.res.ColorStateList;
import android.support.v4.graphics.ColorUtils;

/**
 * Created by zenkefer(zenkefer@gmail.com) on 24.10.2015
 */

class StateColor {

    static ColorStateList create(ColorStateList colors, StateParams params) {
        return colors.isStateful() ? colors : create(colors.getDefaultColor(), params);
    }

    static ColorStateList create(int normalColor, StateParams params) {
        int pressedColor = ColorUtils.compositeColors(params.getPressedFilter(), normalColor);
        int disabledColor = ColorUtils.setAlphaComponent(normalColor, params.getDisabledAlpha());

        int[][] states = new int[][]{
                new int[]{android.R.attr.state_pressed},
                new int[]{android.R.attr.state_enabled},
                new int[]{}
        };
        int[] colors = new int[]{
                pressedColor,
                normalColor,
                disabledColor
        };
        return new ColorStateList(states, colors);
    }

}
