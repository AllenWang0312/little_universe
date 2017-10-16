package edu.tjrac.swant.bestcase.moudle.main.contacts

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.chad.library.adapter.base.BaseQuickAdapter
import com.tbruyelle.rxpermissions.RxPermissions
import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.base.ActionFragment
import edu.tjrac.swant.bestcase.moudle.main.contacts.been.Contacts
import edu.tjrac.swant.bestcase.been.interfaze.FabBtClickCallBack
import edu.tjrac.swant.bestcase.util.utils.T
import kotterknife.bindView




@SuppressLint("ValidFragment")
/**
 * Created by wpc on 2017/4/21.
 */

class ContactsFragment
//(val db: SQLiteDatabase)
    : ActionFragment(), FabBtClickCallBack {


    //    val mEtFindContacts: EditText by bindView(R.id.et_find_contacts);
//    val mElvContacts: ExpandableListView by bindView(R.id.elv_contacts);
    val swipe : SwipeRefreshLayout by bindView(R.id.swiper)
    val recyc_view: RecyclerView by bindView(R.id.recyc_view)

    var viewModel: ContactsViewModel? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater!!.inflate(R.layout.fragment_recyc_refresh, container, false)
        return v
    }

    var onItemChildClickListener = BaseQuickAdapter.OnItemChildClickListener { adapter, view, position ->
        var tel =(adapter.data.get(position) as Contacts).tel
        when (view.id) {

            R.id.iv_item_phone -> {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" +tel ))
                startActivity(intent)
            }
            R.id.iv_item_message -> {

                val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + tel))
//                intent.putExtra("sms_body", message)
                startActivity(intent)
            }
            R.id.iv_item_third -> {

            }
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        var rxP: RxPermissions = RxPermissions(context as Activity)
        rxP.request(Manifest.permission.READ_CONTACTS)?.subscribe { granted ->
            if (granted!!) {

            } else {
//                getPhoneContacts()
            }
        }
        viewModel = ViewModelProviders.of(this).get(ContactsViewModel::class.java)
        viewModel?.getContactsForRecyc(activity)!!.observe(this, android.arch.lifecycle.Observer { contacts ->
            run {
                //                mElvContacts.setAdapter(ContactsExpAdapter(activity, contacts!!))
                var layoutManager = LinearLayoutManager(activity)
//              layoutManager.sets

                var adapter = ContactsRecycAdapter(contacts!!)
                adapter.onItemChildClickListener=onItemChildClickListener
                recyc_view.adapter = adapter
                recyc_view.layoutManager = layoutManager

                adapter.expandAll()
            }
        })
        swipe.setOnRefreshListener(SwipeRefreshLayout.OnRefreshListener {
            swipe.isRefreshing = true
            viewModel?.getContactsForRecyc(context)
//            handler.sendEmptyMessage(Conts.REFESH_RECYC)
            swipe.isRefreshing = false
        })
        fabClickListener = View.OnClickListener { }
        super.onViewCreated(view, savedInstanceState)
    }


    override fun onFabBtClick() {
        T.show(context, "you clicked fab for contacts fragment")
    }

}
