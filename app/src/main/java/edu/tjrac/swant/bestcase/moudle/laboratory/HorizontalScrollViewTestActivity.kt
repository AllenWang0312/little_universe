package edu.tjrac.swant.bestcase.moudle.laboratory

import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import butterknife.bindView
import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager
import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.common.ImageViewPagerAdapter
import edu.tjrac.swant.bestcase.common.ShowAbleItem
import edu.tjrac.swant.bestcase.common.TestItemCreater
import edu.tjrac.swant.bestcase.widget.ViewPagerIndicater
import java.util.*

//import edu.tjrac.swant.bestcase.widget.ViewPagerIndicater.ViewPagerIndicater;

class HorizontalScrollViewTestActivity : AppCompatActivity() {

    val hor_cycle_vp: HorizontalInfiniteCycleViewPager by bindView(R.id.hor_cycle_vp)
    val mVpHorTest: ViewPager by bindView(R.id.vp_hor_test)
    val mVpiHorTest: ViewPagerIndicater by bindView(R.id.vpi_hor_test)
    internal var datas: ArrayList<ShowAbleItem>? = null
    val mTlHorTest: TabLayout by bindView(R.id.tl_hor_test)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_scroll_view_test)
        datas = ArrayList<ShowAbleItem>()
        datas!!.add(TestItemCreater("white", Color.WHITE))
        datas!!.add(TestItemCreater("green", Color.GREEN))
        datas!!.add(TestItemCreater("blue", Color.BLUE))
        val adapter = ImageViewPagerAdapter(this, datas)
        mVpHorTest.adapter = adapter
        mVpiHorTest.setUpWithViewPager(mVpHorTest)
        mTlHorTest.setupWithViewPager(mVpHorTest)
        hor_cycle_vp.adapter=adapter

    }
}
