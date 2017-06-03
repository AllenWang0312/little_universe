package edu.tjrac.swant.bestcase.moudle.main.gank

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import butterknife.bindView
import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.common.adapter.ViewPagerAdapter

class GankActivity : AppCompatActivity() {

    var pagerAdapter : ViewPagerAdapter?=null
    val mViewPager: ViewPager by bindView(R.id.container)
    val tab: TabLayout by bindView(R.id.tab_gank)
    val toolbar :Toolbar by bindView(R.id.toolbar)
    val fab :FloatingActionButton by bindView(R.id.fab)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gank)
        setSupportActionBar(toolbar)
        pagerAdapter= ViewPagerAdapter(supportFragmentManager)
        var meizi : MeiziFragment = MeiziFragment()

        pagerAdapter!!.addFragment(meizi,"meizi1")
        pagerAdapter!!.addFragment(meizi,"meizi2")
        pagerAdapter!!.addFragment(meizi,"meizi3")

        mViewPager.adapter=pagerAdapter
        tab.setupWithViewPager(mViewPager)
        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_gank, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_settings) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
