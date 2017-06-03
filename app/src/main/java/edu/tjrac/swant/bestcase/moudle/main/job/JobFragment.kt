package edu.tjrac.swant.bestcase.moudle.main.job

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import butterknife.bindView
import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.been.Job
import java.util.*

/**
 * Created by wpc on 2017/4/21.
 */

class JobFragment : Fragment() {

    internal var mContext: Context? = null
    val mRecycJobMain: RecyclerView by bindView(R.id.recyc_job_main)

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContext = activity
       return inflater!!.inflate(R.layout.fragment_job, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = JobListAdapter(R.layout.job_item_recyc, jobs)
        mRecycJobMain.layoutManager = LinearLayoutManager(mContext)
        mRecycJobMain.adapter = adapter

    }

    val jobs: List<Job>
        get() {
            val jobs = ArrayList<Job>()
            jobs.add(Job())
            jobs.add(Job())
            jobs.add(Job())
            jobs.add(Job())
            jobs.add(Job())
            jobs.add(Job())
            jobs.add(Job())
            jobs.add(Job())
            jobs.add(Job())
            return jobs
        }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.job,menu)
    }
}
