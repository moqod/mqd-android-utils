package com.moqod.android.lifecycler.app;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.moqod.android.lifecycler.Lifecycle;

import java.util.Collections;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 08/06/16
 * Time: 13:51
 */
public abstract class LifecycleFragment extends Fragment {

    private HashSet<Lifecycle> mLifecycleComponentList = new HashSet<>();

    private boolean mOnCreateCalled;

    protected void addLifecycle(Lifecycle... lifecycles) {
        if (mOnCreateCalled) {
            throw new IllegalStateException("method addLifecycle should be called before onCreate()");
        }
        Collections.addAll(mLifecycleComponentList, lifecycles);
    }

    protected void removeLifecycle(Lifecycle... lifecycles) {
        for (Lifecycle lifecycle : lifecycles) {
            mLifecycleComponentList.remove(lifecycle);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        for (Lifecycle lifecycle : mLifecycleComponentList) {
            lifecycle.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (Lifecycle lifecycle : mLifecycleComponentList) {
            lifecycle.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mOnCreateCalled = true;

        for (Lifecycle lifecycle : mLifecycleComponentList) {
            lifecycle.restoreState(savedInstanceState);
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        for (Lifecycle lifecycle : mLifecycleComponentList) {
            lifecycle.onStart();
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        for (Lifecycle lifecycle : mLifecycleComponentList) {
            lifecycle.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        for (Lifecycle lifecycle : mLifecycleComponentList) {
            lifecycle.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        for (Lifecycle lifecycle : mLifecycleComponentList) {
            lifecycle.onStop();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        for (Lifecycle lifecycle : mLifecycleComponentList) {
            lifecycle.onDestroy();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        for (Lifecycle lifecycle : mLifecycleComponentList) {
            lifecycle.saveState(outState);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mOnCreateCalled = false;
    }
}
