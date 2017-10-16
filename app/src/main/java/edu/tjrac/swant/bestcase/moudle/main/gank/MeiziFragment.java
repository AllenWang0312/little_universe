package edu.tjrac.swant.bestcase.moudle.main.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tjrac.swant.bestcase.R;

/**
 * Created by wpc on 2017/6/2.
 */

public class MeiziFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.meizi, container, false);
        return view;
    }
}
