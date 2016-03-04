package com.chs.myrecycleview.adapter;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chs.myrecycleview.R;

/**
 * 作者：chs on 2016/2/2 15:20
 * 邮箱：657083984@qq.com
 */
public class RecycleViewDivider extends RecyclerView.ItemDecoration{
    private Drawable drawable;
    public RecycleViewDivider(Context context) {
        TypedArray array = context.obtainStyledAttributes(new int[]{android.R.attr.listDivider});
        drawable = array.getDrawable(0);
        array.recycle();
    }
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        drawVertical(c, parent);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        drawVertical(c, parent);
    }

    private void drawVertical(Canvas canvas, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth()-parent.getPaddingRight();
        View child;
        RecyclerView.LayoutParams layoutParams;
        int top;
        int bottom;
        int count = parent.getChildCount();
        for(int i = 0;i<count;i++){
            child = parent.getChildAt(i);
            layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            top = child.getBottom()+layoutParams.bottomMargin;
            bottom = top+drawable.getIntrinsicHeight();
            drawable.setBounds(left,top,right,bottom);
            drawable.draw(canvas);
        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (parent.getChildPosition(view) == parent.getChildCount() - 1) {
            outRect.set(0, 0, 0, 0);
        } else {
            outRect.set(0, 0, 0, drawable.getIntrinsicHeight());
        }
    }
}
