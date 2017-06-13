package edu.tjrac.swant.bestcase.moudle.main.daily

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.been.record.Tips
import edu.tjrac.swant.bestcase.common.swant.StringUtils

/**
 * Created by wpc on 2017/6/7.
 */
class TipsAdapter(layoutResId: Int, data: List<Tips>) : BaseQuickAdapter<Tips>(layoutResId, data) {
    override fun convert(p0: BaseViewHolder?, p1: Tips?) {
//            p0?.setText(R.id.tv_content,p1?.content)
        val content: TextView = p0?.getView(R.id.tv_content)!!
        val img: ImageView = p0!!.getView(R.id.iv_tips_itme)
        content.text = p1?.content
        val str: String = p1?.img_url!!
        if (StringUtils.isSpace(str)) {
            img.visibility = View.GONE
        } else {
            img.visibility = View.VISIBLE
            Glide.with(mContext).load(str).into(img)
        }
    }
}