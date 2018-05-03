package com.moqod.android.sample.diff;

import com.moqod.android.databinding.recycler.BindingAdapter;
import com.moqod.android.databinding.recycler.BindingViewHolder;
import com.moqod.android.sample.R;
import com.moqod.android.sample.databinding.ItemSortedAdapterBinding;
import com.moqod.android.recycler.diff.DiffListWrapper;

import java.util.List;

public class DiffAdapter extends BindingAdapter<ItemSortedAdapterBinding> {

    private DiffListWrapper<SimpleViewModel> mData;
    private EventListener mEventListener;

    public DiffAdapter(EventListener eventListener) {
        mEventListener = eventListener;
        mData = new DiffListWrapper<>(this);
    }

    public void setData(List<SimpleViewModel> data) {
        mData.setData(data);
    }

    @Override
    protected void bindItem(BindingViewHolder<ItemSortedAdapterBinding> viewHolder, int position, List<Object> payload) {
        ItemSortedAdapterBinding binding = viewHolder.getBinding();
        binding.setModel(mData.get(position));
        binding.setEventListener(mEventListener);
    }

    @Override
    protected int getLayoutId(int position) {
        return R.layout.item_sorted_adapter;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
