package edu.tjrac.swant.bestcase.widget.ExpandRecyclerView.abs;

import android.content.res.Resources;

import edu.tjrac.swant.bestcase.widget.ExpandRecyclerView.ViewHolder;
import edu.tjrac.swant.bestcase.widget.ExpandRecyclerView.interfaze.Multiple;
import edu.tjrac.swant.bestcase.widget.ExpandRecyclerView.interfaze.OnClickChangeListener;

/**
 * Created by wpc on 2017/4/15.
 */

public abstract class TreeItem<D extends Multiple> {

    protected TreeParentItem parentItem;
    protected D data;
    protected int layoutId;
    protected int spanSize;

    protected OnClickChangeListener mOnClickChangeListener;

    public TreeItem() {

    }

    public TreeItem(TreeParentItem parentItem) {
        this.parentItem = parentItem;
    }

    public int getLayoutId() {
        layoutId = initLayoutId();
        if (layoutId == 0) {
            throw new Resources.NotFoundException("请设置布局Id");
        }
        return layoutId;
    }

    protected abstract int initLayoutId();
    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public void setParentItem(TreeParentItem parentItem) {
        this.parentItem = parentItem;
    }

    public OnClickChangeListener getOnClickChangeListener() {
        return mOnClickChangeListener;
    }

    protected int initSpansize() {
        return 0;
    }


    public abstract void onBindViewHolder(ViewHolder holder);

    public void onClickChange(TreeItem treeItem) {
        if (mOnClickChangeListener != null) {
            mOnClickChangeListener.onClickChange(treeItem);
        }
    }


    public void setOnClickChangeListener(OnClickChangeListener onClickChangeListener) {
        mOnClickChangeListener = onClickChangeListener;
    }

    public TreeParentItem getParentItem() {
        return parentItem;
    }
}
