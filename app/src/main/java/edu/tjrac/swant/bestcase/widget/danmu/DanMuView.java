package edu.tjrac.swant.bestcase.widget.danmu;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wpc on 2017/4/22.
 */

public class DanMuView extends View {

    DanMu content;

    public DanMuView(Context context, DanMu danMu) {
        this(context, danMu, null);
    }

    public DanMuView(Context context, DanMu danMu, @Nullable AttributeSet attrs) {
        this(context, danMu, attrs, 0);
    }

    public DanMuView(Context context, DanMu danMu, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        content = danMu;
    }
    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawText( content.getContent(),);

        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
