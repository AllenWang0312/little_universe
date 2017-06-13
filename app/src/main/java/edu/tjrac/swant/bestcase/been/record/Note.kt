package edu.tjrac.swant.bestcase.been.record

import android.content.Context
import android.database.Cursor
import edu.tjrac.swant.bestcase.been.interfaze.EditableData
import edu.tjrac.swant.bestcase.been.interfaze.Typeable
import java.util.*

/**
 * Created by wpc on 2017/6/7.
 */

class Note : EditableData, Typeable {

    var title: String?=null
    var Content: String?=null
    var tags :ArrayList<String>?=null

    override fun toHashMapForMySql(context: Context): HashMap<String, Any> {
        return HashMap()
    }

    override fun getType(): Int=2

    constructor(c: Cursor): super(){

    }
}
