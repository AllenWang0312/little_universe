package edu.tjrac.swant.bestcase.moudle.laboratory.diy_views

//package edu.tjrac.swant.bestcase.moudle;

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar


import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.base.BaseActivity
import edu.tjrac.swant.bestcase.common.adapter.ViewPagerAdapter
import kotterknife.bindView

class DiyViewsActivity : BaseActivity() {

    val mToolbar: Toolbar by bindView(R.id.toolbar)
    val mTlDiy: TabLayout by bindView(R.id.tl_diy)
    val mVpDiy: ViewPager by bindView(R.id.vp_diy)

    var danmu: DanMuFragment? = null
    var clock: ClockFragment? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diy_views)
        enableBackPress(mToolbar, null)

        val adapter = ViewPagerAdapter(supportFragmentManager)

        danmu = DanMuFragment()
        clock = ClockFragment()
        adapter.addFragment(danmu, "danmu")
        adapter.addFragment(clock, "clock")

        mVpDiy.adapter = adapter
        mTlDiy.setupWithViewPager(mVpDiy)
    }


}
