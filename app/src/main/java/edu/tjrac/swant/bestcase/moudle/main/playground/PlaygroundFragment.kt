package edu.tjrac.swant.bestcase.moudle.main.playground

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import edu.tjrac.swant.bestcase.R

/**
 * Created by wpc on 2017/4/21.
 */

class PlaygroundFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_playground, container, false)

        return v
    }
}
