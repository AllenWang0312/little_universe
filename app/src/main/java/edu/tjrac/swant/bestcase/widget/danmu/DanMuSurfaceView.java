package edu.tjrac.swant.bestcase.widget.danmu;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * Created by wpc on 2017/4/21.
 */

public class DanMuSurfaceView extends SurfaceView implements SurfaceHolder.Callback {


    private Context mContext;
    private SurfaceHolder mHolder;
    private Canvas mCanvas;
    private Thread t;
    private boolean isRunning;


    public DanMuSurfaceView(Context context) {
        this(context, null);
    }

    public DanMuSurfaceView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DanMuSurfaceView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mHolder = this.getHolder();
        mHolder.addCallback(this);
    }


    ArrayList<DanMu> childs;
    int width, height;

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        isRunning = true;
        Canvas canvas = holder.lockCanvas();
        width = canvas.getWidth();
        height = canvas.getHeight();

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                long start = System.currentTimeMillis();
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
                for (DanMu danMu : childs) {
                  DanMuView danMuView=new DanMuView(mContext,danMu);

                }
            }
        } catch (Exception e) {

        } finally {
            if (mCanvas != null) {
                mHolder.unlockCanvasAndPost(mCanvas);
            }

        }
    }

    void addDanMu(DanMu danMu) {
        if (childs == null) {
            childs = new ArrayList<>();
        }
        childs.add(danMu);
    }

    void removeDanMu(DanMu danMu) {
        if (childs != null && childs.size() > 0) {
            if (childs.contains(danMu)) {
                childs.remove(danMu);
            }
        }
    }

}
