package edu.tjrac.swant.bestcase.widget.viewgroup;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * Created by Administrator on 2017/10/13 0013.
 */

public class RoundForgroundHroScrollView extends HorizontalScrollView {
    public RoundForgroundHroScrollView(Context context) {
        super(context);
    }

    public RoundForgroundHroScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundForgroundHroScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private float[] radiusArray = { 10f, 10f, 10f, 10f};
    @Override
    public void onDrawForeground(Canvas canvas) {
        super.onDrawForeground(canvas);
        Path path = new Path();
        path.addRoundRect(new RectF(0, 0, getWidth(), getHeight()),radiusArray, Path.Direction.CW);
        canvas.clipPath(path);
    }
}
