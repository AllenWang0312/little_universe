package edu.tjrac.swant.bestcase.moudle.main.contacts

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.ContactsContract.CommonDataKinds.Phone
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ExpandableListView
import butterknife.bindView
import edu.tjrac.swant.bestcase.R
import edu.tjrac.swant.bestcase.manager.db.DBConsts
import java.util.*


/**
 * Created by wpc on 2017/4/21.
 */

class ContactsFragment(val db: SQLiteDatabase) : Fragment() {

    /**获取库Phon表字段 */
    private val PHONES_PROJECTION = arrayOf(Phone.DISPLAY_NAME, Phone.NUMBER, Phone.PHOTO_ID, Phone.CONTACT_ID)
    /**联系人显示名称 */
    private val PHONES_DISPLAY_NAME_INDEX = 0
    /**电话号码 */
    private val PHONES_NUMBER_INDEX = 1
    /**头像ID */
    private val PHONES_PHOTO_ID_INDEX = 2
    /**联系人的ID */
    private val PHONES_CONTACT_ID_INDEX = 3

    private val phone_contacts = ArrayList<Contact>()
    private val grop_name = ArrayList<String>()
    private val mContacts = HashMap<String, ArrayList<Contact>>()
    var mContext: Context? = null

    val mEtFindContacts: EditText by bindView(R.id.et_find_contacts);
    val mElvContacts: ExpandableListView by bindView(R.id.elv_contacts);

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContext = activity
        val v = inflater!!.inflate(R.layout.fragment_contacts, container, false)
        return v
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        getPhoneContacts()
        getDataFromDB()
        refeshViews()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun getDataFromDB() {
        val c: Cursor = db.query(DBConsts.table_name, null, null, null, null, null, null)
        if (c.moveToFirst()) {
            do {
                val str = c.getString(c.getColumnIndex("group_name"))
                if (!mContacts.keys.contains(str)) {
                    grop_name.add(str)
                    mContacts.put(str, ArrayList())
                }
                mContacts.get(str)?.add(Contact(c))
            } while (c.moveToNext())
        }
        c.close()
        mContacts.put("手机联系人", phone_contacts)
        grop_name.add(0, "手机联系人")
    }

    private fun refeshViews() {

        mElvContacts.setAdapter(ContactsExpAdapter(activity, grop_name, mContacts))
    }

    private fun getPhoneContacts() {
        val resolver = mContext?.contentResolver
// 获取手机联系人
        val phoneCursor = resolver?.query(Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null)
        if (phoneCursor!!.moveToFirst()) {
            do {
                //得到手机号码
                val phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX)
                //当手机号码为空的或者为空字段 跳过当前循环
                if (TextUtils.isEmpty(phoneNumber))
                    continue
                //得到联系人名称
                val contactName = phoneCursor.getString(PHONES_DISPLAY_NAME_INDEX)
                //得到联系人ID
                val contactid = phoneCursor.getLong(PHONES_CONTACT_ID_INDEX)
                //得到联系人头像ID
                val photoid = phoneCursor.getLong(PHONES_PHOTO_ID_INDEX)
                //得到联系人头像Bitamp
                var contactPhoto: Bitmap? = null
                //photoid 大于0 表示联系人有头像 如果没有给此人设置头像则给他一个默认的
                var uri: Uri? = null
                if (photoid > 0) {
                    uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contactid)
                    val input = ContactsContract.Contacts.openContactPhotoInputStream(resolver, uri)
                    contactPhoto = BitmapFactory.decodeStream(input)
                }
                phone_contacts.add(Contact(contactName, phoneNumber))
//                mContactsName.add(contactName)
//                mContactsNumber.add(phoneNumber)
//                mContactsPhonto.add(contactPhoto)
            } while (phoneCursor?.moveToNext())
            phoneCursor.close()
        }
    }

    /**得到手机SIM卡联系人人信息 */
    private fun getSIMContacts() {
        val resolver = mContext?.getContentResolver()
        // 获取Sims卡联系人
        val uri = Uri.parse("content://icc/adn")
        val phoneCursor = resolver?.query(uri, PHONES_PROJECTION, null, null, null)

        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                // 得到手机号码
                val phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX)
                // 当手机号码为空的或者为空字段 跳过当前循环
                if (TextUtils.isEmpty(phoneNumber))
                    continue
                // 得到联系人名称
                val contactName = phoneCursor
                        .getString(PHONES_DISPLAY_NAME_INDEX)
                //Sim卡中没有联系人头像
//                mContactsName.add(contactName)
//                mContactsNumber.add(phoneNumber)
            }

            phoneCursor.close()
        }
    }

//    private fun getPhoneContacts() {
//        val resolver = mContext!!.contentResolver
//        val c = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null)
//        if (c!!.moveToFirst()) {
//            do {
//                val id = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
//                val isHas = Integer.parseInt(c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)))
//                if (isHas > 0) {
//                    val c1 = mContext!!.contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
//                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id, null, null)
//                    while (c1!!.moveToNext()) {
//                        val number = c1.getString(c1.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
//                        Log.d("TAG ", "Number is : " + number)
//                    }
//                    c1.close()
//                }
//            } while (c.moveToNext())
//        }
//        c.close()
//        //删除联系人
//        //        mContext.getContentResolver()
//        //                .delete(Uri.parse(ContactsContract.RawContacts.CONTENT_URI.toString() + "?" + ContactsContract.CALLER_IS_SYNCADAPTER + "= true"), ContactsContract.RawContacts._ID + "> 0", null);
//    }

}
