package edu.tjrac.swant.bestcase.widget.ExpandRecyclerView.abs;

import java.util.ArrayList;
import java.util.List;

import edu.tjrac.swant.bestcase.widget.ExpandRecyclerView.TreeRecyclerViewType;
import edu.tjrac.swant.bestcase.widget.ExpandRecyclerView.interfaze.Expandable;
import edu.tjrac.swant.bestcase.widget.ExpandRecyclerView.interfaze.Multiple;

/**
 * Created by wpc on 2017/4/15.
 */

public abstract class TreeParentItem<D extends Multiple> extends TreeItem<D> implements Expandable {

    protected List<TreeItem> childs;
    protected boolean isExpanding;
    protected boolean isExpandable = true;


    public TreeParentItem() {

    }

    public TreeParentItem(TreeParentItem parentItem) {
        super(parentItem);
    }

    public boolean isExpanding() {
        return isExpanding;
    }

    public void setExpand(boolean expand) {
        if (isExpandable) {
            isExpanding = expand;
        }
    }

    @Override
    public void setData(D data) {
        super.setData(data);
        List<TreeItem> treeItems = initChildsList(data);
        childs = treeItems == null ? new ArrayList<TreeItem>() : treeItems;
    }

    /**
     * 展开
     */
    @Override
    public void onExpand() {

    }

    /**
     * 折叠
     */
    @Override
    public void onCollapse() {

    }

    @Override
    public List<TreeItem> getChilds() {
        return childs;
    }

    public List<TreeItem> getChilds(TreeRecyclerViewType treeRecyclerViewType) {
        ArrayList<TreeItem> treeItems = new ArrayList<>();
        for (int i = 0; i < childs.size(); i++) {
            TreeItem treeItem = childs.get(i);//下级
            treeItems.add(treeItem);//直接add
            if (treeItem instanceof TreeParentItem && ((TreeParentItem) treeItem).isExpanding()) {//判断是否还有下下级,并且处于expand的状态
                List list = ((TreeParentItem) treeItem).getChilds();//调用下级的getAllChilds遍历,相当于递归遍历
                if (list != null && list.size() > 0) {
                    treeItems.addAll(list);
                }
                if (treeRecyclerViewType == TreeRecyclerViewType.SHOW_COLLAPSE_CHILDS) {
                    ((TreeParentItem) treeItem).setExpand(false);
                    ((TreeParentItem) treeItem).onCollapse();
                }
            }
        }
        return treeItems;
    }

    public boolean isCanChangeExpand() {
        return isExpandable;
    }

    public void setCanChangeExpand(boolean canChangeExpand, boolean defualtExpand) {
        isExpandable = canChangeExpand;
        this.isExpanding = defualtExpand;
    }

    /**
     * 初始化子数据
     *
     * @param data 父级数据
     * @return 得到处理好的子集
     */
    protected abstract List<TreeItem> initChildsList(D data);

    /**
     * 当子类发现变化时,父一级是否需要处理
     */
    public void onUpdate() {

    }

}
