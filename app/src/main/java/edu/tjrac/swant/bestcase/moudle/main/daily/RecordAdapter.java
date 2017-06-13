package edu.tjrac.swant.bestcase.moudle.main.daily;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.tjrac.swant.bestcase.R;
import edu.tjrac.swant.bestcase.been.interfaze.Typeable;

/**
 * Created by wpc on 2017/6/7.
 */

public class RecordAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    LayoutInflater mInflater;
    ArrayList<Typeable> items;

    public RecordAdapter(Context context, ArrayList<Typeable> items) {
        mContext = context;
        this.items = items;
        mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        switch (i) {
            case 0:
                return new TipsViewHolder(mInflater.inflate(R.layout.tips_item, null));
            case 1:
                return new NoticeViewHolder(mInflater.inflate(R.layout.notice_item, null));
            case 2:
                return new NoteViewHolder(mInflater.inflate(R.layout.note_item, null));
            case 3:
                return new DailyViewHolder(mInflater.inflate(R.layout.daily_recyc_item, null));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class TipsViewHolder extends RecyclerView.ViewHolder {

        public TipsViewHolder(View itemView) {
            super(itemView);
        }
    }

    class NoticeViewHolder extends RecyclerView.ViewHolder {

        public NoticeViewHolder(View itemView) {
            super(itemView);
        }
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        public NoteViewHolder(View itemView) {
            super(itemView);
        }
    }

    class DailyViewHolder extends RecyclerView.ViewHolder {

        public DailyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
