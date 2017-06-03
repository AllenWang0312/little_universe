package edu.tjrac.swant.bestcase.moudle.laboratory.diy_views;//package edu.tjrac.swant.bestcase.moudle.main.content;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tjrac.swant.bestcase.R;
import edu.tjrac.swant.bestcase.widget.clock.MiClockView;

/**
 * Created by wpc on 2017/4/21.
 */

public class ClockFragment extends Fragment {

    MiClockView clock;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_clock, container, false);
        return v;
    }
}
