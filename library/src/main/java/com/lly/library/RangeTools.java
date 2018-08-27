package com.lly.library;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * RangeTools[v 1.0.0]
 * classes:com.lly.library.RangeTools
 *
 * @author lileiyi
 * @date 2018/8/27
 * @time 15:35
 * @description
 */
public class RangeTools {

    private RecyclerView mRecyclerView;

    private int mRangeViewId;

    /**
     * 构造方法
     *
     * @param mRecyclerView RecycleView
     * @param mRangeViewId  RangeViewID
     */
    public RangeTools(RecyclerView mRecyclerView, int mRangeViewId) {
        this.mRecyclerView = mRecyclerView;
        this.mRangeViewId = mRangeViewId;
        addOnScrollListener();
    }


    private void addOnScrollListener() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.v("test", "newState:=" + newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    int findFirst = layoutManager.findFirstCompletelyVisibleItemPosition();
                    int lastFirst = layoutManager.findLastCompletelyVisibleItemPosition();
                    for (int i = findFirst; i < lastFirst + 1; i++) {
                        View view = layoutManager.findViewByPosition(i);
                        RangeView rangeView = view.findViewById(mRangeViewId);
                        if (rangeView != null) {
                            Log.v("test", "newState:=" + view.getTop());
                            rangeView.moveRangeView(view, recyclerView.getHeight());
                        }
                    }
                }
            }
        });
    }

}
