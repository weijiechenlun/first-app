package com.konvy.renrenslider;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.VelocityTracker;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    //滚动显示和隐藏menu时，手指滑动需要达到的速度。
    public static final int SNAP_VELOCITY = 200;

    //屏幕宽度值
    private int screenWidth;

    //menu最多可以滑动到的左边缘。值由menu布局的宽度来定，marginLeft到达此值之后，不能再减少。
    private int leftEdge;

    //menu最多可以滑动到的右边缘。值恒为0，即marginLeft到达0之后，不能增加。
    private int rightEdge = 0;

    //menu完全显示时，留给content的宽度值。
    private int menuPadding = 80;

    //主内容的布局
    private View content;

    //菜单内容的布局
    private View menu;

    //menu布局的参数，通过此参数来更改leftMargin的值。
    private LinearLayout.LayoutParams menuParams;

    //手指按下时的横坐标
    private float xDown;

    //手指抬起时的横坐标
    private float xUp;

    //menu当前是显示还是隐藏。只有完全显示或隐藏menu时才会更改此值，滑动过程中此值无效。
    private boolean isMenuVisible;

    // 用于计算手指滑动的速度。
    private VelocityTracker velocityTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initValues();
    }

    private void initValues() {

    }

}
