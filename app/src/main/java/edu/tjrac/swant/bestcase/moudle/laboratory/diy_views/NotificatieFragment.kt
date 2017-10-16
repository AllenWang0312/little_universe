package edu.tjrac.swant.bestcase.moudle.laboratory.diy_views

//package edu.tjrac.swant.bestcase.moudle.main.content;

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import edu.tjrac.swant.bestcase.R
import kotterknife.bindView

/**
 * Created by wpc on 2017/4/19.
 */

class NotificatieFragment : Fragment() {

     val mBtSnakeBar: Button by bindView(R.id.bt_snake_bar);


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_notificatie_main, container, false)

        return v
    }

}
