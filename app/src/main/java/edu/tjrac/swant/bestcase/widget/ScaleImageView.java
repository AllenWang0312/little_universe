package edu.tjrac.swant.bestcase.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by wpc on 2017/6/13.
 */

public class ScaleImageView extends ImageView {

    public ScaleImageView(Context context) {
        super(context);
    }

    public ScaleImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScaleImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    int bitmap_W, bitmap_H, max_W, max_H, min_W, min_H;

    @Override
    public void setImageBitmap(Bitmap bm) {
        bitmap_W = bm.getWidth();
        bitmap_H = bm.getHeight();
        max_W = bitmap_W * 3;
        max_H = bitmap_H * 3;
        min_W = bitmap_W / 2;
        min_H = bitmap_H / 2;
    }
int start_Top=-1,start_Left,start_Bottom,start_Right;
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(start_Top==-1){
            start_Top=top;
            start_Left=left;
            start_Bottom=bottom;
            start_Right=right;

        }
    }
}
