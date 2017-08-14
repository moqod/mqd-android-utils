package com.moqod.android.recycler.sticky;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zenkefer (zenkefer@gmail.com) on 25.02.2017
 */
public class StickyDecoration extends RecyclerView.ItemDecoration implements RecyclerView.OnItemTouchListener {

    private RecyclerView.Adapter mAdapter;
    private StickyInterface mStickyInterface;

    private int mHeaderType;
    private List<Integer> mHeaderPosList = new ArrayList<>();
    private RecyclerView.ViewHolder mHeaderHolderInternal;
    private RectF mHeaderRect = new RectF();

    private int mFooterType;
    private List<Integer> mFooterPosList = new ArrayList<>();
    private RecyclerView.ViewHolder mFooterHolderInternal;
    private RectF mFooterRect = new RectF();

    public StickyDecoration() {

    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        if (!(adapter instanceof StickyInterface)) {
            throw new RuntimeException("adapter should implement the StickyInterface");
        }

        if (mAdapter != null) mAdapter.unregisterAdapterDataObserver(mAdapterDataObserver);
        mAdapter = adapter;
        mAdapter.registerAdapterDataObserver(mAdapterDataObserver);

        mStickyInterface = (StickyInterface) adapter;
        mHeaderType = mStickyInterface.getHeaderType();
        mFooterType = mStickyInterface.getFooterType();

        onDataChanged();
    }

