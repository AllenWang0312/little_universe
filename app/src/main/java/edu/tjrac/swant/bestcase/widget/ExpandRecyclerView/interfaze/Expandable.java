package edu.tjrac.swant.bestcase.widget.ExpandRecyclerView.interfaze;

import java.util.List;

import edu.tjrac.swant.bestcase.widget.ExpandRecyclerView.abs.TreeItem;

/**
 * Created by wpc on 2017/4/15.
 */

public interface Expandable {
    boolean canExpandOrCollapse();

    void onExpand();

    void onCollapse();

    List<TreeItem> getChilds();
}
