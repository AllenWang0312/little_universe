package edu.tjrac.swant.bestcase.moudle.laboratory.sensor

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import butterknife.bindView

import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.base.BaseActivity
import edu.tjrac.swant.bestcase.common.adapter.ViewPagerAdapter

/**
 * Created by wpc on 2017/4/22.
 */

class SensorActivity : BaseActivity() {

    val mToolbar: Toolbar by bindView(R.id.toolbar)
    val mTlSensor: TabLayout by bindView(R.id.tl_sensor)
    val mVpSensor: ViewPager by bindView(R.id.vp_sensor)

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sensor)
        enableBackPress(mToolbar, null)
        val adapter = ViewPagerAdapter(supportFragmentManager)
        //        adapter.addFragment(new DanMuFragment(), "danmu");
        //        adapter.addFragment(new ClockFragment(), "clock");
        adapter.addFragment(GyroscopeFragment(), "gyroscope")
        mVpSensor.adapter = adapter
        mTlSensor.setupWithViewPager(mVpSensor)
    }

}
