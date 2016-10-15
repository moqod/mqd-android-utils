package com.moqod.android.sample.sorted_list;

import com.moqod.android.databinding.recycler.BindingAdapter;
import com.moqod.android.databinding.recycler.BindingViewHolder;
import com.moqod.android.sample.R;
import com.moqod.android.sample.databinding.ItemSortedAdapterBinding;
import com.moqod.android.ui.sorted_list.SortedListWrapper;

import java.util.List;

public class SortedAdapter extends BindingAdapter<ItemSortedAdapterBinding> {

    private SortedListWrapper<SimpleViewModel> mData;
    private EventListener mEventListener;

    public SortedAdapter(EventListener eventListener) {
        mEventListener = eventListener;
        mData = new SortedListWrapper<>(this);
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
