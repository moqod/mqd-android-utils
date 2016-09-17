package com.moqod.android.lifecycler.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import com.moqod.android.lifecycler.Lifecycle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 17/08/16
 * Time: 20:47
 */
@PrepareForTest({FragmentActivity.class, AppCompatActivity.class})
@RunWith(PowerMockRunner.class)
public class LifecycleActivityTest {

    @Mock Lifecycle mComponent1;
    @Mock Lifecycle mComponent2;
    LifecycleActivity mActivity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.suppress(PowerMockito.everythingDeclaredIn(FragmentActivity.class));
        PowerMockito.suppress(PowerMockito.everythingDeclaredIn(AppCompatActivity.class));
        mActivity = new LifecycleActivity() {};
        mActivity.addLifecycle(mComponent1, mComponent2);
    }

    @Test
    public void testOnStart() {
        mActivity.onStart();
        Mockito.verify(mComponent1).onStart();
        Mockito.verify(mComponent2).onStart();
        Mockito.verifyNoMoreInteractions(mComponent1);
        Mockito.verifyNoMoreInteractions(mComponent2);
    }

    @Test
    public void testOnResume() {
        mActivity.onResume();

        Mockito.verify(mComponent1).onResume();
        Mockito.verify(mComponent2).onResume();
        Mockito.verifyNoMoreInteractions(mComponent1);
        Mockito.verifyNoMoreInteractions(mComponent2);
    }

    @Test
    public void testOnPause() {
        mActivity.onPause();

        Mockito.verify(mComponent1).onPause();
        Mockito.verify(mComponent2).onPause();
        Mockito.verifyNoMoreInteractions(mComponent1);
        Mockito.verifyNoMoreInteractions(mComponent2);
    }

    @Test
    public void testOnStop() {
        mActivity.onStop();

        Mockito.verify(mComponent1).onStop();
        Mockito.verify(mComponent2).onStop();
        Mockito.verifyNoMoreInteractions(mComponent1);
        Mockito.verifyNoMoreInteractions(mComponent2);
    }

    @Test
    public void testOnDestroy() {
        mActivity.onDestroy();

        Mockito.verify(mComponent1).onDestroy();
        Mockito.verify(mComponent2).onDestroy();
        Mockito.verifyNoMoreInteractions(mComponent1);
        Mockito.verifyNoMoreInteractions(mComponent2);
    }

    @Test
    public void testRemove() {
        mActivity.removeLifecycle(mComponent1);
        mActivity.onStart();

        Mockito.verifyNoMoreInteractions(mComponent1);
        Mockito.verify(mComponent2).onStart();
        Mockito.verifyNoMoreInteractions(mComponent2);
    }

    @Test
    public void testOnActivityResult() {
        Intent intent = Mockito.mock(Intent.class);
        mActivity.onActivityResult(1, 1, intent);

        Mockito.verify(mComponent1).onActivityResult(1, 1, intent);
        Mockito.verify(mComponent2).onActivityResult(1, 1, intent);
        Mockito.verifyNoMoreInteractions(mComponent1);
        Mockito.verifyNoMoreInteractions(mComponent2);
    }

    @Test
    public void testOnPostCreated() {
        Bundle bundle = Mockito.mock(Bundle.class);

        mActivity.onPostCreate(bundle);

        Mockito.verify(mComponent1).restoreState(bundle);
        Mockito.verify(mComponent2).restoreState(bundle);
        Mockito.verifyNoMoreInteractions(mComponent1);
        Mockito.verifyNoMoreInteractions(mComponent2);
    }

    @Test
    public void testOnPostResume() throws Exception {
        mActivity.onPostResume();

        Mockito.verify(mComponent1).onPostResume();
        Mockito.verify(mComponent2).onPostResume();
        Mockito.verifyNoMoreInteractions(mComponent1);
        Mockito.verifyNoMoreInteractions(mComponent2);
    }

    @Test
    public void testOnSaveInstanceState() throws Exception {
        Bundle bundle = Mockito.mock(Bundle.class);

        mActivity.onSaveInstanceState(bundle);

        Mockito.verify(mComponent1).saveState(bundle);
        Mockito.verify(mComponent2).saveState(bundle);
        Mockito.verifyNoMoreInteractions(mComponent1);
        Mockito.verifyNoMoreInteractions(mComponent2);
    }

}