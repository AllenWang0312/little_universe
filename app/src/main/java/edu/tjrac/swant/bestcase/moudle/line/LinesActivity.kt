package edu.tjrac.swant.bestcase.moudle.line

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import butterknife.bindView

import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem

import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.common.adapter.ViewPagerAdapter

class LinesActivity : AppCompatActivity() {

     val mToolbar: Toolbar by bindView(R.id.toolbar)
     val mVpLines: ViewPager by bindView(R.id.vp_lines)
     val mBvbHealth: BottomNavigationBar by bindView(R.id.bvb_health)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lines)
        setSupportActionBar(mToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(Health(), "health")
        mVpLines!!.adapter = adapter
        mBvbHealth!!.addItem(BottomNavigationItem(R.drawable.ic_favorite_white_24dp, "情感"))
                .addItem(BottomNavigationItem(R.drawable.ic_work_white_24dp, "工作"))
                .addItem(BottomNavigationItem(R.drawable.ic_directions_run_white_24dp, "健康"))
                .initialise()

    }
}
