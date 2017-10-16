package edu.tjrac.swant.bestcase.moudle.main.project.been;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/10/16 0016.
 */

public class Study  extends Project implements Serializable,MultiItemEntity{
    public Study(String name) {
        super(name);
    }

    @Override
    public int getItemType() {
        return 3;
    }
}
