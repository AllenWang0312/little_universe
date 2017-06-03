package edu.tjrac.swant.bestcase.moudle.main;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.View;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Created by wpc on 2017/6/2.
 */

public class FloatActBtnGroup {
    Context mContext;
    boolean showChildNames = true;
    CoordinatorLayout mViewGroup;
    FloatingActionButton parent;

    ArrayList<FloatingActionButton> childs;

    float x, y;

    FloatActBtnGroup(FloatingActionButton parent, Context context) {
        this.parent = parent;
        x = parent.getX();
        y = parent.getY();
        mViewGroup = (CoordinatorLayout) parent.getParent();
        mContext = context;
    }

    public void addChild(@NotNull String s, @NotNull int id, @NotNull View.OnClickListener onClickListener) {
        FloatingActionButton child = new FloatingActionButton(mContext);
        child.setImageResource(id);
        child.setOnClickListener(onClickListener);
        childs.add(child);
        mViewGroup.addView(child);
    }
}
