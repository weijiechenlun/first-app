package com.konvy.viewgroup1.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2015/10/8.
 */
public class Viewgroup1 extends ViewGroup {

    public Viewgroup1(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //用于计算左边两个child的高度
        int lHeight = 0;
        //用于计算右边两个child的高度，取左右两边高度的最大值
        int rHeight = 0;
        //用于计算上边两个child的宽度
        int tWidth = 0;
        //用于计算下边两个child的宽度，取上下两边宽度的最大值
        int bWidth = 0;

        int count = getChildCount();
        int cWidth = 0;
        int cHeight = 0;
        MarginLayoutParams cParams = null;
        for(int i = 0; i < count; i++){
            View child = getChildAt(i);

            measureChildWithMargins(child, widthMeasureSpec, 0, heightMeasureSpec, 0);

            cWidth = child.getMeasuredWidth();
            cHeight = child.getMeasuredHeight();
            cParams = (MarginLayoutParams) child.getLayoutParams();
            if(i == 0 || i == 1){
                tWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
            }
            if(i == 2 || i == 3){
                bWidth += cWidth + cParams.leftMargin + cParams.rightMargin;
            }
            if(i == 0 || i == 2){
                lHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
            }
            if(i == 1 || i == 3){
                rHeight += cHeight + cParams.topMargin + cParams.bottomMargin;
            }
        }

        int width = Math.max(tWidth,bWidth);
        int height = Math.max(lHeight,rHeight);

        //如果是wrap_content设置为我们计算的值,否则：直接设置为父容器计算的值
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width, heightMode == MeasureSpec.EXACTLY ? heightSize : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int count = getChildCount();
            int cWidth = 0;
            int cHeight = 0;
            MarginLayoutParams cParams = null;
            for(int i = 0; i < count; i++){
                View child = getChildAt(i);
                cWidth = child.getMeasuredWidth();
                cHeight = child.getMeasuredHeight();
                cParams = (MarginLayoutParams) child.getLayoutParams();

                int cl = 0, ct = 0, cr = 0, cb = 0;
                switch (i){
                    case 0:
                        cl = cParams.leftMargin;
                        ct = cParams.topMargin;
                        break;
                    case 1:
                        cl = getMeasuredWidth() - cWidth - cParams.rightMargin;
                        ct = cParams.topMargin;
                        break;
                    case 2:
                        cl = cParams.leftMargin;
                        ct = getMeasuredHeight() - cHeight - cParams.bottomMargin;
                        break;
                    case 3:
                        cl = getMeasuredWidth() - cWidth - cParams.rightMargin;
                        ct = getMeasuredHeight() - cHeight - cParams.bottomMargin;
                        break;
                }
                child.layout(cl, ct, cl + cWidth, ct + cHeight);
            }
        }
    }

}
