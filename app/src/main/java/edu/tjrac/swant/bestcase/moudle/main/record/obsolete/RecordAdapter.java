package edu.tjrac.swant.bestcase.moudle.main.record.obsolete;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import edu.tjrac.swant.bestcase.R;
import edu.tjrac.swant.bestcase.moudle.main.record.been.Notice;
import edu.tjrac.swant.bestcase.moudle.main.record.been.Record;
import edu.tjrac.swant.bestcase.moudle.main.record.been.Tips;
import edu.tjrac.swant.bestcase.util.utils.TimeUtils;

/**
 * Created by wpc on 2017/6/7.
 */

public class RecordAdapter extends BaseMultiItemQuickAdapter<Record, BaseViewHolder> {

    List<Record> mdatas;

    public RecordAdapter(List datas) {
        super(datas);
        mdatas = datas;
        addItemType(0, R.layout.item_record_tips);
        addItemType(1, R.layout.item_record_notice);

    }

    public List<Record> getDatas() {
        return mdatas;
    }

    @Override
    protected void convert(BaseViewHolder helper, Record item) {
        switch (helper.getItemViewType()) {
            case 0:
                Tips tips = (Tips) item;
                helper.setText(R.id.tv_content, tips.content)
                        .addOnClickListener(R.id.iv_cancel)
                        .addOnClickListener(R.id.iv_check);

//        helper.setImageResource(R.id.icon, item.getImageResource());
                // 加载网络图片
//        Glide.with(mContext).load(item.getUserAvatar()).crossFade().into((ImageView) viewHolder.getView(R.id.iv));

                break;
            case 1:
                Notice notice = (Notice) item;
                helper.setText(R.id.tv_time_notice,notice.hour + ":" + notice.min)
                        .setText(R.id.tv_content_notice, notice.tips)
                        .setText(R.id.tv_alert_days,
                                TimeUtils.getSelectDaysString(new boolean[]{
                                        notice.sun, notice.mon, notice.tue, notice.wed, notice.thu, notice.fri, notice.sat
                                })
                        )
                        .setChecked(R.id.switcher, notice.on).addOnClickListener(R.id.switcher);


                break;
        }
    }
}