package edu.tjrac.swant.bestcase.widget.chart;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wpc on 2017/5/11.
 */

public  class XYChart extends View {
    //x
    public static final int RIGHT = 1;
    public static final int LEFT = 3;
    //y
    public static final int TOP = 0;
    public static final int BOTTOM = 2;

    int x_direction = RIGHT;
    int y_direction = TOP;

    void bindZeroPoint(Point p){

    }

    public XYChart(Context context) {
        super(context);
    }

    public XYChart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public XYChart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public int getX_direction() {
        return x_direction;
    }

    public void setX_direction(int x_direction) {
        this.x_direction = x_direction;
    }

    public int getY_direction() {
        return y_direction;
    }

    public void setY_direction(int y_direction) {
        this.y_direction = y_direction;
    }

}
