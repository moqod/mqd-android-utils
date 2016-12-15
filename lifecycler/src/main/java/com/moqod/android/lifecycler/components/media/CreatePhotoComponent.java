package com.moqod.android.lifecycler.components.media;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import com.moqod.android.lifecycler.LifecycleAdapter;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 08/06/16
 * Time: 14:27
 */
public class CreatePhotoComponent extends LifecycleAdapter {

    private static final int REQUEST_CODE_CAMERA = 101;
    private static final String KEY_FILE_URI = "CreatePhotoComponent::mFileUri";

    private MediaListener mListener;
    private @Nullable Uri mFileUri;

    public CreatePhotoComponent(MediaListener listener) {
        mListener = listener;
    }

    public void start(Fragment fragment, File cacheFile) {
        start(fragment, cacheFile, REQUEST_CODE_CAMERA);
    }

    public void start(Fragment fragment, File cacheFile, int requestCode) {
        mFileUri = Uri.fromFile(cacheFile);

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mFileUri);
        fragment.startActivityForResult(intent, requestCode);
    }

    @Override
    public void restoreState(@Nullable Bundle inState) {
        if (inState != null) {
            mFileUri = inState.getParcelable(KEY_FILE_URI);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CAMERA) {
            if (resultCode == Activity.RESULT_OK && mFileUri != null) {
                mListener.onPhotoAdded(mFileUri);
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
