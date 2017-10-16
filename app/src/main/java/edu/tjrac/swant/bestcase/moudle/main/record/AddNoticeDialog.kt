package edu.tjrac.swant.bestcase.moudle.main.record

import android.annotation.SuppressLint
import android.app.Dialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.been.interfaze.OnPositiveButtonClickCallback
import edu.tjrac.swant.bestcase.moudle.main.record.been.Notice
import edu.tjrac.swant.bestcase.manager.db.DBManager
import edu.tjrac.swant.bestcase.util.utils.TimeUtils
import java.util.*


/**
 * Created by wpc on 2017/5/23.
 */

@SuppressLint("ValidFragment")
class AddNoticeDialog
(internal var tag: String, internal var positive: OnPositiveButtonClickCallback)
    : DialogFragment() {

    internal var notice: Notice? = null

//    internal var dialog: Dialog? = null


    var clock: TextView?=null //by bindView(R.id.tv_time_notice)
    var cb0: CheckBox?=null //by bindView(R.id.cb_sun)
    var cb1: CheckBox?=null //by bindView(R.id.cb_mon)
    var cb2: CheckBox?=null //by bindView(R.id.cb_tue)
    var cb3: CheckBox?=null //by bindView(R.id.cb_wed)
    var cb4: CheckBox?=null //by bindView(R.id.cb_thu)
    var cb5: CheckBox?=null //by bindView(R.id.cb_fri)
    var cb6: CheckBox?=null //by bindView(R.id.cb_sat)
    var vibrate: CheckBox?=null //by bindView(R.id.cb_vibrate)
    var ll: LinearLayout?=null //by bindView(R.id.ll_volum)
    var tips: EditText?=null //by bindView(R.id.et_tags)

//    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val window = getDialog()?.getWindow()
//        val view = inflater?.inflate(R.layout.dialog_add_notice,
////                window!!.findViewById<View>(android.R.id.content) as ViewGroup,
//                container,
//                false)//需要用android.R.id.content这个view
//        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))//注意此处
//        window.setLayout(500, 1000)//这2行,和上面的一样,注意顺序就行;
//        return view
//    }
//
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        var model = ViewModelProviders.of(this).get(RecordViewModel::class.java
        notice = Notice()

        val builder = AlertDialog.Builder(activity)
        val v = LayoutInflater.from(activity).inflate(R.layout.dialog_add_notice, null)
        clock = v.findViewById(R.id.tv_time_notice)
    clock?.setText(TimeUtils.getTimeWithFormat(TimeUtils.HOUR_MIN))
        cb0 = v.findViewById(R.id.cb_sun)
        cb1 = v.findViewById(R.id.cb_mon)
        cb2 = v.findViewById(R.id.cb_tue)
        cb3 = v.findViewById(R.id.cb_wed)
        cb4 = v.findViewById(R.id.cb_thu)
        cb5 = v.findViewById(R.id.cb_fri)
        cb6 = v.findViewById(R.id.cb_sat)

        vibrate = v.findViewById(R.id.cb_sun)
        ll = v.findViewById(R.id.ll_volum)
        tips = v.findViewById(R.id.et_tags)

//        ButterKnife.inject(this, view)
        var c: Calendar = Calendar.getInstance()
        c.setTimeInMillis(System.currentTimeMillis())

        val hour = c.get(Calendar.HOUR_OF_DAY)

        val minute = c.get(Calendar.MINUTE)
        clock?.setOnClickListener(View.OnClickListener {
            TimePickerDialog(activity, TimePickerDialog.OnTimeSetListener { view, hour, min ->
                run {
                    notice?.hour = hour
                    notice?.min = min

                    clock?.setText(hour.toString() + ":" + min.toString())

                }

            }, hour, minute, true).show()
        }
        )
        ll?.setOnClickListener(View.OnClickListener {
//startActivityForResult(Intent(activity,))
        })

        builder.setView(v)

        builder.setNegativeButton("取消", null)
        builder.setPositiveButton("确定") { dialog, which ->
            notice?.sun= cb0?.isChecked
            notice?.mon= cb1?.isChecked
            notice?.tue= cb2?.isChecked
            notice?.wed= cb3?.isChecked
            notice?.thu= cb4?.isChecked
            notice?.fri= cb5?.isChecked
            notice?.sat= cb6?.isChecked

//            notice.vlorm
            notice?.vibrative = vibrate?.isChecked

            notice?.tips=tips?.text.toString()
            notice?.tag=tag
            DBManager.getInstance(activity).insertNotice(notice)
            positive.call()
        }

        return builder.create()
    }
}