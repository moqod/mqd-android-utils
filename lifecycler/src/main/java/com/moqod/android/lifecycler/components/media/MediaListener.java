package com.moqod.android.lifecycler.components.media;

import android.net.Uri;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 08/06/16
 * Time: 14:41
 */
public interface MediaListener {
    void onCanceled(int requestCode);
    void onPhotoAdded(Uri uri, int requestCode);
    void onVideoAdded(Uri uri, int requestCode);
}
