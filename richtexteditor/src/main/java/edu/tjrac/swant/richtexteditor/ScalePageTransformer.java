package edu.tjrac.swant.richtexteditor;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by Administrator on 2017/10/14 0014.
 */

public class ScalePageTransformer implements ViewPager.PageTransformer {

    public static final float MAX_SCALE = 1.1f;
    public static final float MIN_SCALE = 0.9f;

    @Override
    public void transformPage(View page, float position) {

        int width =page.getWidth();

        if (position < -1) {
            position = -1;
        } else if (position > 1) {
            position = 1;
        }

        float tempScale = position < 0 ? 1 + position : 1 - position;

        float slope = (MAX_SCALE - MIN_SCALE) / 1;
        //一个公式
        float scaleValue = MIN_SCALE + tempScale * slope;
        page.setScaleX(scaleValue);
        page.setScaleY(scaleValue);

    }
}