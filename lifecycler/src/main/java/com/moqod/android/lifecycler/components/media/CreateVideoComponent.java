package com.moqod.android.lifecycler.components.media;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.moqod.android.lifecycler.LifecycleAdapter;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 09/06/16
 * Time: 14:12
 */
public class CreateVideoComponent extends LifecycleAdapter {

    private static final int REQUEST_CODE = 201;
    private static final String KEY_FILE_URI = "CreateVideoComponent::mFileUri";

    private MediaListener mListener;
    private @Nullable Uri mFileUri;


    public CreateVideoComponent(@NonNull MediaListener listener) {
        mListener = listener;
    }

    public void start(Fragment fragment, File cacheFile) {
        mFileUri = Uri.fromFile(cacheFile);

        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mFileUri);
        fragment.startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public void restoreState(@Nullable Bundle inState) {
        if (inState != null) {
            mFileUri = inState.getParcelable(KEY_FILE_URI);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK && mFileUri != null) {
                mListener.onVideoAdded(mFileUri);
            } else {
                mListener.onCanceled();
            }
        }
    }

    @Override
    public void saveState(Bundle outState) {
        if (mFileUri != null) {
            outState.putParcelable(KEY_FILE_URI, mFileUri);
        }
    }

}