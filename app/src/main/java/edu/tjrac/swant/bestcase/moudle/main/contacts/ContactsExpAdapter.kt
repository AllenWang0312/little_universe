package edu.tjrac.swant.bestcase.moudle.main.contacts

import android.content.Context
import android.database.DataSetObserver
import android.graphics.BitmapFactory
import android.provider.ContactsContract
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ExpandableListAdapter
import android.widget.ImageView
import android.widget.TextView
import de.hdodenhof.circleimageview.CircleImageView
import edu.tjrac.swant.bestcase.R
import java.util.*

/**
 * Created by wpc on 2017/5/24.
 */

class ContactsExpAdapter(internal var mContext: Context, internal var group_names: ArrayList<String>, internal var mContacts: HashMap<String, ArrayList<Contact>>) : ExpandableListAdapter {

    override fun registerDataSetObserver(observer: DataSetObserver) {

    }

    override fun unregisterDataSetObserver(observer: DataSetObserver) {

    }

    override fun getGroupCount(): Int {
        return group_names.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return mContacts[group_names[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): ArrayList<Contact>? {
        return mContacts[group_names[groupPosition]]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return mContacts[group_names[groupPosition]]!!.get(childPosition)
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(groupPosition: Int, isExpanded: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        var gvh: GroupViewHolder? = null
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_group_contacts, parent, false)
            gvh = GroupViewHolder(convertView)
            convertView!!.tag = gvh
        } else {
            gvh = convertView.tag as GroupViewHolder
        }

        return convertView
    }

    override fun getChildView(groupPosition: Int, childPosition: Int, isLastChild: Boolean, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        var cvh: ChindViewHolder? = null
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_child_contacts, parent, false)
            cvh = ChindViewHolder(convertView)
            convertView!!.tag = cvh
        } else {
            cvh = convertView.tag as ChindViewHolder
        }
        val c = getChild(groupPosition, childPosition) as Contact
        cvh.name.text = c.user_name
        if (c.portrait != null) {
            val input = ContactsContract.Contacts.openContactPhotoInputStream(mContext.contentResolver, c.portrait)
            cvh.portrait.setImageBitmap(BitmapFactory.decodeStream(input))
        }

        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun areAllItemsEnabled(): Boolean {
        return true
    }

    override fun isEmpty(): Boolean {
        return false
    }

    override fun onGroupExpanded(groupPosition: Int) {

    }

    override fun onGroupCollapsed(groupPosition: Int) {

    }

    override fun getCombinedChildId(groupId: Long, childId: Long): Long {
        return 0
    }

    override fun getCombinedGroupId(groupId: Long): Long {
        return 0
    }

    internal inner class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    internal inner class ChindViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var portrait: CircleImageView
        var weibo: ImageView
        var wechat: ImageView
        var qq: ImageView
        var name: TextView
        var tel: TextView

        init {
            portrait = itemView.findViewById(R.id.cir_portrait_item_contact) as CircleImageView
            weibo = itemView.findViewById(R.id.iv_item_weibo) as ImageView
            wechat = itemView.findViewById(R.id.iv_item_wechat) as ImageView
            qq = itemView.findViewById(R.id.iv_item_qq) as ImageView
            name = itemView.findViewById(R.id.tv_name_contact) as TextView
            tel = itemView.findViewById(R.id.tv_tel_contact) as TextView
        }
    }
}
