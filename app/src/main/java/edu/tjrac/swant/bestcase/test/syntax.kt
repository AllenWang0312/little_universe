package edu.tjrac.swant.bestcase.test

import android.database.sqlite.SQLiteDatabase
import android.widget.EditText

/**
 * Created by wpc on 2017/5/20.
 */

class syntax internal constructor(internal var db: SQLiteDatabase) : Any() {
    init {
        val et: EditText ?=null
        et!!.setText("nnn")
    }
}
