package com.moqod.android.lifecycler;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 08/06/16
 * Time: 13:46
 */
public interface Lifecycle {
    void onActivityResult(int requestCode, int resultCode, Intent data);
    void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults);
    void onStart();
    void onResume();
    void onPostResume();
    void onPause();
    void onStop();
    void onDestroy();
    void saveState(Bundle outState);
    void restoreState(@Nullable Bundle inState);
}
