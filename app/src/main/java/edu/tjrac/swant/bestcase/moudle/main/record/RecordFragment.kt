package edu.tjrac.swant.bestcase.moudle.main.playground

import android.annotation.SuppressLint
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.base.ActionFragment
import edu.tjrac.swant.bestcase.been.interfaze.OnPositiveButtonClickCallback
import edu.tjrac.swant.bestcase.manager.db.DBManager
import edu.tjrac.swant.bestcase.moudle.main.record.AddNoticeDialog
import edu.tjrac.swant.bestcase.moudle.main.record.AddTipsDialog
import edu.tjrac.swant.bestcase.moudle.main.record.RecordAdapter1
import edu.tjrac.swant.bestcase.moudle.main.record.RecordViewModel
import edu.tjrac.swant.bestcase.moudle.main.record.been.Note
import edu.tjrac.swant.bestcase.moudle.main.record.been.Tips
import edu.tjrac.swant.bestcase.util.swant.FileUtils
import edu.tjrac.swant.bestcase.util.swant.UiUtils
import edu.tjrac.swant.richtexteditor.markdown.MarkdownEditorActivity
import kotterknife.bindView

//import org.greenrobot.eventbus.EventBus


/**
 * Created by wpc on 2017/4/21.
 */


@SuppressLint("ValidFragment")
class RecordFragment
internal constructor(internal var tag: String)
    : ActionFragment() {

    //    var head_view: View? = null
    val recyc_tips: RecyclerView by bindView(R.id.recyc_view)
    val swipe: SwipeRefreshLayout by bindView(R.id.swiper)
    var viewModel: RecordViewModel? = null

    var group_adapter: RecordAdapter1? = null
    var itemClick = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
        var record = adapter.data.get(position)
        if (record is Note) {
            var intent = Intent(activity, MarkdownEditorActivity::class.java)
            intent.putExtra("default_path", record.path)
            intent.putExtra("filename", record.filename)
            startActivity(intent)
        }
    }
    var itemChildClick = BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
        when (view.id) {
            R.id.iv_cancel -> {
                DBManager.getInstance(activity).tipsDao?.delete(viewModel?.getRecords(activity, 1, tag)?.value?.get(position) as Tips)
                viewModel?.deleteItem(position, 1)
                group_adapter!!.notifyItemRemoved(position)
            }

            R.id.iv_check -> {
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_recyc_refresh, container, false)
    }

    //    var siv: StateImageView? = null
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(this).get(RecordViewModel::class.java)

        recyc_tips.layoutManager = LinearLayoutManager(activity)

        viewModel?.getRecords(activity, 1, tag)?.observe(this, Observer { records ->
            run {
                group_adapter = null
                group_adapter = RecordAdapter1(records!!)
                group_adapter?.bindToRecyclerView(recyc_tips)
                group_adapter?.setEmptyView(R.layout.empty)
//                    group_adapter?.addHeaderView(head_view)
                group_adapter?.setOnItemClickListener(itemClick)
                group_adapter?.setOnItemChildClickListener(itemChildClick)
                recyc_tips.adapter = group_adapter
            }
        })


        swipe.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            swipe.isRefreshing = true
            viewModel?.getRecords(context, 1, tag)
            swipe.isRefreshing = false
        })


        fabClickListener = View.OnClickListener { view ->
            UiUtils.showPopmenu(activity, view, true, R.menu.and_record,
                    { item ->
                        when (item.itemId) {
                            R.id.add_tips_menu -> {
                                AddTipsDialog(tag, OnPositiveButtonClickCallback {
                                    viewModel?.getRecords(context, 1, tag)
                                }).show(fragmentManager, "add_tips_menu")
                            }
                            R.id.add_notice_menu -> {
                                AddNoticeDialog(tag, OnPositiveButtonClickCallback {
                                    viewModel?.getRecords(context, 1, tag)
                                }).show(fragmentManager, "add_notice_menu")
                            }
                            R.id.add_note_menu -> {
//                                MarkdownView
                                var intent = Intent(activity, MarkdownEditorActivity::class.java)
                                intent.putExtra("default_path", FileUtils.SD_local_record + "/" + tag)

                                startActivityForResult(intent, 1)
                            }
                        }
                        true
                    })
        }
        super.onViewCreated(view, savedInstanceState)
    }

    fun setTag(text: String) {
        tag = text
        viewModel?.getRecords(activity, 1, tag)
    }

    fun refesh() {
        viewModel?.getRecords(activity, 1, tag)
    }
}
