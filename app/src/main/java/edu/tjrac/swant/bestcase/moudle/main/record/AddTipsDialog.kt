package edu.tjrac.swant.bestcase.moudle.main.record

//import edu.tjrac.swant.bestcase.been.record_kt.Tips
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.been.interfaze.OnPositiveButtonClickCallback
import edu.tjrac.swant.bestcase.moudle.main.record.been.Tips
import edu.tjrac.swant.bestcase.manager.db.DBManager



/**
 * Created by wpc on 2017/5/23.
 */

@SuppressLint("ValidFragment")
class AddTipsDialog
(internal var tag :String,internal var positive : OnPositiveButtonClickCallback)
    : DialogFragment() {

    internal var mTips: Tips?= null
    internal var et: EditText ?= null
    internal var tv: TextView ?= null

    internal var dialog: Dialog ?= null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        var model = ViewModelProviders.of(this).get(RecordViewModel::class.java)

        mTips = Tips()
        val builder = AlertDialog.Builder(activity)
        val v = LayoutInflater.from(activity).inflate(R.layout.dialog_add_tips, null)
        et = v.findViewById(R.id.et_add_tips_dialog)
        et!!.setText("嘿嘿嘿,有什么鬼主意")
        et!!.selectAll()
//        UiUtils.hiddenSoftInput(activity)

        tv = v.findViewById(R.id.tv_tips_add_dialog)

        et!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                tv!!.text = s
            }

            override fun afterTextChanged(s: Editable) {

            }
        })
        builder.setView(v)

        builder.setNegativeButton("取消", null)
        builder.setPositiveButton("确定") { dialog, which ->
            mTips!!.content = et!!.text.toString()
            mTips!!.tag=tag
            mTips!!.imp_lev = 3
            DBManager.getInstance(activity).insertTips(mTips)
            positive.call()
//            EventBus.getDefault().post(RefeshRecordEvent())
//            db.insert(DBConsts.tips_table_name, null, mTips!!.getContentValue())
        }
        dialog = builder.create()
        return (dialog as AlertDialog?)!!
    }

    override fun getDialog(): Dialog? {
        return dialog
    }
}
