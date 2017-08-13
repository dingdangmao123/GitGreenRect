package com.dingdangmao.gitgreenrect;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by suxiaohui on 2017/8/13.
 */

public class GitGreenRect extends View {

    int cStart;
    int cEnd;
    int max;
    int min;
    float size;
    float space;
    int row;
    int col;
    int[][] data = null;
    String[] color = new String[]{"#ebedf0", "#c6e48b", "#7bc96f", "#239a3b", "#196127"};
    Paint p;
    onListener listener;

    public GitGreenRect(Context context) {
        this(context, null);
    }

    public GitGreenRect(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GitGreenRect(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);

    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray attr = context.obtainStyledAttributes(attrs, R.styleable.GitGreenRect, defStyleAttr, 0);
        try {
            cStart = attr.getColor(R.styleable.GitGreenRect_cStart, Color.parseColor("#ebedf0"));
            cEnd = attr.getColor(R.styleable.GitGreenRect_cEnd, Color.parseColor("#196127"));
            size = attr.getDimension(R.styleable.GitGreenRect_size, 0);
            space = attr.getDimension(R.styleable.GitGreenRect_space, 0);
        } catch (Exception e) {
            Log.i("Unit", e.toString());
        } finally {
            attr.recycle();
        }

        p = new Paint();
        p.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (widthMode == MeasureSpec.AT_MOST && heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(300, 300);
        } else if (widthMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(300, heightSize);
        } else if (heightMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSize, 300);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width=getWidth()-getPaddingRight();
        int height=getHeight()-getPaddingBottom();
        float left ;
        float top ;
        for (int i = 0; i < data.length; i++) {
            top = (size + space) * i+ getPaddingTop();
            if(top+size>height)
                break;
            for (int j = 0; j < data[i].length; j++) {
                p.setColor(getColor(data[i][j]));
                left = (size + space) * j+getPaddingLeft();
                if(left+size>width)
                    break;
                canvas.drawRect(left, top, left + size, top + size, p);
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (listener == null)
            return true;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                row = getRow(event.getY());
                if (row == -1)
                    return true;
                col = getCol(event.getX());
                if (col == -1)
                    return true;
                listener.onDown(row, col);
                break;
            case MotionEvent.ACTION_UP:
                int row = getRow(event.getY());
                int col = getCol(event.getX());
                if (row == -1 || col == -1)
                    return true;
                if (row == this.row && col == this.col) {
                    listener.onUp(row, col);
                } else
                    listener.onDiff(this.row, this.col, row, col);
        }
        return true;
    }

    private int getRow(float y) {
        int i = (int) (y / (size + space));
        if (i * (size + space) <= y && i * (size + space) + size >= y && i < data.length)
            return i;
        return -1;
    }

    private int getCol(float x) {
        int i = (int) (x / (size + space));
        if (row != -1 && i * (size + space) <= x && i * (size + space) + size >= x && i < data[row].length)
            return i;
        return -1;
    }

    public void addData(int[][] data) {
        this.data = data;
        getMaxAndMin();
        invalidate();
    }

    public void addColor(String[] color) {
        this.color = color;
    }

    private int getColor(int v) {

        if (cStart >= 0 && cEnd > 0)
            return cStart + (cEnd - cStart) * (v - min) / (max - min);
        if (this.color != null && this.color.length > 0) {
            int i = (v - min) / Math.max(1, ((max - min) / this.color.length));
            i = Math.min(this.color.length - 1, i);
            return Color.parseColor(this.color[i]);
        }
        throw new RuntimeException("color array is invalid");
    }

    private void getMaxAndMin() {
        int tmin = 0;
        int tmax = 0;
        for (int[] i : data)
            for (int tmp : i) {
                tmax = Math.max(tmp, tmax);
                tmin = Math.min(tmp, tmin);
            }
        this.max = tmax;
        this.min = tmin;
    }

    public void clearData() {
        this.data = null;
    }

    public void addListener(onListener listener) {
        this.listener = listener;
    }

    public int getcEnd() {
        return cEnd;
    }

    public void setcEnd(int cEnd) {
        this.cEnd = cEnd;
    }

    public int getcStart() {
        return cStart;
    }

    public void setcStart(int cStart) {
        this.cStart = cStart;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
        invalidate();
    }


}
