package com.moqod.android.databinding.recycler;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 17/03/16
 * Time: 20:12
 */
public class BindingViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    protected T binding;

    public BindingViewHolder(T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public T getBinding() {
        return binding;
    }

    public static <T extends ViewDataBinding> BindingViewHolder<T> create(ViewGroup parent, @LayoutRes int layoutId) {
        T binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutId, parent, false);
        return new BindingViewHolder<>(binding);
    }

}
