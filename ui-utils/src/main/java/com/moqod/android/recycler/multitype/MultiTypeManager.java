package com.moqod.android.recycler.multitype;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 30/08/15
 * Time: 21:49
 */
public class MultiTypeManager {

    private SparseArray<MultiTypeViewBinder> mBinderArray = new SparseArray<>();

    public MultiTypeManager() {
    }

    public void addBinder(int type, MultiTypeViewBinder binder) {
        mBinderArray.put(type, binder);
    }

    public final int getItemViewType(Object model) {
        for (int i = 0, size = mBinderArray.size(); i < size; i++) {
            MultiTypeViewBinder delegate = mBinderArray.valueAt(i);
            if (delegate.isValidModel(model)) {
                return mBinderArray.keyAt(i);
            }
        }
        throw  new RuntimeException("unknown type " + model.getClass().getName());
    }

    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return getViewBinder(viewType).onCreateViewHolder(LayoutInflater.from(parent.getContext()), parent);
    }

    @SuppressWarnings("unchecked")
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, Object model) {
        getViewBinder(getItemViewType(model)).onBindViewHolder(holder, model);
    }

    private MultiTypeViewBinder getViewBinder(int viewType) {
        return mBinderArray.get(viewType);
    }

}
