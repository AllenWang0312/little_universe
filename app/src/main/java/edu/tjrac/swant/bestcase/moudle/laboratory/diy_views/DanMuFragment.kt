package edu.tjrac.swant.bestcase.moudle.laboratory.diy_views

//package edu.tjrac.swant.bestcase.moudle.main.content;//package edu.tjrac.swant.bestcase.moudle;

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.bindView

import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.widget.danmu.DanMuSurfaceView

/**
 * Created by wpc on 2017/4/15.
 */

class DanMuFragment : Fragment() {

   val mDmlPageMain: DanMuSurfaceView? by bindView(R.id.dml_page_main);

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_danmu_main, container, false)
        return v
    }

}
