package edu.tjrac.swant.bestcase.moudle.main.contacts

import android.database.Cursor
import android.net.Uri
import edu.tjrac.swant.bestcase.been.User

/**
 * Created by wpc on 2017/5/24.
 */
class  Contact : User {
    var user_name: String
    var user_phone_num: String? = null
    var qq: String? = null
    var weibo: String? = null
    var weichat: String? = null
    var group_name: String? = null
    internal var grade: Long? = null

    var portrait: Uri?=null
    internal var portrait_uri: String? = null

    constructor(name: String?, user_name: String?) {
        this.user_name = name!!
        this.user_name = user_name!!
    }

    constructor(name: String, phone: String, portrait_uri: String?) {
        this.user_name = name
        this.user_phone_num = phone
        this.portrait_uri = portrait_uri
        portrait = Uri.parse(portrait_uri)
    }

    constructor(c: Cursor) {
        this.user_id = c.getLong(c.getColumnIndex("user_id"))
        this.user_name = c.getString(c.getColumnIndex("user_name"))
        this.user_phone_num = c.getString(c.getColumnIndex("user_phone_num"))
        this.portrait = Uri.parse(c.getString(c.getColumnIndex("user_portrait_uri")))
        this.group_name = c.getString(c.getColumnIndex("group_name"))
        this.grade = c.getLong(c.getColumnIndex("grade"))
    }
}
