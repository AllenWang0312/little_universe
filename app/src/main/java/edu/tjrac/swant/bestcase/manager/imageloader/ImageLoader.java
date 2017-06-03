package edu.tjrac.swant.bestcase.manager.imageloader;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by wpc on 2017/3/14.
 */

public interface ImageLoader {
    void init(Context context);
    void displayImage(String imageUrl, ImageView imageView, int defaultImage);
    void displayImage(int imagId, ImageView imageView, int defaultImage);
}
