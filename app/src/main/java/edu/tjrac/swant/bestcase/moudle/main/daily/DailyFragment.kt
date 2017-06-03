package edu.tjrac.swant.bestcase.moudle.main.playground

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.bindView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.common.util.StringUtils
import edu.tjrac.swant.bestcase.been.Daily
import edu.tjrac.swant.bestcase.been.Tips
import edu.tjrac.swant.bestcase.manager.db.DBConsts
import edu.tjrac.swant.bestcase.moudle.main.daily.AddTipsDialog

/**
 * Created by wpc on 2017/4/21.
 */

class DailyFragment internal constructor(internal var db: SQLiteDatabase) : Fragment() {
    //    internal var headview: View? = null
    var emptyview: View? = null

    val add_tips: ImageView by bindView(R.id.iv_tips_add)
    val recyc_tips: RecyclerView by bindView(R.id.recyc_view_tips)
    val recyc_notice: RecyclerView by bindView(R.id.recyc_view_notice)
    val recyc_daily: RecyclerView by bindView(R.id.recyc_view_daily)


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_daily, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        recyc_tips.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        refeshTipsView()
        add_tips.setOnClickListener {
            val d: AddTipsDialog = AddTipsDialog(db)
            d.dialog?.setOnDismissListener {
                refeshTipsView()
            }
            d.show(fragmentManager, "add_tips")

        }
        super.onCreate(savedInstanceState)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun refeshTipsView() {
        var tips_adapter = TipsAdapter(R.layout.tips_item, initTips())
        emptyview = LayoutInflater.from(context).inflate(R.layout.enpty_view, null)
//        tips_adapter.addHeaderView(headview)
        tips_adapter.emptyView=emptyview
//        tips_adapter.setOnRecyclerViewItemLongClickListener(BaseQuickAdapter.OnRecyclerViewItemLongClickListener(){
//        })
        recyc_tips.adapter = tips_adapter

    }

    private fun initTips(): List<Tips> {
        val tips: ArrayList<Tips> = ArrayList()
        val c: Cursor = db.query(DBConsts.tips_table_name, null, null, null, null, null, null, null)
        if (c.moveToFirst()) {
            do {
                tips.add(Tips(c))
            } while (c.moveToNext())
        }
        Log.i(tips.size.toString(),tips.toString())
        return tips
    }


    class TipsAdapter(layoutResId: Int, data: List<Tips>) : BaseQuickAdapter<Tips>(layoutResId, data) {
        override fun convert(p0: BaseViewHolder?, p1: Tips?) {
//            p0?.setText(R.id.tv_content,p1?.content)
            val content: TextView = p0?.getView(R.id.tv_content)!!
            val img: ImageView = p0.getView(R.id.iv_tips_itme)
            content.text = p1?.content
            val str: String = p1?.img_url!!
            if (StringUtils.isEmpty(str)) {
                img.visibility = View.GONE
            } else {
                img.visibility = View.VISIBLE
                Glide.with(mContext).load(str).into(img)
            }
        }
    }

    class DailyAdapter(layoutResId: Int, data: List<Daily>) : BaseQuickAdapter<Daily>(layoutResId, data) {
        override fun convert(p0: BaseViewHolder?, p1: Daily?) {

        }
    }

}
