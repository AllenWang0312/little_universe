package edu.tjrac.swant.bestcase.widget.recycle.exp_recycler_view.v1;

import java.util.ArrayList;

/**
 * Created by wpc on 2017/4/15.
 */

public abstract class ExpandGroupData<T extends Child> {
    boolean expanded;

    abstract ArrayList<T> getChilds();

    abstract boolean isExpand();
}
