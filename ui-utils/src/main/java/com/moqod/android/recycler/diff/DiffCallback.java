package com.moqod.android.recycler.diff;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

/**
 * Created by zenkefer on 04.10.2019
 */

public class DiffCallback extends DiffUtil.ItemCallback<DiffEntity>{

    @Override
    public boolean areItemsTheSame(@NonNull DiffEntity oldItem, @NonNull DiffEntity newItem) {
        return oldItem.areItemsTheSame(newItem);
    }

    @Override
    public boolean areContentsTheSame(@NonNull DiffEntity oldItem, @NonNull DiffEntity newItem) {
        return oldItem.areContentsTheSame(newItem);
    }

    @Nullable
    @Override
    public Object getChangePayload(@NonNull DiffEntity oldItem, @NonNull DiffEntity newItem) {
        return true;
    }
}
