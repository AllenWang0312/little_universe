package edu.tjrac.swant.bestcase.moudle.main

//import edu.tjrac.swant.bestcase.manager.db.DBHelper
import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.view.GravityCompat
import android.support.v4.view.ViewPager
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.tbruyelle.rxpermissions.RxPermissions
import de.hdodenhof.circleimageview.CircleImageView
import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.base.ActionFragment
import edu.tjrac.swant.bestcase.base.BaseActivity
import edu.tjrac.swant.bestcase.been.User
import edu.tjrac.swant.bestcase.common.adapter.ViewPagerAdapter
import edu.tjrac.swant.bestcase.manager.backgroundcreater.RandomBgCreater
import edu.tjrac.swant.bestcase.manager.db.DBManager
import edu.tjrac.swant.bestcase.moudle.MainSeachActivity
import edu.tjrac.swant.bestcase.moudle.laboratory.HorizontalScrollViewTestActivity
import edu.tjrac.swant.bestcase.moudle.laboratory.diy_views.DiyViewsActivity
import edu.tjrac.swant.bestcase.moudle.laboratory.sensor.SensorActivity
import edu.tjrac.swant.bestcase.moudle.main.alarm.AlarmActivity
import edu.tjrac.swant.bestcase.moudle.main.contacts.ContactsFragment
import edu.tjrac.swant.bestcase.moudle.main.filesystem.FileSystemDialog
import edu.tjrac.swant.bestcase.moudle.main.gallery.GalleryActivity
import edu.tjrac.swant.bestcase.moudle.main.map.MapActivity
import edu.tjrac.swant.bestcase.moudle.main.playground.RecordFragment
import edu.tjrac.swant.bestcase.moudle.main.project.ProjectFragment
import edu.tjrac.swant.bestcase.moudle.service.ClipboardTextWatcherService
import edu.tjrac.swant.bestcase.moudle.settings.SettingsActivity
import edu.tjrac.swant.bestcase.moudle.user_center.UserCenterActivity
import edu.tjrac.swant.bestcase.util.swant.FileUtils
import edu.tjrac.swant.bestcase.util.swant.UiUtils
import kotterknife.bindView
import java.io.File

class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    val mToolbar: Toolbar by bindView(R.id.toolbar)
    val mNavView: NavigationView by bindView(R.id.nav_view)
    val mDrawerLayout: DrawerLayout by bindView(R.id.drawer_layout)
    val mTlMain: TabLayout by bindView(R.id.tl_main)
    val mVpMain: ViewPager by bindView(R.id.vp_main)


    val fab: FloatingActionButton by bindView(R.id.fab)
    val hro: HorizontalScrollView by bindView(R.id.hsv_record_tag)
    val rg: RadioGroup by bindView(R.id.rg_record_check)


     var mNavHeadView: View? = null
     var city: TextView? = null
     var portrait: CircleImageView? = null
     var name: TextView? = null
     var Email: TextView? = null
     var folder: ImageView? = null
var address: ImageView?=null
    var sp: SharedPreferences? = null
    var rxPermissions: RxPermissions? = null

    var record: RecordFragment? = null
    var workgroup: ProjectFragment? = null
    var contacts: ContactsFragment? = null

    @SuppressLint("ServiceCast", "MissingPermission")
