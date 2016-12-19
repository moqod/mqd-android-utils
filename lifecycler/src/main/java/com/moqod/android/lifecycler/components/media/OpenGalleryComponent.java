package com.moqod.android.lifecycler.components.media;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.support.v4.app.Fragment;
import com.moqod.android.lifecycler.LifecycleAdapter;

import static android.R.attr.data;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 08/06/16
 * Time: 14:39
 */
public class OpenGalleryComponent extends LifecycleAdapter {

    private ContentResolver mContentResolver;
    private MediaListener mListener;
    private boolean mVideo;
    private final int mRequestCode;

    public OpenGalleryComponent(ContentResolver contentResolver, int requestCode, MediaListener listener) {
        mContentResolver = contentResolver;
        mRequestCode = requestCode;
        mListener = listener;
    }

    public OpenGalleryComponent(ContentResolver contentResolver, int requestCode, MediaListener listener, boolean video) {
        mContentResolver = contentResolver;
        mRequestCode = requestCode;
        mListener = listener;
        mVideo = video;
    }

    public void start(Fragment fragment) {
        String[] mimeTypes;
        if (mVideo) {
            mimeTypes = new String[] {
                    "image/*", "video/mp4"
            };
        } else {
            mimeTypes = new String[] {
                    "image/*"
            };
        }

        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        fragment.startActivityForResult(intent, mRequestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == mRequestCode) {
            if (resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
                final int takeFlags = data.getFlags() & (Intent.FLAG_GRANT_READ_URI_PERMISSION);
                mContentResolver.takePersistableUriPermission(data.getData(), takeFlags);
                String type = mContentResolver.getType(data.getData());

                if (type != null) {
                    if (type.contains("image")) {
                        mListener.onPhotoAdded(data.getData(), requestCode);
                    } else if (type.contains("video")) {
                        mListener.onVideoAdded(data.getData(), requestCode);
                    }
                }
            } else {
                mListener.onCanceled(requestCode);
            }
        }
    }
}
