package edu.tjrac.swant.bestcase.moudle.main.contacts;

import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

import edu.tjrac.swant.bestcase.R;
import edu.tjrac.swant.bestcase.moudle.main.contacts.been.Contacts;
import edu.tjrac.swant.bestcase.moudle.main.contacts.been.ContactsGroup;
import edu.tjrac.swant.bestcase.util.utils.TimeUtils;

/**
 * Created by Administrator on 2017/10/10 0010.
 */

public class ContactsRecycAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public ContactsRecycAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(4, R.layout.item_group_contacts);
        addItemType(0, R.layout.item_child_contacts);

    }

    @Override
    protected void convert(BaseViewHolder helper, MultiItemEntity item) {
        switch (helper.getItemViewType()){
            case 4:
                ContactsGroup group=(ContactsGroup) item ;
                helper.setText(R.id.tv_group_name,group.title);
                helper.setText(R.id.tv_asy_time, TimeUtils.getTimeWithFormat(TimeUtils.HOUR_MIN));

                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = helper.getAdapterPosition();
                        Log.d(TAG, "Level 0 item pos: " + pos);
                        if (group.isExpanded()) {
                            collapse(pos);
                        } else {
//                            if (pos % 3 == 0) {
//                                expandAll(pos, false);
//                            } else {
                            expand(pos);
//                            }
                        }
                    }
                });

                break;
            case 0:
                helper.addOnClickListener(R.id.iv_item_phone);
                helper.addOnClickListener(R.id.iv_item_message);
                helper.addOnClickListener(R.id.iv_item_third);
                Contacts contacts =(Contacts)item;
                helper.setText(R.id.tv_name_contact,contacts.name);
                helper.setText(R.id.tv_tel_contact,contacts.tel);
                helper.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                    }
                });

                break;
        }
    }
}
