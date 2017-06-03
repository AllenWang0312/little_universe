package edu.tjrac.swant.bestcase.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * surfaceview 套路
 * Created by wpc on 2017/4/20.
 */

public class OscilloscopeSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private Thread t;
    private boolean isRunning;


    public OscilloscopeSurfaceView(Context context) {
        this(context, null);
    }

    public OscilloscopeSurfaceView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public OscilloscopeSurfaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHolder = getHolder();
        mHolder.addCallback(this);
        //可获得焦点
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);
    }

    int width, height, cent_x, cent_y;
    int max_value = 10;
    int values_count = 100;
    float x_density, y_density;
    ArrayList<Double> values;

    Paint p;

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        p = new Paint(Paint.ANTI_ALIAS_FLAG);
        p.setColor(Color.GREEN);
        p.setStrokeWidth(2);

        width = this.getWidth();
        height = this.getHeight();
        Log.i("surfaceCreated", "width" + width + "height" + height);

        cent_x = width / 2;
        cent_y = height / 2;
        x_density = (width / (float) (values_count + 1));
        y_density = cent_y / ((float) (max_value));

        isRunning = true;
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                if (isRunning) {
                    long start = System.currentTimeMillis();
                    Log.i("draw start", "" + start);
                    draw();
                    long end = System.currentTimeMillis();
                    if (end - start < 50) {
                        try {
                            Thread.sleep(50 - (end - start));
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        t.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();

            if (mCanvas != null) {
//draw something
                mCanvas.drawColor(0x000000);
                if(values!=null){
                    for (int i = 0; i < values.size(); i++) {
                        float x = x_density * (i + 1);
                        mCanvas.drawLine(x, cent_y, x, (float) (cent_y - values.get(i) * y_density), p);
                    }
                }
            }
        } catch (Exception e) {

        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }


    public int getMax_value() {
        return max_value;
    }

    public void setMax_value(int max_value) {
        this.max_value = max_value;
    }

    public void addValue(Double d) {
        if (values == null) {
            values = new ArrayList<>();
        }
        if (values.size() == values_count) {
            values.remove(0);
        }
        values.add(d);
    }
}

