package com.moqod.android.lifecycler.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
 * Date: 18/08/16
 * Time: 17:55
 */
@PrepareForTest({Fragment.class})
@RunWith(PowerMockRunner.class)
public class LifecycleFragmentTest {

    @Mock Lifecycle mComponent1;
    @Mock Lifecycle mComponent2;
    LifecycleFragment mActivity;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        PowerMockito.suppress(PowerMockito.everythingDeclaredIn(Fragment.class));
        mActivity = new LifecycleFragment() {};
        mActivity.addLifecycle(mComponent1, mComponent2);
    }

    @Test
    public void removeLifecycle() throws Exception {
        mActivity.removeLifecycle(mComponent1);
        mActivity.onStart();

        Mockito.verifyNoMoreInteractions(mComponent1);
        Mockito.verify(mComponent2).onStart();
        Mockito.verifyNoMoreInteractions(mComponent2);
    }

    @Test
    public void onActivityResult() throws Exception {
        Intent intent = Mockito.mock(Intent.class);
        mActivity.onActivityResult(1, 1, intent);

        Mockito.verify(mComponent1).onActivityResult(1, 1, intent);
        Mockito.verify(mComponent2).onActivityResult(1, 1, intent);
        Mockito.verifyNoMoreInteractions(mComponent1);
        Mockito.verifyNoMoreInteractions(mComponent2);
    }

    @Test
    public void onCreate() throws Exception {
        Bundle bundle = Mockito.mock(Bundle.class);

        mActivity.onCreate(bundle);

        Mockito.verify(mComponent1).restoreState(bundle);
        Mockito.verify(mComponent2).restoreState(bundle);
        Mockito.verifyNoMoreInteractions(mComponent1);
        Mockito.verifyNoMoreInteractions(mComponent2);
    }

    @Test
    public void onStart() throws Exception {
        mActivity.onStop();

        Mockito.verify(mComponent1).onStop();
        Mockito.verify(mComponent2).onStop();
        Mockito.verifyNoMoreInteractions(mComponent1);
        Mockito.verifyNoMoreInteractions(mComponent2);
    }

    @Test
    public void onResume() throws Exception {
        mActivity.onResume();

        Mockito.verify(mComponent1).onResume();
        Mockito.verify(mComponent2).onResume();
        Mockito.verifyNoMoreInteractions(mComponent1);
        Mockito.verifyNoMoreInteractions(mComponent2);
    }

    @Test
    public void onPause() throws Exception {
        mActivity.onPause();

        Mockito.verify(mComponent1).onPause();
        Mockito.verify(mComponent2).onPause();
        Mockito.verifyNoMoreInteractions(mComponent1);
        Mockito.verifyNoMoreInteractions(mComponent2);
    }

    @Test
    public void onStop() throws Exception {
        mActivity.onStop();

        Mockito.verify(mComponent1).onStop();
        Mockito.verify(mComponent2).onStop();
        Mockito.verifyNoMoreInteractions(mComponent1);
        Mockito.verifyNoMoreInteractions(mComponent2);
    }

    @Test
    public void onDestroyView() throws Exception {
        mActivity.onDestroyView();

        Mockito.verify(mComponent1).onDestroy();
        Mockito.verify(mComponent2).onDestroy();
        Mockito.verifyNoMoreInteractions(mComponent1);
        Mockito.verifyNoMoreInteractions(mComponent2);
    }

    @Test
    public void onSaveInstanceState() throws Exception {
        Bundle bundle = Mockito.mock(Bundle.class);

        mActivity.onSaveInstanceState(bundle);

        Mockito.verify(mComponent1).saveState(bundle);
        Mockito.verify(mComponent2).saveState(bundle);
        Mockito.verifyNoMoreInteractions(mComponent1);
        Mockito.verifyNoMoreInteractions(mComponent2);
    }

}