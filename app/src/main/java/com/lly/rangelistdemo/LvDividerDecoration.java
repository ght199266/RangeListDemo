package com.lly.rangelistdemo;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * LvDividerDecoration[v 1.0.0]
 * classes:com.lly.test.view.LvDividerDecoration
 *
 * @author lileiyi
 * @date 2018/6/6
 * @time 14:37
 * @description 自定义RecycleView分割线
 */

public class LvDividerDecoration extends RecyclerView.ItemDecoration {

    /**
     * 是否显示头部分割线
     */
    private boolean mHeaderDividersEnabled = false;

    /**
     * 分割线高度
     */
    private int mDividerHeight = 10;


    private int mOrientation = -1;//列表的方向：LinearLayoutManager.VERTICAL或LinearLayoutManager.HORIZONTAL

    /**
     * 绘制分割线的颜色
     */
    private Paint mPaint;


    public LvDividerDecoration(int mDividerHeight) {
        this.mDividerHeight = mDividerHeight;
    }


    public void setHeaderDividersEnabled(boolean mHeaderDividersEnabled) {
        this.mHeaderDividersEnabled = mHeaderDividersEnabled;
    }

    public LvDividerDecoration(int mDividerHeight, int color) {
        this.mDividerHeight = mDividerHeight;
        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setStyle(Paint.Style.FILL);
    }


    private int getOrientation(RecyclerView parent) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) parent.getLayoutManager();
        return linearLayoutManager.getOrientation();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        int position = parent.getChildAdapterPosition(view);
        int orientation = getOrientation(parent);
        if (orientation == LinearLayoutManager.VERTICAL) {
            if (mHeaderDividersEnabled) {
                if (position == 0)
                    outRect.top = mDividerHeight;
            }
            outRect.bottom = mDividerHeight;
        } else if (orientation == LinearLayoutManager.HORIZONTAL) {
            if (mHeaderDividersEnabled) {
                if (position == 0)
                    outRect.left = mDividerHeight;
            }
            outRect.right = mDividerHeight;
        }

    }


    /**
     * 绘制竖直方向的分割线
     */
    private void drawVertical(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            if (mHeaderDividersEnabled) {
                if (i == 0) {
                    c.drawRect(left, view.getTop(), right, view.getTop() - mDividerHeight, mPaint);
                }
            }
            c.drawRect(left, view.getBottom(), right, view.getBottom() + mDividerHeight, mPaint);
        }
    }

    /**
     * 绘制横向的分割线
     */
    private void drawHorizontal(Canvas c, RecyclerView parent) {
        int childCount = parent.getChildCount();
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            if (mHeaderDividersEnabled) {
                if (i == 0) {
                    c.drawRect(view.getLeft() - mDividerHeight, top, view.getLeft(), bottom, mPaint);
                }
            }
            c.drawRect(view.getRight(), top, view.getRight() + mDividerHeight, bottom, mPaint);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (mPaint != null) {
            int orientation = getOrientation(parent);
            if (orientation == LinearLayoutManager.VERTICAL) {
                drawVertical(c, parent);
            } else if (orientation == LinearLayoutManager.HORIZONTAL) {
                drawHorizontal(c, parent);
            }
        }
    }
}
