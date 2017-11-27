package com.moqod.android.recycler.multitype;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

/**
 * Created by zenkefer(zenkefer@gmail.com) on 12.09.2017
 */

public abstract class MultiTypeViewBinder<Holder extends RecyclerView.ViewHolder, Model> {

    public MultiTypeViewBinder() {
    }

    public abstract boolean isValidModel(Object model);

    public abstract Holder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent);

    public abstract void onBindViewHolder(Holder holder, Model model);

}