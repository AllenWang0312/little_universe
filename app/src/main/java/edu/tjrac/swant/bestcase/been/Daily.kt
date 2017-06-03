package edu.tjrac.swant.bestcase.been

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import edu.tjrac.swant.bestcase.base.EditableData
import java.util.*

/**
 * Created by wpc on 2017/5/20.
 */
class Daily : EditableData {
    var user_id:Long =0
    override fun getContentValue(): ContentValues {
        return super.getContentValue()
    }
    override fun toHashMapForMySql(context: Context): HashMap<String, Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var group: String? = "all"

    var title: String? = null
    var content: String? = null
    //    var content: ArrayList<String>? = null


    constructor(c: Cursor) : super(c) {
        this.user_id=c.getLong(c.getColumnIndex("user_id"))
        this.title = c.getString(c.getColumnIndex("title"))
        this.content = c.getString(c.getColumnIndex("content"))
    }
}