package edu.tjrac.swant.bestcase.moudle.laboratory.diy_views

//package edu.tjrac.swant.bestcase.moudle;

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.widget.Toolbar
import butterknife.bindView

import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.base.BaseActivity
import edu.tjrac.swant.bestcase.common.adapter.ViewPagerAdapter

class DiyViewsActivity : BaseActivity() {

     val mToolbar: Toolbar by bindView(R.id.toolbar)
  val mTlDiy: TabLayout by bindView(R.id.tl_diy)
    val mVpDiy: ViewPager by bindView(R.id.vp_diy)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diy_views)
        enableBackPress(mToolbar, null)

        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(DanMuFragment(), "danmu")
        adapter.addFragment(ClockFragment(), "clock")

        mVpDiy.adapter = adapter
        mTlDiy.setupWithViewPager(mVpDiy)
    }


}
