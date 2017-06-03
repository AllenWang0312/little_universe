package edu.tjrac.swant.bestcase.widget.recycle.exp_recycler_view.v1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by wpc on 2017/3/24.
 */

public class ExpandRecycleViewAdapter<T extends ExpandGroupData> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    ArrayList<T> datas;

    ExpandRecycleViewAdapter(Context context, ArrayList<T> datas) {
        mContext = context;
        this.datas = datas;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        int count = 0;
        for (T t : datas) {
            count += 1;
            if (t.isExpand()) {
                count += t.getChilds().size();
            }
        }
        return count;
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
