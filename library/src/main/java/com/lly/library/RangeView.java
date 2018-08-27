package com.lly.library;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * RangeView[v 1.0.0]
 * classes:com.lly.rangelistdemo.RangeView
 *
 * @author lileiyi
 * @date 2018/8/22
 * @time 10:10
 * @description
 */
public class RangeView extends View {

    private Bitmap defBitmap;
    private Paint mPaint;

    private Rect src;//绘制bitmap区域
    private Rect def;//绘制的区域

    private int drawHeight;//绘制高度
    private int drawWidth;//绘制宽度


    /**
     * 设置图片显示
     *
     * @param defBitmap
     */
    public void setDefBitmap(Bitmap defBitmap) {
        this.defBitmap = defBitmap;
        postScaleBitmap();
    }

    public RangeView(Context context) {
        this(context, null);
    }

    public RangeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        drawHeight = getHeight();//绘制高度
        drawWidth = getWidth();

        postScaleBitmap();

        src.top = getScrBitmapHeight() - drawHeight;
        src.bottom = getScrBitmapHeight();
        src.right = getSrcBitmapWidth();

        def.bottom = drawHeight;
        def.right = getWidth();

    }

    /**
     * 初始化
     */
    private void init() {
        mPaint = new Paint();

        src = new Rect();
        src.top = 0;
        src.left = 0;

        def = new Rect();
        def.top = 0;
        src.left = 0;
    }

    /**
     * 获取图片的宽度
     */
    private int getSrcBitmapWidth() {
        return defBitmap != null ? defBitmap.getWidth() : 0;
    }

    /**
     * 获取图片的高度
     */
    private int getScrBitmapHeight() {
        return defBitmap != null ? defBitmap.getHeight() : 0;
    }


    /**
     * 移动图片
     *
     * @param recycleViewHeight
     */
    public void moveRangeView(View rangeView, int recycleViewHeight) {
        float rangeHeight = recycleViewHeight - rangeView.getHeight();
        float value = getScrBitmapHeight() / rangeHeight;
        int bottom = (int) (value * rangeView.getTop() + (1 - (value * rangeView.getTop() / getScrBitmapHeight())));
        bottom = (int) (bottom + (1 - (value * rangeView.getTop() / getScrBitmapHeight())) * drawHeight);
        if (bottom < getScrBitmapHeight() && bottom > drawHeight) {
            src.bottom = bottom;
            src.top = bottom - drawHeight;
            invalidate();
        }
    }

    public RangeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 对图片进行缩放,以便图片在容器不变形
     */
    private void postScaleBitmap() {
        if (drawWidth > 0 && defBitmap != null) {
            float scale = drawWidth * 1.0f / defBitmap.getWidth();
            Matrix matrix = new Matrix();
            matrix.postScale(scale, scale);
            defBitmap = Bitmap.createBitmap(defBitmap, 0, 0, defBitmap.getWidth(), defBitmap.getHeight(), matrix, true);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (defBitmap != null) {
            canvas.drawBitmap(defBitmap, src, def, mPaint);
        }
    }

}
