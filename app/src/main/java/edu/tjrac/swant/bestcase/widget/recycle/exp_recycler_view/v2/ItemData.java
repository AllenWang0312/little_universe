package edu.tjrac.swant.bestcase.widget.recycle.exp_recycler_view.v2;

/**
 * Created by wpc on 2017/4/15.
 */

public abstract class ItemData<T> implements Multiple, Showable, Layoutable {

    public static final int TYPE_PARENT = 0;
    public static final int TYPE_CHILD = 1;

    T data;
    boolean isShowing;
    int type;
    int layoutId;

    public ItemData(T data, int type) {
        this.data = data;
        this.type = type;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public boolean isShowing() {
        return isShowing;
    }

    @Override
    public int getLayoutId() {
        return layoutId;
    }
}
