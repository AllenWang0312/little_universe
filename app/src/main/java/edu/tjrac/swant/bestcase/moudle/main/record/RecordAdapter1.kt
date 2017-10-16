package edu.tjrac.swant.bestcase.moudle.main.record

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.moudle.main.record.been.*
import edu.tjrac.swant.bestcase.util.utils.TimeUtils

/**
 * Created by Administrator on 2017/10/12 0012.
 */

class RecordAdapter1(internal var mdatas: List<Record>) : BaseMultiItemQuickAdapter<Record, BaseViewHolder>(mdatas) {

    init {
        addItemType(-1, R.layout.group_head)
        addItemType(0, R.layout.item_record_tips)
        addItemType(1, R.layout.item_record_notice)
        addItemType(2, R.layout.item_record_note)
    }

    override fun convert(helper: BaseViewHolder, item: Record) {
        when (helper.itemViewType) {
            -1 -> {
                val title = item as Title
                helper.setText(R.id.group_title, title.title)
            }
            0 -> {
                val tips = item as Tips
                helper.setText(R.id.tv_content, tips.content)
                        .addOnClickListener(R.id.iv_cancel)
                        .addOnClickListener(R.id.iv_check)
            }
            1 -> {
                val notice = item as Notice
                helper.setText(R.id.tv_time_notice, notice.hour.toString() + ":" + notice.min)
                        .setText(R.id.tv_content_notice, notice.tips)
                        .setText(R.id.tv_alert_days,
                                TimeUtils.getSelectDaysString(booleanArrayOf(notice.sun, notice.mon, notice.tue, notice.wed, notice.thu, notice.fri, notice.sat))
                        )
                        .setChecked(R.id.switcher, notice.on).addOnClickListener(R.id.switcher)
            }
            2 -> {
                val note = item as Note

                helper.setText(R.id.name, note.filename).setText(R.id.time,
                        TimeUtils.getTimeWithFormat(note.date, TimeUtils.HOUR_MIN)
                )
            }
        }//        helper.setImageResource(R.id.icon, item.getImageResource());
        // 加载网络图片
        //        Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) viewHolder.getView(R.id.iv));
    }
}
