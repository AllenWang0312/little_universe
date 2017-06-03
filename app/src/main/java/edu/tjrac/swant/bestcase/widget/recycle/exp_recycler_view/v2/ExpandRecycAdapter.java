package edu.tjrac.swant.bestcase.widget.recycle.exp_recycler_view.v2;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by wpc on 2017/4/15.
 */

public class ExpandRecycAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<ItemData> mItemDatas;

    public ExpandRecycAdapter(ArrayList<ItemData> itemDatas) {
        mItemDatas = itemDatas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mItemDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mItemDatas.get(position).getType();
    }
}