//    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startService(Intent(this,ClipboardTextWatcherService ::class.java))


        sp = getSharedPreferences("app_config", Context.MODE_PRIVATE)
        if (sp!!.getBoolean("isFirstLoad", true)) {

            sp!!.edit().putBoolean("isFirstLoad", false)
                    .putBoolean("学习", true)
                    .putBoolean("工作", false)
                    .putBoolean("生活", false)
                    .putBoolean("健康", false)
                    .commit()

            DBManager.getInstance(this)!!.insertUser(User("localuser"))

        }

        rxPermissions = RxPermissions(this)
        rxPermissions?.request(Manifest.permission.ACCESS_FINE_LOCATION)?.subscribe { granted ->
            if (granted!!) {

            } else {
            }
        }

        mNavHeadView = mNavView.getHeaderView(0)
        val randomBgCreater = RandomBgCreater(null, true, true, null)
        mNavHeadView!!.background = randomBgCreater.getBackground(0, 0.3f)

        city = mNavHeadView!!.findViewById(R.id.tv_location_nav)
        portrait = mNavHeadView!!.findViewById(R.id.portrait_nav_head)
        portrait!!.setOnClickListener(this)
        name = mNavHeadView!!.findViewById(R.id.tv_name_nav_head)
        Email = mNavHeadView!!.findViewById(R.id.tv_address_nav_head)


        folder = mNavHeadView!!.findViewById(R.id.iv_folder)
        folder?.setOnClickListener(View.OnClickListener {
        FileSystemDialog().show(supportFragmentManager, "filesystem")
        })

        address=mNavHeadView!!.findViewById(R.id.iv_address)

//        AddressManagerDialog()
        setSupportActionBar(mToolbar)
        val toggle = ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        mDrawerLayout.setDrawerListener(toggle)
        toggle.syncState()
        mNavView.setNavigationItemSelectedListener(this)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        var file = File(FileUtils.SD_local_record)

        var chars = file.list()
        for (char in chars) {
            if (sp!!.getBoolean(char, false)) {
                record = RecordFragment(char)
                adapter.addFragment(record, "记录")
                break
            }
        }
        workgroup = ProjectFragment()
        contacts = ContactsFragment()
        adapter.addFragment(workgroup, "工作組")
//        adapter.addFragment(PlaygroundFragment(), "广场")
        adapter.addFragment(contacts, "联系人")

        mVpMain.adapter = adapter
        mTlMain.setupWithViewPager(mVpMain)
        mVpMain.offscreenPageLimit = 3

        //listener
        city?.setOnClickListener { startActivity(Intent(this, MapActivity::class.java)) }
        fab.setOnClickListener({
            var frg = adapter.getItem(mVpMain.currentItem) as ActionFragment
            frg.fabClickListener.onClick(fab)
        })

        mVpMain.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (position == 0 && hro.x != 0f) {
                    hro.animate().translationX(0f)
                } else {
                    hro.animate().translationX(-UiUtils.getScreenWidth(this@MainActivity).toFloat())
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        rg.setOnCheckedChangeListener { radioGroup, i ->
            record?.setTag(findViewById<RadioButton>(i).text.toString())
        }

    }

    @SuppressLint("MissingPermission")
    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.action_seach) {
            startActivity(Intent(this, MainSeachActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when (id) {
            R.id.nav_alarm -> startActivity(Intent(this, AlarmActivity::class.java))
//            R.id.nav_line -> startActivity(Intent(this, LinesActivity::class.java))
//            R.id.nav_gank -> startActivity(Intent(this, GankActivity::class.java))

            R.id.nav_setting -> startActivity(Intent(this, SettingsActivity::class.java))
//            R.id.nav_camera -> startActivity(Intent(this, CameraActivity::class.java))
//            R.id.nav_camera -> startActivity(Intent(this, Camera2Activity::class.java))

            R.id.nav_gallery -> startActivity(Intent(this, GalleryActivity::class.java))
//            R.id.nav_manage -> {
//                try {
//                    intent = packageManager.getLaunchIntentForPackage("color.measurement.com.from_cp20")
//                    startActivity(intent)
//                } catch (e: Exception) {
//                    var i = Intent("android.intent.action.VIEW", Uri.parse("http://weixin.qq.com/"))
//                    startActivity(i)
//                    e.printStackTrace()
//                }
//
//            }

            R.id.nav_ver -> startActivity(Intent(this, HorizontalScrollViewTestActivity::class.java))
            R.id.nav_diy -> startActivity(Intent(this, DiyViewsActivity::class.java))
            R.id.nav_sensor -> startActivity(Intent(this, SensorActivity::class.java))

        }
//        val drawer = findViewById(R.id.drawer_layout) as DrawerLayout
        mDrawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.portrait_nav_head -> startActivity(Intent(this@MainActivity, UserCenterActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == 1) {
            record?.refesh()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
