package edu.tjrac.swant.bestcase.been

import android.content.Context
import android.database.Cursor
import edu.tjrac.swant.bestcase.base.EditableData
import java.util.*

/**
 * Created by wpc on 2017/5/20.
 */
class Notice : EditableData {
    var user_id: Long = 0

    override fun toHashMapForMySql(context: Context): HashMap<String, Any> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var open_notice: Boolean? = false
    var content: String? = null

    var voice_audio_path: String? = null
    var alert_times: Int? = 1;
    var when_notice: Long? = 0


    constructor(c: Cursor) : super(c) {
        this.user_id = c.getLong(c.getColumnIndex("user_id"))
        this.open_notice = c.getInt(c.getColumnIndex("open_notice")) == 1
        this.voice_audio_path = c.getString(c.getColumnIndex("voice_audio_path"))
        this.alert_times = c.getInt(c.getColumnIndex("alert_times"))
        this.when_notice = c.getLong(c.getColumnIndex("when_notice"))
        this.content = c.getString(c.getColumnIndex("content"))
    }

}