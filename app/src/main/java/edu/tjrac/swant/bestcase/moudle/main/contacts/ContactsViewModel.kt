package edu.tjrac.swant.bestcase.moudle.main.contacts

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.ContentUris
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.ContactsContract
import android.text.TextUtils
import com.chad.library.adapter.base.entity.MultiItemEntity
import edu.tjrac.swant.bestcase.moudle.main.contacts.been.Contacts
import edu.tjrac.swant.bestcase.moudle.main.contacts.been.ContactsGroup
import edu.tjrac.swant.bestcase.manager.db.DBManager

/**
 * Created by Administrator on 2017/10/10 0010.
 */

class ContactsViewModel : ViewModel() {


    /**获取库Phon表字段 */
    private val PHONES_PROJECTION = arrayOf(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Phone.PHOTO_ID, ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
    /**联系人显示名称 */
    private val PHONES_DISPLAY_NAME_INDEX = 0
    /**电话号码 */
    private val PHONES_NUMBER_INDEX = 1
    /**头像ID */
    private val PHONES_PHOTO_ID_INDEX = 2
    /**联系人的ID */
    private val PHONES_CONTACT_ID_INDEX = 3

private var contacts: MutableLiveData<ContactsDatas>?=null



    private fun getPhoneContacts(context: Context): ArrayList<Contacts> {
        var phone_contacts = ArrayList<Contacts>()
        val resolver = context?.contentResolver
// 获取手机联系人
        val phoneCursor = resolver?.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null)
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
                phone_contacts.add(Contacts(contactName, phoneNumber))
//                mContactsName.add(contactName)
//                mContactsNumber.add(phoneNumber)
//                mContactsPhonto.add(contactPhoto)
            } while (phoneCursor?.moveToNext())
            phoneCursor.close()
        }
        return phone_contacts;
    }

    /**得到手机SIM卡联系人人信息 */
    private fun getSIMContacts(context: Context) {
        val resolver = context?.getContentResolver()
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

     fun getContactsForExpand(activity: Context?): LiveData<ContactsDatas>{
       if(contacts==null){
           contacts= MutableLiveData<ContactsDatas>()
       }
        var contacts_datas=ContactsDatas()

        contacts_datas. grop_name?.add("通讯录")
        contacts_datas. mContacts?.put("通讯录", getPhoneContacts(activity!!))
        var conts: List<Contacts> = DBManager.getInstance(activity).queryContactsList()
        for (contact in conts) {
            if (contacts_datas.grop_name!!.contains(contact.groupName)) {
                contacts_datas. mContacts!!.get(contact.groupName)?.add(contact)
            } else {
                contacts_datas. grop_name!!.add(contact.groupName)
                contacts_datas. mContacts!!.put(contact.groupName, ArrayList())
            }

        }
        contacts!!.value=contacts_datas
        return contacts as MutableLiveData<ContactsDatas>
    }

    fun getContactsForRecyc(activity: Context?):LiveData<ArrayList<MultiItemEntity>> {

        var live_res =MutableLiveData<ArrayList<MultiItemEntity>>()
        var grop_name = ArrayList<String>()
        var mContacts = HashMap<String,ArrayList<Contacts>>()

         grop_name?.add("通讯录")
         mContacts?.put("通讯录", getPhoneContacts(activity!!))
        var conts: List<Contacts> = DBManager.getInstance(activity).queryContactsList()
        for (contact in conts) {
            if (grop_name!!.contains(contact.groupName)) {
                 mContacts!!.get(contact.groupName)?.add(contact)
            } else {
                 grop_name!!.add(contact.groupName)
                 mContacts!!.put(contact.groupName, ArrayList())
            }
        }
        var res = ArrayList<MultiItemEntity>()

        for (name in grop_name) {
            var childs : ArrayList<Contacts> ?= mContacts.get(name)
            val lv0 = ContactsGroup(name, childs!!.size.toString())
            for (contact in childs) {
                lv0.addSubItem(contact)
            }
            res.add(lv0)
        }
        live_res?.value=res
        return live_res!!
    }
}