package edu.tjrac.swant.bestcase.moudle.main.record.obsolete;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import edu.tjrac.swant.bestcase.R;
import edu.tjrac.swant.bestcase.moudle.main.record.been.Notice;
import edu.tjrac.swant.bestcase.moudle.main.record.been.Tips;
import edu.tjrac.swant.bestcase.util.utils.TimeUtils;

/**
 * Created by Administrator on 2017/10/12 0012.
 *  过时
 */

public class GroupRecordsAdapter extends BaseSectionQuickAdapter<Section, BaseViewHolder> {

private  Context context;
    public GroupRecordsAdapter(Context context,List data) {
        super(0, R.layout.group_head, data);
        this.context=context;
    }

    @Override
    protected void convertHead(BaseViewHolder helper, Section item) {
        helper.setText(R.id.group_title, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, Section item) {
        if (item.t instanceof Tips){
            View v = LayoutInflater.from(context).inflate(R.layout.item_record_tips,null);
            helper=new BaseViewHolder(v);
            helper.setText(R.id.tv_content, ((Tips)item.t).content)
                    .addOnClickListener(R.id.iv_cancel)
                    .addOnClickListener(R.id.iv_check);

        }else if(item.t instanceof Notice){
            View v = LayoutInflater.from(context).inflate(R.layout.notice_item,null);
            helper=new BaseViewHolder(v);
            Notice notice = (Notice) item.t;
            helper.setText(R.id.tv_time_notice,notice.hour + ":" + notice.min)
                    .setText(R.id.tv_content_notice, notice.tips)
                    .setText(R.id.tv_alert_days,
                            TimeUtils.getSelectDaysString(new boolean[]{
                                    notice.sun, notice.mon, notice.tue, notice.wed, notice.thu, notice.fri, notice.sat
                            })
                    )
                    .setChecked(R.id.switcher, notice.on).addOnClickListener(R.id.switcher);

        }
    }

    class TipsViewHolder extends BaseViewHolder {

        public TipsViewHolder(View view) {
            super(view);
        }
    }


}
