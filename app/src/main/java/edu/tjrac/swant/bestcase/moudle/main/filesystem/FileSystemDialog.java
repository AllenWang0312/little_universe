package edu.tjrac.swant.bestcase.moudle.main.filesystem;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.tjrac.swant.bestcase.R;
import edu.tjrac.swant.bestcase.common.adapter.ViewPagerAdapter;

/**
 * Created by Administrator on 2017/10/15 0015.
 */

public class FileSystemDialog extends DialogFragment {

    TabLayout tab;
    ViewPager vp;

    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.file_dialog,container,false);
        tab = v.findViewById(R.id.tab_file_system);
        vp = v.findViewById(R.id.vp_file_system);
        ViewPagerAdapter adapter =new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new TreeViewFragment(),"ed" );
//adapter.addFragment(new );
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
        return v;
    }
}
