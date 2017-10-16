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
import edu.tjrac.swant.bestcase.moudle.main.contacts.been.Contacts
import edu.tjrac.swant.bestcase.util.utils.TimeUtils
import java.util.*

/**
 * Created by wpc on 2017/5/24.
 */

class ContactsExpAdapter(
        internal var mContext: Context,
       internal var contacts: ContactsDatas) : ExpandableListAdapter {

    override fun registerDataSetObserver(observer: DataSetObserver) {

    }

    override fun unregisterDataSetObserver(observer: DataSetObserver) {

    }

    override fun getGroupCount(): Int {
        return contacts.grop_name!!.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return contacts.mContacts!!.get(contacts.grop_name!!.get(groupPosition))!!.size

    }

    override fun getGroup(groupPosition: Int): ArrayList<Contacts>? {
        return contacts.mContacts!!.get(contacts.grop_name!!.get(groupPosition))
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return getGroup(groupPosition)!!.get(childPosition)
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
            gvh.group_name.setText(contacts.grop_name!!.get(groupPosition))
            gvh.asy_time.setText(TimeUtils.getTimeWithFormat(TimeUtils.HOUR_MIN))
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
        val c = getChild(groupPosition, childPosition) as Contacts
        cvh.name.text = c.name
        if (c.portrait_uri != null) {
            val input = ContactsContract.Contacts.openContactPhotoInputStream(mContext.contentResolver, c.portrait_uri)
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

    internal inner class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        var group_name: TextView =itemView.findViewById(R.id.tv_group_name)
                var asy_time : TextView = itemView.findViewById(R.id.tv_asy_time)

    }

    internal inner class ChindViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var portrait: CircleImageView
        var weibo: ImageView
        var wechat: ImageView
        var qq: ImageView
        var name: TextView
        var tel: TextView

        init {
            portrait = itemView.findViewById(R.id.cir_portrait_item_contact)
            weibo = itemView.findViewById(R.id.iv_item_message)
            wechat = itemView.findViewById(R.id.iv_item_third)
            qq = itemView.findViewById(R.id.iv_item_third)
            name = itemView.findViewById(R.id.tv_name_contact)
            tel = itemView.findViewById(R.id.tv_tel_contact)
        }
    }
}
