package com.moqod.android.ui.sorted_list;

import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 19/09/16
 * Time: 19:37
 */

public class SortedListWrapper<T extends SortedEntity> {

    private ArrayList<T> mData = new ArrayList<>();

    private final RecyclerView.Adapter mAdapter;

    public SortedListWrapper(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
    }

    public void setData(List<T> data) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new SortedCallback<>(mData, data));
        mData.clear();
        mData.addAll(data);
        diffResult.dispatchUpdatesTo(mAdapter);
    }

    public T get(int position) {
        return mData.get(position);
    }

    public int size() {
        return mData.size();
    }

    @SuppressWarnings("unchecked")
    private static class SortedCallback<T extends SortedEntity> extends DiffUtil.Callback {

        private List<T> mOldData;
        private List<T> mNewData;

        private SortedCallback(List<T> oldData, List<T> newData) {
            mOldData = oldData;
            mNewData = newData;
        }

        @Override
        public int getOldListSize() {
            return mOldData.size();
        }

        @Override
        public int getNewListSize() {
            return mNewData.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldData.get(oldItemPosition).areItemsTheSame(mNewData.get(newItemPosition));
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldData.get(oldItemPosition).areContentsTheSame(mNewData.get(newItemPosition));
        }
    }

}
