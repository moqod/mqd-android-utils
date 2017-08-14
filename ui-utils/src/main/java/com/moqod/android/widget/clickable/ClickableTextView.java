package com.moqod.android.widget.clickable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


/**
 * Created by zenkefer(zenkefer@gmail.com) on 06.05.2015
 */

public class ClickableTextView extends AppCompatTextView {

    private StateParams mParams;

    public ClickableTextView(Context context) {
        super(context);
        init(context, null);
    }

    public ClickableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ClickableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mParams = StateParams.parseAttrs(context, attrs);
        setBackgroundDrawable(StateDrawable.create(getBackground(), mParams));
        setTextColor(StateColor.create(getTextColors(), mParams));
    }

    @Override
    public void setBackgroundDrawable(Drawable background) {
        if (mParams != null) {
            super.setBackgroundDrawable(StateDrawable.create(background, mParams));
        } else {
            super.setBackgroundDrawable(background);
        }
    }

    @Override
    public void setTextColor(@ColorInt int color) {
        if (mParams != null) {
            super.setTextColor(StateColor.create(color, mParams));
        } else {
            super.setTextColor(color);
        }
    }

    @Override
    public void setTextColor(ColorStateList colors) {
        if (mParams != null) {
            super.setTextColor(StateColor.create(colors, mParams));
        } else {
            super.setTextColor(colors);
        }
    }

}
