package edu.tjrac.swant.bestcase.moudle.main.playground

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import butterknife.bindView
import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.been.interfaze.Typeable
import edu.tjrac.swant.bestcase.been.record.Daily
import edu.tjrac.swant.bestcase.been.record.Note
import edu.tjrac.swant.bestcase.been.record.Notice
import edu.tjrac.swant.bestcase.been.record.Tips
import edu.tjrac.swant.bestcase.common.swant.UiUtils
import edu.tjrac.swant.bestcase.manager.db.DBConsts
import edu.tjrac.swant.bestcase.moudle.main.daily.AddRecordDialog
import edu.tjrac.swant.bestcase.moudle.main.daily.RecordAdapter
import java.util.*

/**
 * Created by wpc on 2017/4/21.
 */

class RecordFragment internal constructor(internal var db: SQLiteDatabase) : Fragment() {
    //    internal var headview: View? = null
    var emptyview: View? = null

    val add_tips: ImageView by bindView(R.id.iv_tips_add)
    val recyc_tips: RecyclerView by bindView(R.id.recyc_view_tips)
    //    val recyc_notice: RecyclerView by bindView(R.id.recyc_view_notice)
//    val recyc_daily: RecyclerView by bindView(R.id.recyc_view_daily)

    val mCallback = object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(p0: RecyclerView?, p1: RecyclerView.ViewHolder?): Int {
            var dargFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
            var swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
            return makeMovementFlags(dargFlags, swipeFlags)
        }

        override fun onMove(p0: RecyclerView?, p1: RecyclerView.ViewHolder?, p2: RecyclerView.ViewHolder?): Boolean {
            Collections.swap(datas, p1!!.adapterPosition, p2!!.adapterPosition)
            tips_adapter!!.notifyItemMoved(p1!!.adapterPosition, p2.adapterPosition)
            return false
        }

        override fun onSwiped(p0: RecyclerView.ViewHolder?, p1: Int) {
            datas!!.removeAt(p0!!.adapterPosition)
            tips_adapter!!.notifyItemRemoved(p0.adapterPosition)
        }

        override fun isLongPressDragEnabled(): Boolean {
            return true
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_daily, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        //initviews
        recyc_tips.layoutManager = LinearLayoutManager(activity)
        ItemTouchHelper(mCallback).attachToRecyclerView(recyc_tips)

//        recyc_tips.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        initData()
        refeshTipsView()
        //setlistener
        add_tips.setOnClickListener {
            UiUtils.showPopmenu(activity, add_tips, true, R.menu.and_record,
                    { item ->
                        when (item.itemId) {
                            R.id.add_tips_menu -> {
                                val d: AddRecordDialog = AddRecordDialog(db)
                                d.dialog?.setOnDismissListener {
                                    refeshTipsView()
                                }
                                d.show(fragmentManager, "add_tips")
                            }
                        }
                        true
                    })
        }
        super.onViewCreated(view, savedInstanceState)
    }

    var tips_adapter: RecordAdapter? = null
    private fun refeshTipsView() {
//        var tips_adapter = TipsAdapter(R.layout.tips_item, initTips())
        tips_adapter = RecordAdapter(activity, datas)
//        emptyview = LayoutInflater.from(context).inflate(R.layout.enpty_view, null)
//        tips_adapter.addHeaderView(headview)
//        tips_adapter.setOnRecyclerViewItemLongClickListener(BaseQuickAdapter.OnRecyclerViewItemLongClickListener(){
//        })
        recyc_tips.adapter = tips_adapter
    }

    var datas: ArrayList<Typeable>? = null
    private fun initData() {
        datas = ArrayList()
        var c: Cursor = db.query(DBConsts.tips_table_name, null, null, null, null, null, null, null)
        if (c.moveToFirst()) {
            do {
                datas!!.add(Tips(c))
            } while (c.moveToNext())
        }
        c = db.query(DBConsts.notice_table_name, null, null, null, null, null, null, null)
        if (c.moveToFirst()) {
            do {
                datas!!.add(Notice(c))
            } while (c.moveToNext())
        }
        c = db.query(DBConsts.note_table_name, null, null, null, null, null, null, null)
        if (c.moveToFirst()) {
            do {
                datas!!.add(Note(c))
            } while (c.moveToNext())
        }
        c = db.query(DBConsts.daily_table_name, null, null, null, null, null, null, null)
        if (c.moveToFirst()) {
            do {
                datas!!.add(Daily(c))
            } while (c.moveToNext())
        }
    }

}
