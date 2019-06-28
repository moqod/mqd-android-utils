package com.moqod.android.lifecycler.components.media;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import com.moqod.android.lifecycler.LifecycleAdapter;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 08/06/16
 * Time: 14:27
 */
public class CreatePhotoComponent extends LifecycleAdapter {

    private static final String KEY_FILE_URI = "CreatePhotoComponent::mFileUri";

    private MediaListener mListener;
    private @Nullable Uri mFileUri;
    private final int mRequestCode;

    public CreatePhotoComponent(int requestCode, MediaListener listener) {
        mRequestCode = requestCode;
        mListener = listener;
    }

    public void start(Fragment fragment, File cacheFile) {
        Context context = fragment.requireContext();
        mFileUri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", cacheFile);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, mFileUri);
        fragment.startActivityForResult(intent, mRequestCode);
    }

    @Override
    public void restoreState(@Nullable Bundle inState) {
        if (inState != null) {
            mFileUri = inState.getParcelable(KEY_FILE_URI);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == mRequestCode) {
            if (resultCode == Activity.RESULT_OK && mFileUri != null) {
                mListener.onPhotoAdded(mFileUri, requestCode);
            } else {
                mListener.onCanceled(requestCode);
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
