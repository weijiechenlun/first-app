package com.konvy.flowlayout.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/8.
 */
public class FlowLayout extends ViewGroup {

    //存储每个item的位置
    private ArrayList<Map<String,Integer>> childrenPos = new ArrayList<>();

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //当该viewgroup的布局参数为wrap_content时，记录的宽和高
        int width = 0;
        int height = 0;

        //记录每一行的宽和高
        int lineWidth = 0;
        int lineHeight = 0;

        int count = getChildCount();
        for (int i = 0; i < count; i++){
            View child = getChildAt(i);
            //child的宽度
            int cWidth = child.getMeasuredWidth();
            //child的高度
            int cHeight = child.getMeasuredHeight();
            //child的布局参数
            MarginLayoutParams cParams = (MarginLayoutParams) child.getLayoutParams();
            //child的实际宽度
            int childWidth = cWidth + cParams.leftMargin + cParams.rightMargin;
            //child的实际高度
            int childHeight = cHeight + cParams.topMargin + cParams.bottomMargin;

            Map<String,Integer> map = new HashMap<String, Integer>();
            //如果加上当前child的宽度，超出最大宽度
            if (lineWidth + childWidth > widthSize){
                map.put("left", cParams.leftMargin);

                width = Math.max(width, lineWidth);
                height += lineHeight;

                //重新开启行，并记录
                lineWidth = childWidth;
                lineHeight = childHeight;
            }else {
                map.put("left", lineWidth + cParams.leftMargin);

                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
            map.put("top", height + cParams.topMargin);
            childrenPos.add(i,map);

            // 如果是最后一个，则将当前记录的最大宽度和当前lineWidth做比较，同样的也需要累加height
            if(i == count - 1){
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }
        }
        //如果是wrap_content设置为我们计算的值,否则：直接设置为父容器计算的值
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width, heightMode == MeasureSpec.EXACTLY ? heightSize : height);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(changed){
            int count = getChildCount();
            for (int i = 0; i < count; i++){
                View child = getChildAt(i);
                Map<String,Integer> childPos = childrenPos.get(i);
                child.layout(childPos.get("left"), childPos.get("top"), childPos.get("left") + child.getMeasuredWidth(), childPos.get("top") + child.getMeasuredHeight());
            }
        }
    }
}
