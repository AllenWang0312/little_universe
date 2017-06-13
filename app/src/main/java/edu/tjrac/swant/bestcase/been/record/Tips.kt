package edu.tjrac.swant.bestcase.been.record

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import edu.tjrac.swant.bestcase.been.interfaze.EditableData
import edu.tjrac.swant.bestcase.been.interfaze.Typeable
import java.util.*

/**
 * Created by wpc on 2017/5/20.
 */
class Tips : EditableData, Typeable {
    override fun getType(): Int =0

    var user_id: Long? = 0
    var imp_lev: Int? = 1
    var img_url: String? = ""
    var content: CharSequence? = null

    override fun toHashMapForMySql(context: Context): HashMap<String, Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun getContentValue(): ContentValues {
        val cv: ContentValues =super.getContentValue()
        cv.put("user_id",user_id)
        cv.put("imp_lev",imp_lev)
        cv.put("img_url",img_url)
        cv.put("content",content.toString())
        return cv
    }
constructor(){

}
    constructor(c: Cursor) : super(c) {
        this.user_id = c.getLong(c.getColumnIndex("user_id"))
        this.content = c.getString(c.getColumnIndex("content"))
        this.imp_lev = c.getInt(c.getColumnIndex("imp_lev"))
        this.img_url = c.getString(c.getColumnIndex("img_url"))
    }

}