package edu.tjrac.swant.bestcase.manager.backgroundcreater;

import android.graphics.drawable.Drawable;

/**
 * Created by wpc on 2017/3/1.
 */

public interface BackgroundCreater {
    Drawable getBackground(int position, float present);
}
