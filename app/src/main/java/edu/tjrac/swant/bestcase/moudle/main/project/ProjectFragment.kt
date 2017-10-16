package edu.tjrac.swant.bestcase.moudle.main.project

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.base.ActionFragment
import edu.tjrac.swant.bestcase.been.interfaze.OnPositiveButtonClickCallback
import edu.tjrac.swant.bestcase.util.swant.UiUtils
import kotterknife.bindView

/**
 * Created by wpc on 2017/4/21.
 */

class ProjectFragment : ActionFragment() {

    internal var mContext: Context? = null
    val mRecycJobMain: RecyclerView by bindView(R.id.recyc_job_main)
    var viewModel: ProjectViewModel? = null
    val swipe: SwipeRefreshLayout by bindView(R.id.swiper)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContext = activity


        return inflater!!.inflate(R.layout.fragment_job, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {

        viewModel = ViewModelProviders.of(this).get(ProjectViewModel::class.java)

        super.onViewCreated(view, savedInstanceState)

        mRecycJobMain.layoutManager = LinearLayoutManager(mContext)
        viewModel?.projects?.observe(this, Observer { projects ->
            var adapter = ProjectAdapter(projects!!)
            adapter.bindToRecyclerView(mRecycJobMain)
            mRecycJobMain.adapter = adapter
            adapter.setEmptyView(R.layout.empty)
        })
        swipe.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            swipe.isRefreshing = true
            viewModel?.projects
            swipe.isRefreshing = false
        })

        fabClickListener = View.OnClickListener {view ->
            UiUtils.showPopmenu(activity, view, false, R.menu.and_project,
                    { item ->
                        when (item.itemId) {
                            R.id.add_project -> {
//                                AddProjectDialog(activity, OnPositiveButtonClickCallback {
//                                    viewModel?.projects
//                                }).show(fragmentManager, "add_tips_menu")
                            }
                            R.id.add_project -> {
                                AddProjectDialog(activity, OnPositiveButtonClickCallback {
                                    viewModel?.projects
                                }).show(fragmentManager, "add_tips_menu")
                            }
                        }
                        true
                    })
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.job, menu)
    }


}
