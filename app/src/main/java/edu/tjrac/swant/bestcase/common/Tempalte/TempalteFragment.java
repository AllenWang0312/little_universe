package edu.tjrac.swant.bestcase.common.Tempalte;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tjrac.swant.bestcase.R;

/**
 * Created by wpc on 2017/4/21.
 */

public class TempalteFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_tempalte,container,false);

        return v;
    }
}
