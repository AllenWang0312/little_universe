package edu.tjrac.swant.bestcase.moudle.main.project

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.moudle.main.project.been.Project

/**
 * Created by wpc on 2017/4/26.
 */

class ProjectAdapter(internal var data: List<Project>) : BaseMultiItemQuickAdapter<Project, BaseViewHolder>(data) {


    init {
        addItemType(0, R.layout.item_project)
    }

    override fun convert(helper: BaseViewHolder, item: Project) {
        helper.setText(R.id.project_name, item.name)
    }

}
