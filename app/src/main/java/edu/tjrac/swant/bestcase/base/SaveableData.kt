package edu.tjrac.swant.bestcase.base

import android.content.ContentValues
import android.content.Context
import java.util.*

/**
 * Created by wpc on 2017/4/17.
 */

interface SaveableData {
    //save to sqlite
    fun getContentValue(): ContentValues
    //save to mysql
    fun toHashMapForMySql(context: Context): HashMap<String, Any>
}
