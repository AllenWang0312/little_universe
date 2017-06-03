package edu.tjrac.swant.bestcase.manager.backgroundcreater;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;


/**
 * Created by wpc on 2017/3/1.
 */

public class RandomBgCreater implements BackgroundCreater {

    public int defaultcolors[] = {0xf44336, 0xe91e63, 0x9c27b0, 0x673ab7, 0x3f51b5, 0x2196f3, 0x00bcd4, 0x009688, 0x4caf50, 0xffeb3b, 0xffc107, 0xff9800};
    Integer strokeColor;
    Integer strokeWidth;

    Integer radius;

    boolean random;
    boolean gradually;

    public RandomBgCreater(@Nullable int colors[], boolean random, boolean gradually, Integer r) {
        if (colors != null) {
            this.defaultcolors = colors;
        }
        this.random = random;
        this.gradually = gradually;
        if (r != null) {
            radius = r;
        }
    }
    public RandomBgCreater setRadius(int radius) {
        this.radius = radius;
        return this;
    }

    public RandomBgCreater setStroke(int strokeColor, int strokeWidth) {
        this.strokeColor = strokeColor;
        this.strokeWidth = strokeWidth;
        return this;
    }


    @Override
    public Drawable getBackground(int position, float present) {
        GradientDrawable gd;
        int c = 0;
        if (random) {
            c = defaultcolors[(int) (Math.random() * 12)];
        } else {
            c = defaultcolors[position];
        }
        if (gradually) {
            int cs[] = {getLightColor(c, present), getDarkColor(c, present)};
            gd = new GradientDrawable(GradientDrawable.Orientation.BL_TR, cs);
        } else {
            gd = new GradientDrawable();
            gd.setColor(c);
        }
//        int strokeColor = Color.parseColor("#2E3135");//边框颜色
        if (radius != null) {
            gd.setCornerRadius(radius);
        }
        if (strokeColor != null) {
            gd.setStroke(strokeWidth, strokeColor);
        }
        return gd;
    }


    public static int getLightColor(int color, float persent) {
//        int pa = Color.alpha(color);
        int pr = Color.red(color);
        int pg = Color.green(color);
        int pb = Color.blue(color);
//        int alpha = (int) (pa + (0xff - pa) * persent);
        int red = (int) (pr + (0xff - pr) * persent);
        int green = (int) (pg + (0xff - pg) * persent);
        int blue = (int) (pb + (0xff - pb) * persent);
        return Color.rgb(red, green, blue);
    }

    public static int getDarkColor(int color, float persent) {
//        int pa = Color.alpha(color);
        int pr = Color.red(color);
        int pg = Color.green(color);
        int pb = Color.blue(color);

//        int alpha = (int) (pa * (1 - persent));
        int red = (int) (pr * (1 - persent));
        int green = (int) (pg * (1 - persent));
        int blue = (int) (pb * (1 - persent));
        return Color.rgb(red, green, blue);
    }
}
