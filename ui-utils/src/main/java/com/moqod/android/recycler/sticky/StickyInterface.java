package com.moqod.android.recycler.sticky;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zenkefer (zenkefer@gmail.com) on 25.02.2017
 */
public interface StickyInterface {

    int getHeaderType();

    int getFooterType();

    RecyclerView.ViewHolder convertToSticky(RecyclerView.ViewHolder holder);

}
