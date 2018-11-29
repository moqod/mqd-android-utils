package com.moqod.android.lifecycler;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 08/06/16
 * Time: 13:47
 */
public abstract class LifecycleAdapter implements Lifecycle {
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPostResume() {
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onStop() {
    }

    @Override
    public void onDestroy() {
    }

    @Override
    public void saveState(Bundle outState) {
    }

    @Override
    public void restoreState(@Nullable Bundle inState) {
    }
}
