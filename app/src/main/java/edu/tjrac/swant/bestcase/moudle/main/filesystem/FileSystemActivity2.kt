package edu.tjrac.swant.bestcase.moudle.main.filesystem

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.design.widget.Snackbar
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.been.interfaze.OnItemClickListener
import edu.tjrac.swant.bestcase.util.swant.FileUtils
import kotlinx.android.synthetic.main.activity_file_system2.*
import kotlinx.android.synthetic.main.app_bar_file_system2.*
import kotterknife.bindView
import java.io.File
import java.util.*

class FileSystemActivity2 : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val mNavView: NavigationView by bindView(R.id.nav_view)
    val mDrawerLayout: DrawerLayout by bindView(R.id.drawer_layout)

    internal var mNavHeadView: View? = null
    internal var NavRecyc: RecyclerView? = null

    internal var adapter: FileSystemRecycAdapter? = null
    private val itemclick = OnItemClickListener { view, position ->
        val file = adapter?.getFileByViewPosition(position)
        if (file!!.isDirectory) {
            if (adapter!!.fileIsOpen(file)) {
                adapter?.open_state?.put(file?.absolutePath, false)
            } else {
                adapter?.open_state?.put(file?.absolutePath, true)
            }
        }
        //            mRecyclerView.invalidate();
        refeshRecyc(adapter?.open_state)
        //               adapter.notifyDataSetChanged();
        com.orhanobut.logger.Logger.d(adapter?.open_state)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_system2)
        setSupportActionBar(toolbar)

        mNavHeadView = mNavView.getHeaderView(0)
        NavRecyc = mNavHeadView?.findViewById(R.id.recyclerview)

        refeshRecyc(null)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    private fun refeshRecyc(openstate: HashMap<String, Boolean>?) {
        val file = File(FileUtils.SD_local)
        adapter = FileSystemRecycAdapter(file, this, openstate)
        adapter?.setItemclick(itemclick)
        NavRecyc?.setLayoutManager(LinearLayoutManager(this))
        NavRecyc?.setAdapter(adapter)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.file_system_activity2, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
            R.id.nav_manage -> {

            }
            R.id.nav_share -> {

            }
            R.id.nav_send -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
