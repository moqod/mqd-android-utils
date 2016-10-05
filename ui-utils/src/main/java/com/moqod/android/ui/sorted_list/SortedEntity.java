package com.moqod.android.ui.sorted_list;

/**
 * Created with IntelliJ IDEA.
 * User: Sergey Chuvashev
 * Date: 19/09/16
 * Time: 19:37
 */
public interface SortedEntity<E extends SortedEntity> {
    boolean areItemsTheSame(E entity);
    boolean areContentsTheSame(E entity);
}