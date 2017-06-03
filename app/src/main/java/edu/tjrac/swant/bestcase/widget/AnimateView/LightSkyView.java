package edu.tjrac.swant.bestcase.widget.AnimateView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Timer;

import edu.tjrac.swant.bestcase.R;

/**
 * Created by wpc on 2017/4/20.
 */

public class LightSkyView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private Thread t;
    private boolean isRunning;

    public LightSkyView(Context context) {
        this(context, null);
    }

    public LightSkyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LightSkyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mHolder = getHolder();
        mHolder.addCallback(this);
        //可获得焦点
        setFocusable(true);
        setFocusableInTouchMode(true);
        setKeepScreenOn(true);

        t = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LightSkyView, defStyleAttr, 0);
        int n = a.getIndexCount();
        for (int i = 0; i < n; i++) {
            int attr = a.getIndex(i);
            if (attr == R.styleable.LightSkyView_auto_play) {
            } else if (attr == R.styleable.LightSkyView_skyColor) {
            } else if (attr == R.styleable.LightSkyView_meteor_color) {
            } else if (attr == R.styleable.LightSkyView_star_color) {
            } else if (attr == R.styleable.LightSkyView_meteorAngle) {
            } else if (attr == R.styleable.LightSkyView_meteorSize) {
            } else if (attr == R.styleable.LightSkyView_maxStartSize) {
            } else if (attr == R.styleable.LightSkyView_minStartSize) {
            }
        }
    }

    Timer mTimer;
    boolean auto_refesh;

    int width, hight;
    int sky_color = Color.BLACK, meteor_color = Color.WHITE, star_color = Color.WHITE;

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunning = true;

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    long start = System.currentTimeMillis();
                    draw();
                    long end = System.currentTimeMillis();
                    if (end - start < 50) {//1秒20
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

    private void draw() {
        try {
            mCanvas = mHolder.lockCanvas();
            if (mCanvas != null) {
                drawBg();
            }
        } catch (Exception e) {

        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }

        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }


    private void drawBg() {

    }

}

