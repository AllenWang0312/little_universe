package edu.tjrac.swant.bestcase.moudle.main.contacts.been;

import com.chad.library.adapter.base.entity.AbstractExpandableItem;
import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Administrator on 2017/10/11 0011.
 */

public class ContactsGroup extends AbstractExpandableItem<Contacts> implements MultiItemEntity {
    public String title;
    public String subTitle;

    public ContactsGroup( String title, String subTitle) {
        this.subTitle = subTitle;
        this.title = title;
    }

    @Override
    public int getLevel() {
        return 0;
    }

    @Override
    public int getItemType() {
        return 4;
    }
}
