package com.moqod.android.databinding.recycler;

import android.databinding.OnRebindCallback;
import android.databinding.ViewDataBinding;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 03/06/16
 * Time: 13:03
 * Based on https://github.com/google/android-ui-toolkit-demos/blob/master/DataBinding/DataBoundRecyclerView/app/src/main/java/com/example/android/databoundrecyclerview/BaseDataBoundAdapter.java
 */
public abstract class BindingAdapter<T extends ViewDataBinding> extends RecyclerView.Adapter<BindingViewHolder<T>> {

    private static final Object PAYLOAD = new Object();
    @Nullable
    private RecyclerView mRecyclerView;

    private final OnRebindCallback mPreBindCallback = new OnRebindCallback() {
        @Override
        public boolean onPreBind(ViewDataBinding binding) {
            if (mRecyclerView == null || mRecyclerView.isComputingLayout()) {
                return true;
            }

            int adapterPosition = mRecyclerView.getChildAdapterPosition(binding.getRoot());
            if (adapterPosition == RecyclerView.NO_POSITION) {
                return true;
            }

            notifyItemChanged(adapterPosition, PAYLOAD);
            return false;
        }
    };

    @Override
    @CallSuper
    public BindingViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        BindingViewHolder<T> viewHolder = BindingViewHolder.create(parent, viewType);
        viewHolder.binding.addOnRebindCallback(mPreBindCallback);
        return viewHolder;
    }

    @Override
    public final void onBindViewHolder(BindingViewHolder<T> holder, int position) {
        onBindViewHolder(holder, position, Collections.emptyList());
    }

    @Override
    public final void onBindViewHolder(BindingViewHolder<T> holder, int position, List<Object> payloads) {
        if (payloads.isEmpty() || hasNonDataBindingInvalidate(payloads)) {
            bindItem(holder, position, payloads);
        }
        holder.binding.executePendingBindings();
    }

    private boolean hasNonDataBindingInvalidate(List<Object> payloads) {
        for (Object payload : payloads) {
            if (payload != PAYLOAD) {
                return true;
            }
        }
        return false;
    }

    protected abstract void bindItem(BindingViewHolder<T> holder, int position, List<Object> payloads);

    @Override
    public final int getItemViewType(int position) {
        return getLayoutId(position);
    }

    @LayoutRes
    protected abstract int getLayoutId(int position);

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        mRecyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mRecyclerView = null;
    }
}
