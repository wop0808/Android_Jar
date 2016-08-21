package hkc.com.recyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Random;

/**
 * Created by DIY on 2016/8/20.
 */
public class MyDecoration extends RecyclerView.ItemDecoration {
    public static final double DOUBLE = 37.;
    public static final double DOUBLE1 = 61.;
    private Context context;
    private Drawable mDivider;
    int orientation;
    private int[] attrs = new int[]{android.R.attr.listDivider};

    public MyDecoration(Context context, int orientation) {
        this.context = context;
        this.orientation = orientation;
        TypedArray typedArray = context.obtainStyledAttributes(attrs);
        Random random = new Random();
        mDivider = typedArray.getDrawable(random.nextInt(attrs.length));
        typedArray.recycle();
//        mDivider = typedArray.getDrawable(0);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        drawHDeraction(c, parent);
//        drawVDeraction(c, parent);

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if (OrientationHelper.HORIZONTAL == orientation) {  //逻辑判断应该绘制水平或垂直
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        } else {
            outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
        }
    }



    private void drawHDeraction(Canvas c, RecyclerView parent) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {  //水平分割线绘制逻辑
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int top = child.getBottom() + layoutParams.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    private void drawVDeraction(Canvas c, RecyclerView parent) {
        int top = parent.getPaddingTop();
        int bottom = parent.getHeight() - parent.getPaddingBottom();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {  //垂直分割线绘制逻辑
            View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            int left = child.getRight() + layoutParams.rightMargin;
            int right = left + mDivider.getIntrinsicWidth();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

}

