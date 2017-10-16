package edu.tjrac.swant.richtexteditor.markdown

import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import edu.tjrac.swant.richtexteditor.InputUtils
import edu.tjrac.swant.richtexteditor.R
import edu.tjrac.swant.richtexteditor.ScalePageTransformer
import edu.tjrac.swant.richtexteditor.SoftKeyBoardListener
import kotlinx.android.synthetic.main.activity_markdown_editor.*
import us.feras.mdv.MarkdownView
import java.io.File
import java.io.FileReader
import java.io.FileWriter


class MarkdownEditorActivity : AppCompatActivity() {

    var tools_bt = listOf("Tab", "#", "*", "~", "_", "-", ">", "https")

    var tab: Button? = null

    var hor: HorizontalScrollView? = null
    var vp: ViewPager? = null
    var editor: MarkdownEditText? = null
    var ll: LinearLayout? = null

    var path: String? = null
    var filename: String ?=null
    var  sp :SharedPreferences ?=null;
    var  preview: MarkdownView?=null;
    var  MD_preview:br.tiagohm.markdownview.MarkdownView ?=null

    @RequiresApi(Build.VERSION_CODES.HONEYCOMB_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         sp = getSharedPreferences("app_config", Context.MODE_PRIVATE)

        path = intent.getStringExtra("default_path")
        filename = intent.getStringExtra("filename")

        closeStatusBar()
        setContentView(R.layout.activity_markdown_editor)
        vp = findViewById(R.id.viewpager)

        var edit_view = layoutInflater.inflate(R.layout.mdeditor, vp, false)
        var pre_view = layoutInflater.inflate(R.layout.mdpreview, vp, false)

        editor = edit_view.findViewById<MarkdownEditText>(R.id.mdet_content)
         preview = pre_view.findViewById(R.id.md_view)
         MD_preview= pre_view.findViewById(R.id.markdown_view);



        var onclick = View.OnClickListener { view ->
            InputUtils.insertString(editor, (view as Button).text.toString())
        }

        var adapter = MyPagerAdapter(listOf(pre_view, edit_view))
        vp?.adapter = adapter
        vp?.setPageTransformer(true, ScalePageTransformer())


        hor = findViewById(R.id.hsv)
        ll = findViewById(R.id.ll_input_tools)
        ll?.findViewById<Button>(R.id.jin)?.setOnClickListener(onclick)
        ll?.findViewById<Button>(R.id.xin)?.setOnClickListener(onclick)
        ll?.findViewById<Button>(R.id.lang)?.setOnClickListener(onclick)
        ll?.findViewById<Button>(R.id.xiahua)?.setOnClickListener(onclick)
        ll?.findViewById<Button>(R.id.gang)?.setOnClickListener(onclick)
        ll?.findViewById<Button>(R.id.dayu)?.setOnClickListener(onclick)
//        et?.setOnKeyListener(View.OnKeyListener { view, i, keyEvent ->
//            if((KeyEvent.KEYCODE_ENTER === i)&&KeyEvent.ACTION_DOWN === keyEvent.getAction()) {
//                InputUtils.insertString(et,"\n")
//                true
//            }
//                else false
//        })
        editor?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {

            }

            override fun afterTextChanged(editable: Editable) {
                insetData()
            }
        })
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        SoftKeyBoardListener.setListener(this, object : SoftKeyBoardListener.OnSoftKeyBoardChangeListener {
            override fun keyBoardShow(height: Int) {
                hor?.animate()?.translationY(-height.toFloat())
            }

            override fun keyBoardHide(height: Int) {
                hor?.animate()?.translationY(0f)
            }
        })

        if(filename!=null&& !filename.equals("")){
           var reader   = FileReader(File(path,filename))
            editor?.setText(reader.readText())
        }
    }

    fun closeStatusBar() {
        val decorView = window.decorView
        val uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN
        decorView.systemUiVisibility = uiOptions
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.markdown_options, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
//                finish()
            }
            R.id.action_undo -> {
                editor?.undo()
            }
            R.id.action_redo -> {
                editor?.redo()

            }
            R.id.action_save -> {
                if(filename!=null&&!filename.equals("")){
                    var file =File(path,filename)
                    file.delete()
                    file.createNewFile()
                  var writer =   FileWriter(file)
                    writer.write(editor?.text.toString())
                    writer.close()
                    setResult(2)
                    finish()
                }else{
                    var et = EditText(this)
                    et.setHint("请输入文件名")
                    AlertDialog.Builder(this)
                            .setTitle("文件名")
                            .setView(et)
                            .setPositiveButton("保存", DialogInterface.OnClickListener { dialog, view ->
                                run{
                                    var str = et.text.toString()
                                    if (str != null && !str.equals("")) {
                                        var file = File(path, if (str.endsWith(".md")) str else str + ".md")

                                        if (file.exists()) {
                                            Toast.makeText(this, "文件名已存在,保存失败", Toast.LENGTH_LONG).show()
                                        } else {
                                            if (file.createNewFile()) {
                                                var fw = FileWriter(file)
                                                fw.write(editor?.text.toString())
                                                fw.close()
                                                setResult(1)
                                                finish()
                                            } else {
                                                Toast.makeText(this, "文件保存失败", Toast.LENGTH_LONG).show()
                                            }

                                        }
                                    }
                                }


                            })
                            .setNegativeButton("取消", null).show()


                }

            }
//            R.id.action_preview -> {
//                var mdview = MarkdownView(this)
//
//                mdview.loadMarkdown(mdet_content.text.toString())
//
//                AlertDialog.Builder(this).setView(mdview).show()
//            }
//            R.id.action_info -> {
//            }
            R.id.action_ku_switch ->{
                if(sp?.getInt("markdownview_kind",0)==0){
                    sp?.edit()?.putInt("markdownview_kind",1)?.commit()
                }else{
                    sp?.edit()?.putInt("markdownview_kind",0)?.commit()
                }
              refeshPreView()
                insetData()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun insetData() {
        if(preview?.visibility==View.VISIBLE){
            preview?.loadMarkdown(editor?.text.toString())
        }
        if(MD_preview?.visibility==View.VISIBLE){
            MD_preview?.loadMarkdown(editor?.text.toString())
        }
    }

    private fun refeshPreView() {
        if(sp?.getInt("markdownview_kind",0)==0){
            preview?.visibility=View.VISIBLE
            MD_preview?.visibility=View.GONE
        }else{
            preview?.visibility=View.GONE
            MD_preview?.visibility=View.VISIBLE
        }
    }

    class MyPagerAdapter(internal var views: List<View>) : PagerAdapter() {


        override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
            return view == `object`
        }

        override fun getCount(): Int {
            return views.size
        }

        override fun destroyItem(container: ViewGroup?, position: Int, `object`: Any?) {
            container?.removeView(views.get(position))
//            super.destroyItem(container, position, `object`)
        }

        override fun instantiateItem(container: ViewGroup?, position: Int): Any {
            container?.addView(views.get(position))
            return views.get(position)
            //return super.instantiateItem(container, position)
        }
    }
}
