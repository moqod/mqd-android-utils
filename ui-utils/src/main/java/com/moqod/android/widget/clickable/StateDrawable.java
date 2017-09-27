package com.moqod.android.widget.clickable;

/**
 * Created by zenkefer(zenkefer@gmail.com) on 06.05.2015
 */

import android.content.res.ColorStateList;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.support.v4.graphics.ColorUtils;

class StateDrawable {

    static Drawable create(Drawable drawable, StateParams params) {
        if(drawable == null) return null;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && params.isUseRipple()) {
            Drawable stateDrawable = new DrawableStateLayerDrawable(drawable, params.getDisabledAlpha());
            return new RippleDrawable(ColorStateList.valueOf(params.getPressedFilter()), stateDrawable, null);
        }
        if (drawable instanceof ColorDrawable) {
            ColorDrawable colorDrawable = (ColorDrawable) drawable;
            int normalColor = colorDrawable.getColor();
            return new ColorStateLayerDrawable(normalColor, params.getPressedFilter(), params.getDisabledAlpha());
        }
        return new DrawableStateLayerDrawable(drawable, params.getPressedFilter(), params.getDisabledAlpha());
    }


    private static class ColorStateLayerDrawable extends StateListDrawable {

        private ColorStateLayerDrawable(int normalColor, int pressedFilterColor, int disabledAlpha) {
            int pressedColor = ColorUtils.compositeColors(pressedFilterColor, normalColor);
            int disabledColor = ColorUtils.setAlphaComponent(normalColor, disabledAlpha);

            addState(new int[]{android.R.attr.state_pressed}, new ColorDrawable(pressedColor));
            addState(new int[]{android.R.attr.state_enabled}, new ColorDrawable(normalColor));
            addState(new int[]{}, new ColorDrawable(disabledColor));
        }
    }

    private static class DrawableStateLayerDrawable extends LayerDrawable {

        private ColorFilter mPressedFilter;
        private int mDisabledAlpha;

        private DrawableStateLayerDrawable(Drawable d, int disabledAlpha) {
            super(new Drawable[]{d});
            mDisabledAlpha = disabledAlpha;
        }

        private DrawableStateLayerDrawable(Drawable d, int pressedFilterColor, int disabledAlpha) {
            super(new Drawable[]{d});
            mPressedFilter = new PorterDuffColorFilter(pressedFilterColor, PorterDuff.Mode.SRC_ATOP);
            mDisabledAlpha = disabledAlpha;
        }

        @Override
        protected boolean onStateChange(int[] states) {
            boolean enabled = false;
            boolean pressed = false;
            for (int state : states) {
                if (state == android.R.attr.state_enabled) {
                    enabled = true;
                } else if (state == android.R.attr.state_pressed) {
                    pressed = true;
                }
            }

            mutate();

            ColorFilter filter = pressed ? mPressedFilter : null;
            setColorFilter(filter);

            int alpha = enabled ? 255 : mDisabledAlpha;
            setAlpha(alpha);

            invalidateSelf();
            return super.onStateChange(states);
        }

        @Override
        public boolean isStateful() {
            return true;
        }

    }

}