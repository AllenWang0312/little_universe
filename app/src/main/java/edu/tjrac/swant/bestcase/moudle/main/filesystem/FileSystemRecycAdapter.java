package edu.tjrac.swant.bestcase.moudle.main.filesystem;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.HashMap;

import edu.tjrac.swant.bestcase.R;
import edu.tjrac.swant.bestcase.been.interfaze.OnItemClickListener;

/**
 * Created by Administrator on 2017/10/15 0015.
 */

public class FileSystemRecycAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    File file;
    Context mContext;

    HashMap<String, Boolean> open_state = new HashMap<>();
    private OnItemClickListener itemclick;

    public void setItemclick(OnItemClickListener itemclick) {
        this.itemclick = itemclick;
    }

   public FileSystemRecycAdapter(File file, Context context, HashMap<String, Boolean> open_state) {
        this.file = file;
        this.mContext = context;
        if (open_state == null) {
            file_count = getInnerFileCount(file);
        } else {
            this.open_state = open_state;
        }
    }

    int file_count = 1;
    int view_count;

    int getInnerFileCount(File file) {

        if (!file.isDirectory()) {
            return 1;
        }
        open_state.put(file.getAbsolutePath(), false);
        int i = 0;
        String[] childs = file.list();
        if (childs.length > 0) {
            i += childs.length;
            for (String child : childs) {
                i += getInnerFileCount(new File(file, child));
            }
        } else {
            i += 1;
        }

        return i;
    }

    boolean fileIsOpen(File file) {
        return open_state.get(file.getAbsolutePath());
    }

    int getViewCount() {
        view_count = 0;
        return getViewCount(file, open_state.get(file.getAbsolutePath()));
    }

    int getViewCount(File dirfile, Boolean open) {
        view_count += 1;
        if (open) {
            String[] childs = dirfile.list();
            for (String child : childs) {
                File f = new File(dirfile, child);
                if (f.isDirectory()) {
                    getViewCount(f, open_state.get(f.getAbsolutePath()));
                }
            }
        }
//        Log.i("view_count",view_count+"");
        return view_count;
    }

    File getFileByViewPosition(int position) {
        if (position == 0) {
            return file;
        } else {
            return getNext(file, position);
        }
    }

    File getNext(File file, int count) {
        if (count == 0) {
//            Log.i("getNext",file.getAbsolutePath());
            return file;
        }
        if (file.isDirectory()) {
            if (fileIsOpen(file)) {
                String[] childs = file.list();
                if (childs.length > 0) {
                    return getNext(new File(file, childs[0]), count - 1);
                }
            }
        }
        return getNextBrother(file, count);
    }

    private File getNextBrother(File file, int i) {
        if (i == 0) {
            return file;
        }
        File parent = file.getParentFile();
        String name = file.getName();
        String[] brothers = parent.list();
        int length = brothers.length;
//        Log.i("getNextBrother",file.getAbsolutePath()+"brothers"+length);


        if (!name.equals(brothers[length - 1])) {
            return getNext(new File(parent
                    , brothers[getIndex(name, brothers) + 1]), i - 1);
        }


        return getNextBrother(parent, i);

    }

    int getIndex(String name, String[] files) {
        for (int i = 0; i < files.length; i++) {
            if (files[i].equals(name)) {
                return i;
            }
        }
        return 0;
    }

    int getLevel(File item_file) {
        int root_length = file.getAbsolutePath().length();
        String xd = item_file.getAbsolutePath().substring(root_length);
        int cnt = 0;
        int offset = 0;
        while ((offset = xd.indexOf("/", offset)) != -1) {
            offset++;
            cnt++;
        }
        return cnt;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.iten_file, parent, false);
        return new FileViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        File file = getFileByViewPosition(position);
        int level = getLevel(file);
//        Log.i("fileinfo",file.getAbsolutePath()+"lv:"+level);

        FileViewHolder fvh = (FileViewHolder) holder;
        fvh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemclick.onItemClick(view, position);
            }
        });
        fvh.itemView.setTranslationX(level * 50);
        fvh.filename.setText(file.getName());
//        fvh.path.setText(file.getAbsolutePath());
//        fvh.time.setText(TimeUtils.getTimeWithFormat(new Date(file.lastModified()), TimeUtils.HOUR_MIN));
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return getViewCount();
    }

    class FileViewHolder extends RecyclerView.ViewHolder {
//        RelativeLayout rl;
        ImageView icon;
        TextView filename
//                , path, time
                ;


        public FileViewHolder(View itemView) {
            super(itemView);
//            this.rl = itemView.findViewById(R.id.rl);
            this.icon = itemView.findViewById(R.id.icon);
//            this.path = itemView.findViewById(R.id.abs_path);
//            this.time = itemView.findViewById(R.id.time);
            this.filename = itemView.findViewById(R.id.name);
        }
    }

    class DirViewHolder extends RecyclerView.ViewHolder {

        public DirViewHolder(View itemView) {
            super(itemView);
        }
    }
}
