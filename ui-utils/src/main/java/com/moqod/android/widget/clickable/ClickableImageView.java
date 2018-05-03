package com.moqod.android.widget.clickable;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * Created by zenkefer(zenkefer@gmail.com) on 06.05.2015
 */

public class ClickableImageView extends AppCompatImageView {

    private StateParams mParams;

    public ClickableImageView(Context context) {
        super(context);
        init(context, null);
    }

    public ClickableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ClickableImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mParams = StateParams.parseAttrs(context, attrs);
        super.setBackgroundDrawable(StateDrawable.create(getBackground(), mParams));
        super.setImageDrawable(StateDrawable.create(getDrawable(), mParams));
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
    public void setImageDrawable(@Nullable Drawable drawable) {
        if (mParams != null) {
            super.setImageDrawable(StateDrawable.create(drawable, mParams));
        } else {
            super.setImageDrawable(drawable);
        }
    }
}
