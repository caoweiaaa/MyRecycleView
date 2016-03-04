package com.chs.myrecycleview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.chs.myrecycleview.R;

/**
 * 作者：chs on 2016/2/4 10:02
 * 邮箱：657083984@qq.com
 */
public class TimeLine extends View {
    public static final int START = 0;
    public static final int END = 1;
    public static final int NORMAL = 2;
    public static final int SPECIAL = 3;//只有一条数据的时候
    private int mIconSize;
    private int mLineSize;
    private Drawable mBeginLine;
    private Drawable mEndLine;
    private Drawable mIcon;

    public TimeLine(Context context) {
        this(context, null);
    }

    public TimeLine(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeLine(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.TimeLine);
        this.mBeginLine = array.getDrawable(R.styleable.TimeLine_begin_line);
        this.mEndLine = array.getDrawable(R.styleable.TimeLine_end_line);
        this.mIcon = array.getDrawable(R.styleable.TimeLine_my_icon);
        this.mIconSize = array.getDimensionPixelSize(R.styleable.TimeLine_my_icon_size,20);
        this.mLineSize = array.getDimensionPixelSize(R.styleable.TimeLine_line_size,2);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int w = mIconSize+getPaddingRight()+getPaddingLeft();
        int h = mIconSize+getPaddingTop() +getPaddingBottom();

        int widthSize = resolveSizeAndState(w,widthMeasureSpec,0);
        int heightSize = resolveSizeAndState(h,heightMeasureSpec,0);
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //当view显示的时候调用此方法
        initDrawable();
    }

    private void initDrawable() {
        int left = getPaddingLeft();
        int right = getPaddingRight();
        int top = getPaddingTop();
        int bottom = getPaddingBottom();

        int width = getWidth();
        int height = getHeight();
        int cWidth = width-left-right;
        int cHeight = height-top-bottom;
        int iconSize = Math.min(mIconSize,Math.min(cWidth,cHeight));
        Rect bounds = null;
        if(mIcon!=null){
            mIcon.setBounds(left,top,left+iconSize,top+iconSize);
            bounds = mIcon.getBounds();

        }
        int lineLeft = bounds.centerX() - mLineSize/2;//除以2
        if(mBeginLine!=null){
            mBeginLine.setBounds(lineLeft,0,lineLeft+mLineSize,bounds.top);
        }
        if(mEndLine!=null){
            mEndLine.setBounds(lineLeft,bounds.bottom,lineLeft+mLineSize,height);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        if(mBeginLine!=null){
//            mBeginLine.draw(canvas);
//        }
        if(mEndLine!=null){
            mEndLine.draw(canvas);
        }
        if(mIcon!=null){
            mIcon.draw(canvas);
        }
    }

    public void setIconSize(int mIconSize) {
        this.mIconSize = mIconSize;
        initDrawable();
        invalidate();
    }

    public void setLineSize(int mLineSize) {
        this.mLineSize = mLineSize;
        initDrawable();
        invalidate();
    }

    public void setBeginLine(Drawable mBeginLine) {
        this.mBeginLine = mBeginLine;
        initDrawable();
        invalidate();
    }

    public void setEndLine(Drawable mEndLine) {
        this.mEndLine = mEndLine;
        initDrawable();
        invalidate();
    }

    public void setIcon(Drawable mIcon) {
        this.mIcon = mIcon;
        initDrawable();
        invalidate();
    }
}
