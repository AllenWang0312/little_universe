package edu.tjrac.swant.bestcase.widget.AnimateView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.View;

/**
 * Created by wpc on 2017/4/20.
 */

public class BackgroudHelper {
    View v;

    int height, width;
    Bitmap mBitmap;
    Canvas mCanvas;

    public void supportView(View v) {
        this.v = v;
        height = v.getHeight();
        width = v.getWidth();
        mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
        mCanvas = new Canvas(mBitmap);
    }

}
