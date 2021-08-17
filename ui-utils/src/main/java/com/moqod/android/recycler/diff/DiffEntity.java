package com.moqod.android.recycler.diff;

import androidx.annotation.Nullable;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 19/09/16
 * Time: 19:37
 */
public interface DiffEntity {
    boolean areItemsTheSame(DiffEntity entity);
    boolean areContentsTheSame(DiffEntity entity);
    @Nullable Object getChangePayload(DiffEntity entity);
}
