package edu.tjrac.swant.bestcase.base

import android.content.ContentValues
import android.database.Cursor

/**
 * Created by wpc on 2017/5/20.
 */
abstract class EditableData : SaveableData {
    var create_time: Long? = 0
    var last_alter_time: Long? = 0;

    init {
        create_time = System.currentTimeMillis();
    }

    constructor() {

    }


    constructor(c: Cursor) {
        this.create_time = c.getLong(c.getColumnIndex("create_time"))
        this.last_alter_time = c.getLong(c.getColumnIndex("last_alter_time"))
    }

    override fun getContentValue(): ContentValues {
        var cv: ContentValues = ContentValues()
        cv.put("create_time",create_time)
        cv.put("last_alter_time",last_alter_time)
        return cv
    }

}