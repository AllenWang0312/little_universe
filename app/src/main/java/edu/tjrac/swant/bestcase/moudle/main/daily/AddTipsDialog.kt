package edu.tjrac.swant.bestcase.moudle.main.daily

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.been.Tips
import edu.tjrac.swant.bestcase.common.util.UiUtils
import edu.tjrac.swant.bestcase.manager.db.DBConsts

/**
 * Created by wpc on 2017/5/23.
 */

@SuppressLint("ValidFragment")
class AddTipsDialog(internal var db: SQLiteDatabase) : DialogFragment() {

    internal var mTips: Tips ?= null
    internal var et: EditText ?= null
    internal var tv: TextView ?= null

    internal var dialog: Dialog ?= null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mTips = Tips()
        val builder = AlertDialog.Builder(activity)
        val v = LayoutInflater.from(activity).inflate(R.layout.dialog_add_tips, null)
        et = v.findViewById(R.id.et_add_tips_dialog) as EditText
        et!!.setText("嘿嘿嘿,有什么鬼主意")
        et!!.selectAll()
        UiUtils.hiddenSoftInput(activity)

        tv = v.findViewById(R.id.tv_tips_add_dialog) as TextView

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
            mTips!!.imp_lev = 3
            db.insert(DBConsts.tips_table_name, null, mTips!!.getContentValue())
        }
        dialog = builder.create()
        return (dialog as AlertDialog?)!!
    }

    override fun getDialog(): Dialog? {
        return dialog
    }
}
