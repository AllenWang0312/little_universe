package edu.tjrac.swant.bestcase.moudle.main.record.obsolete;

import com.chad.library.adapter.base.entity.SectionEntity;

import edu.tjrac.swant.bestcase.moudle.main.record.been.Record;

/**
 * Created by Administrator on 2017/10/12 0012.
 */

public class Section extends SectionEntity<Record> {

    private boolean isMore;

    public Section(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public Section(Record record) {
        super(record);
    }


}