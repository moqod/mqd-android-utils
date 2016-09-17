package com.moqod.android.lifecycler.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.moqod.android.lifecycler.Lifecycle;

import java.util.Collections;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 08/06/16
 * Time: 13:44
 */
public abstract class LifecycleActivity extends AppCompatActivity {

    private HashSet<Lifecycle> mLifecycleComponentList = new HashSet<>();

    protected void addLifecycle(Lifecycle... lifecycles) {
        Collections.addAll(mLifecycleComponentList, lifecycles);
    }

    protected void removeLifecycle(Lifecycle... lifecycles) {
        for (Lifecycle lifecycle : lifecycles) {
            mLifecycleComponentList.remove(lifecycle);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        for (Lifecycle lifecycle : mLifecycleComponentList) {
            lifecycle.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        for (Lifecycle lifecycle : mLifecycleComponentList) {
            lifecycle.restoreState(savedInstanceState);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        for (Lifecycle lifecycle : mLifecycleComponentList) {
            lifecycle.onStart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        for (Lifecycle lifecycle : mLifecycleComponentList) {
            lifecycle.onResume();
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        for (Lifecycle lifecycle : mLifecycleComponentList) {
            lifecycle.onPostResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        for (Lifecycle lifecycle : mLifecycleComponentList) {
            lifecycle.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        for (Lifecycle lifecycle : mLifecycleComponentList) {
            lifecycle.onStop();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        for (Lifecycle lifecycle : mLifecycleComponentList) {
            lifecycle.onDestroy();
        }
        mLifecycleComponentList.clear();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        for (Lifecycle lifecycle : mLifecycleComponentList) {
            lifecycle.saveState(outState);
        }
    }

}
