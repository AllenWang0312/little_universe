package edu.tjrac.swant.bestcase.widget.ExpandRecyclerView;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.List;

import edu.tjrac.swant.bestcase.widget.ExpandRecyclerView.abs.TreeItem;

/**
 * Created by wpc on 2017/4/15.
 */

public class BaseExpandRecyclerAdapter <T extends TreeItem> extends RecyclerView.Adapter<ViewHolder> {
    private Context mContext;
    private List<T> mDatas;

    public BaseExpandRecyclerAdapter(Context context, List<T> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return ViewHolder.createViewHolder(mContext,parent,viewType);//viewType=layoutid
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        T t = mDatas.get(position);
        t.onBindViewHolder(holder);
    }

    @Override
    public int getItemViewType(int position) {
        return mDatas.get(position).getLayoutId();
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    TreeItem treeItem = mDatas.get(position);
                    if (treeItem.getSpanSize() == 0) {
                        return gridLayoutManager.getSpanCount();
                    }
                    return treeItem.getSpanSize();
                }
            });
        }
    }
    @Override
    public int getItemCount() {
        return mDatas.size();
    }
}
