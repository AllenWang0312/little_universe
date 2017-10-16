package edu.tjrac.swant.bestcase.moudle.main.filesystem;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.util.HashMap;

import edu.tjrac.swant.bestcase.R;
import edu.tjrac.swant.bestcase.been.interfaze.OnItemClickListener;
import edu.tjrac.swant.bestcase.util.swant.FileUtils;

/**
 * Created by Administrator on 2017/10/15 0015.
 */

public class TreeViewFragment extends Fragment {

    RecyclerView mRecyclerView;
    FileSystemRecycAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.recycle, container, false);
        mRecyclerView = (RecyclerView) v;
        refeshRecyc(null);
        return v;
    }

    private OnItemClickListener itemclick = new OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            File file = adapter.getFileByViewPosition(position);
            if (file.isDirectory()) {
                if (adapter.fileIsOpen(file)) {
                    adapter.open_state.put(file.getAbsolutePath(), false);
                } else {
                    adapter.open_state.put(file.getAbsolutePath(), true);
                }
            }
//            mRecyclerView.invalidate();
            refeshRecyc(adapter.open_state);
//               adapter.notifyDataSetChanged();
            com.orhanobut.logger.Logger.d(adapter.open_state);

        }
    };

    private void refeshRecyc(HashMap<String, Boolean> openstate) {
        File file = new File(FileUtils.SD_ed);
        adapter = new FileSystemRecycAdapter(file, getActivity(), openstate);
        adapter.setItemclick(itemclick);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter);
    }
}
