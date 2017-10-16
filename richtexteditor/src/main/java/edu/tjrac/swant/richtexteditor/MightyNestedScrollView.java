package edu.tjrac.swant.richtexteditor;

import android.content.Context;
import android.support.v4.widget.NestedScrollView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Administrator on 2017/10/14 0014.
 */

public class MightyNestedScrollView extends NestedScrollView {
    public MightyNestedScrollView(Context context) {
        super(context);
    }

    public MightyNestedScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MightyNestedScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        float x1=0,x2=0,y1=0,y2=0;
        if(ev.getAction() == MotionEvent.ACTION_DOWN) {
            //当手指按下的时候
            x1 = ev.getX();
            y1 = ev.getY();
        }
        if(ev.getAction() == MotionEvent.ACTION_UP) {
            //当手指离开的时候
            x2 = ev.getX();
            y2 = ev.getY();

            if(Math.abs(y1-y2)>Math.abs(x1-x2)){
                //上下滑动
                return true;
            }
//            if(y1 - y2 > 100) {
////                Toast.makeText(MainActivity.this, "向上滑", Toast.LENGTH_SHORT).show();
//            } else if(y2 - y1 > 50) {
////                Toast.makeText(MainActivity.this, "向下滑", Toast.LENGTH_SHORT).show();
//            } else if(x1 - x2 > 50) {
////                Toast.makeText(MainActivity.this, "向左滑", Toast.LENGTH_SHORT).show();
//            } else if(x2 - x1 > 50) {
////                Toast.makeText(MainActivity.this, "向右滑", Toast.LENGTH_SHORT).show();
//            }
        }
        return super.onTouchEvent(ev);
    }
}