    private RecyclerView.AdapterDataObserver mAdapterDataObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            onDataChanged();
        }

        public void onItemRangeInserted(int positionStart, int itemCount) {
            onDataChanged();
        }

        public void onItemRangeRemoved(int positionStart, int itemCount) {
            onDataChanged();
        }

        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            onDataChanged();
        }
    };

    private void onDataChanged() {
        List<Integer> headerPosList = new ArrayList<>();
        int headerPos = -1;
        for (int pos = 0; pos < mAdapter.getItemCount(); pos++) {
            if (mAdapter.getItemViewType(pos) == mHeaderType) headerPos = pos;
            headerPosList.add(headerPos);
        }
        mHeaderPosList = headerPosList;

        List<Integer> footerPosList = new ArrayList<>();
        int footerPos = -1;
        for (int pos = mAdapter.getItemCount() - 1; pos >= 0; pos--) {
            if (mAdapter.getItemViewType(pos) == mFooterType) footerPos = pos;
            footerPosList.add(footerPos);
        }
        Collections.reverse(footerPosList);
        mFooterPosList = footerPosList;

        mHeaderHolderInternal = null;
        mFooterHolderInternal = null;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        if (mHeaderHolderInternal != null && mHeaderRect.contains(e.getX(), e.getY())) {
            if (e.getAction() == MotionEvent.ACTION_DOWN) {

                rv.scrollToPosition(mHeaderHolderInternal.getPosition());

                mHeaderHolderInternal.itemView.performClick();

                mHeaderHolderInternal = null;
                rv.invalidate();
            }
            return true;
        }

        if (mFooterHolderInternal != null && mFooterRect.contains(e.getX(), e.getY())) {
            if (e.getAction() == MotionEvent.ACTION_DOWN) {
                mFooterHolderInternal.itemView.performClick();

                mFooterHolderInternal = null;
                rv.invalidate();
            }
            return true;
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    @Override
    public void onDrawOver(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        if (mHeaderType >= 0) {
            int topPos = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
            mHeaderRect.set(0, 0, 0, 0);

            if (topPos != RecyclerView.NO_POSITION) {
                View topView = parent.findViewHolderForAdapterPosition(topPos).itemView;

                int headerPos = mHeaderPosList.get(topPos);
                if (headerPos >= 0) {
                    View headerView = getHeader(parent, headerPos).itemView;

                    final int left = topView.getLeft();
                    final int top = getHeaderTop(topView, headerView, topPos);

                    mHeaderRect.set(left, top, topView.getRight(), top + headerView.getHeight());

                    canvas.save();
                    canvas.translate(left, top);
                    headerView.setTranslationX(left);
                    headerView.setTranslationY(top);
                    headerView.draw(canvas);
                    canvas.restore();
                }
            }
        }

        if (mFooterType >= 0) {
            int bottomPos = ((LinearLayoutManager) parent.getLayoutManager()).findLastVisibleItemPosition();
            mFooterRect.set(0, 0, 0, 0);

            if (bottomPos != RecyclerView.NO_POSITION) {
                View bottomView = parent.findViewHolderForAdapterPosition(bottomPos).itemView;

                int footerPos = mFooterPosList.get(bottomPos);
                if (footerPos >= 0) {
                    View footerView = getFooter(parent, footerPos).itemView;

                    final int left = bottomView.getLeft();
                    final int top = getFooterTop(bottomView, footerView, bottomPos, parent.getHeight());

                    mFooterRect.set(left, top, bottomView.getRight(), top + footerView.getHeight());

                    canvas.save();
                    canvas.translate(left, top);
                    footerView.setTranslationX(left);
                    footerView.setTranslationY(top);
                    footerView.draw(canvas);
                    canvas.restore();
                }
            }
        }
    }

    private RecyclerView.ViewHolder getHeader(RecyclerView parent, int headerPos) {
        if (mHeaderHolderInternal == null) {
            mHeaderHolderInternal = mStickyInterface.convertToSticky(mAdapter.createViewHolder(parent, mHeaderType));
        }
        if (mHeaderHolderInternal.getPosition() != headerPos) {
            //noinspection unchecked
            mAdapter.bindViewHolder(mHeaderHolderInternal, headerPos);
            updateViewSize(parent, mHeaderHolderInternal.itemView);
        }
        return mHeaderHolderInternal;
    }

    private RecyclerView.ViewHolder getFooter(RecyclerView parent, int footerPos) {
        if (mFooterHolderInternal == null) {
            mFooterHolderInternal = mStickyInterface.convertToSticky(mAdapter.createViewHolder(parent, mFooterType));
        }
        if (mFooterHolderInternal.getPosition() != footerPos) {
            //noinspection unchecked
            mAdapter.bindViewHolder(mFooterHolderInternal, footerPos);
            updateViewSize(parent, mFooterHolderInternal.itemView);
        }
        return mFooterHolderInternal;
    }

    private int getHeaderTop(View child, View header, int topPos) {
        if (!mHeaderPosList.contains(topPos + 1)) return 0;
        if (equals(mHeaderPosList.get(topPos), mHeaderPosList.get(topPos + 1))) return 0;

        int childBottom = child.getBottom();
        int headerHeight = header.getHeight();

        if (headerHeight <= childBottom) return 0;

        return childBottom - headerHeight;
    }

    private int getFooterTop(View child, View footer, int bottomPos, int parentHeight) {
        int headerHeight = footer.getHeight();
        int headerTop = parentHeight - headerHeight;

        if (!mFooterPosList.contains(bottomPos - 1)) return headerTop;
        if (equals(mFooterPosList.get(bottomPos), mFooterPosList.get(bottomPos - 1)))
            return headerTop;

        int childTop = child.getTop();

        if (headerTop >= childTop) return headerTop;

        return childTop;
    }

    private void updateViewSize(RecyclerView parent, View view) {
        int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getMeasuredWidth(), View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getMeasuredHeight(), View.MeasureSpec.UNSPECIFIED);
        int childWidth = ViewGroup.getChildMeasureSpec(
                widthSpec,
                parent.getPaddingLeft() + parent.getPaddingRight(),
                view.getLayoutParams().width
        );
        int childHeight = ViewGroup.getChildMeasureSpec(
                heightSpec,
                parent.getPaddingTop() + parent.getPaddingBottom(),
                view.getLayoutParams().height
        );

        view.measure(childWidth, childHeight);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
    }


    private static boolean equals(Object a, Object b) {
        return (a == b) || (a != null && a.equals(b));
    }

}
