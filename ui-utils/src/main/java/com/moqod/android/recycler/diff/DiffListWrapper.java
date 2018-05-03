package com.moqod.android.recycler.diff;

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

public class DiffListWrapper<T extends DiffEntity> {

    private ArrayList<T> mData = new ArrayList<>();

    private final RecyclerView.Adapter mAdapter;

    public DiffListWrapper(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
    }

    public void setData(List<T> data) {
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffCallback<>(mData, data));
        mData.clear();
        mData.addAll(data);
        diffResult.dispatchUpdatesTo(mAdapter);
    }

    public void addData(List<T> data) {
        ArrayList<T> newData = new ArrayList<>(mData.size() + data.size());
        newData.addAll(mData);
        newData.addAll(data);
        setData(newData);
    }

    public T get(int position) {
        return mData.get(position);
    }

    public int size() {
        return mData.size();
    }

    public boolean remove(T t) {
        int index = mData.indexOf(t);
        if (index > -1) {
            mData.remove(index);
            mAdapter.notifyItemRemoved(index);
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private static class DiffCallback<T extends DiffEntity> extends DiffUtil.Callback {

        private List<T> mOldData;
        private List<T> mNewData;

        private DiffCallback(List<T> oldData, List<T> newData) {
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
