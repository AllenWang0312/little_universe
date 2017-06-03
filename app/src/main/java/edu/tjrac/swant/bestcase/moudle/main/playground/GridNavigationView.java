package edu.tjrac.swant.bestcase.moudle.main.playground;

import android.content.Context;
import android.support.design.internal.ScrimInsetsFrameLayout;
import android.support.design.widget.NavigationView;
import android.util.AttributeSet;
import android.view.MenuInflater;

/**
 * Created by wpc on 2017/5/26.
 */

public class GridNavigationView extends ScrimInsetsFrameLayout{
    NavigationView.OnNavigationItemSelectedListener mListener;
    private int mMaxWidth;
    private MenuInflater mMenuInflater;

    public GridNavigationView(Context context) {
        this(context,null);
    }

    public GridNavigationView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public GridNavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
}
