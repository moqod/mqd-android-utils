package com.moqod.android.sample.sorted_list;

import com.moqod.android.ui.sorted_list.SortedEntity;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 15/10/2016
 * Time: 22:52
 */

public class SimpleViewModel implements SortedEntity<SimpleViewModel> {

    public final int id;
    public final String title;

    public SimpleViewModel(int id, String title) {
        this.id = id;
        this.title = title;
    }

    @Override
    public boolean areItemsTheSame(SimpleViewModel entity) {
        return entity.id == id;
    }

    @Override
    public boolean areContentsTheSame(SimpleViewModel entity) {
        return title.equals(entity.title);
    }
}
