package com.moqod.android.recycler.multitype;

import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 30/08/15
 * Time: 21:49
 */
public abstract class MultiTypeAdapter extends RecyclerView.Adapter {

    private MultiTypeManager mManager = new MultiTypeManager();

    public MultiTypeAdapter() {
    }

    protected void addBinder(int type, MultiTypeViewBinder binder) {
        mManager.addBinder(type, binder);
    }

    protected abstract Object getItem(int position);

    @Override
    public final int getItemViewType(int position) {
        Object model = getItem(position);
        return mManager.getItemViewType(model);
    }

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    @SuppressWarnings("unchecked")
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mManager.onBindViewHolder(holder, getItem(position));
    }
}
