package com.moqod.android.base.data.repository;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 26/11/2016
 * Time: 18:38
 */
public interface Refresh {
    Observable<?> getNotification();
    Scheduler getScheduler();
}
