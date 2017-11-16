package net.vrgsoft.layoutmanager;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

public class RollingLayoutManager extends LinearLayoutManager {

    private static final String TAG = "RollingLayoutManager";

    public RollingLayoutManager(Context context) {
        super(context);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        updateViewScale();
        super.onLayoutChildren(recycler, state);
    }

    private void updateViewScale() {
        float height = getHeight();
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            int childHeight = view.getHeight();
            float rotationTresholdPersent = 1f - (childHeight / height);
            int thresholdPx = (int) (height * (rotationTresholdPersent));
            float scale;
            int viewTop = getDecoratedTop(view);
            if (viewTop >= thresholdPx) {
                int delta = viewTop - thresholdPx;
                scale = (childHeight - delta) / (float) childHeight;
                scale = Math.max(scale, 0);
                view.setAlpha(0.1f + 0.9f * scale);
                view.setPivotX(view.getWidth() / 2);
                view.setPivotY(-view.getHeight() / 12);
                view.setRotationX(-90 * (1 - scale));
            } else {
                view.setRotationX(0);
                view.setAlpha(1f);
            }
        }
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
        if (position >= getItemCount()) {
            Log.e(TAG, "Cannot scroll to " + position + ", item count is " + getItemCount());
            return;
        }

        LinearSmoothScroller scroller = new LinearSmoothScroller(recyclerView.getContext()) {
            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return RollingLayoutManager.this.computeScrollVectorForPosition(targetPosition);
            }

            @Override
            protected int getVerticalSnapPreference() {
                return SNAP_TO_START;
            }
        };
        scroller.setTargetPosition(position);
        startSmoothScroll(scroller);
    }

    public PointF computeScrollVectorForPosition(int targetPosition) {
        if (getChildCount() == 0) {
            return null;
        }
        final int firstChildPos = getPosition(getChildAt(0));
        final int direction = targetPosition < firstChildPos ? -1 : 1;
        return new PointF(0, direction);
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        updateViewScale();
        return super.scrollVerticallyBy(dy, recycler, state);
    }
}