package edu.tjrac.swant.bestcase.moudle.laboratory.diy_views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zph.glpanorama.GLPanorama;

import edu.tjrac.swant.bestcase.R;

/**
 * Created by Administrator on 2017/9/14 0014.
 */

public class View360Fragment extends Fragment {
    private GLPanorama mGLPanorama;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mGLPanorama = (GLPanorama) inflater.inflate(R.layout.fragment_360view, container, false);
//        mGLPanorama.setGLPanorama(R.);
        return mGLPanorama;
    }
}
