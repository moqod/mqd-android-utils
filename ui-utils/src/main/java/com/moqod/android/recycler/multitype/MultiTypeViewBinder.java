package com.moqod.android.recycler.multitype;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by zenkefer(zenkefer@gmail.com) on 12.09.2017
 */

public abstract class MultiTypeViewBinder<Holder extends RecyclerView.ViewHolder, Model> {

    public MultiTypeViewBinder() {
    }

    public abstract boolean isValidModel(Object model);

    public abstract Holder onCreateViewHolder(LayoutInflater inflater, ViewGroup parent);

    public void onBindViewHolder(Holder holder, Model model) {}

    @SuppressWarnings("rawtypes")
    public void onBindViewHolder(Holder holder, Model model, List payloads) {
        onBindViewHolder(holder, model);
    }
}