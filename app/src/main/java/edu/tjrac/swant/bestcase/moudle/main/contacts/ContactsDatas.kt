package edu.tjrac.swant.bestcase.moudle.main.contacts

import edu.tjrac.swant.bestcase.moudle.main.contacts.been.Contacts

/**
 * Created by Administrator on 2017/10/10 0010.
 */

 class ContactsDatas{
    var grop_name : ArrayList<String> ? = null
    var mContacts : HashMap<String, ArrayList<Contacts>>?=null
    constructor(){
        grop_name = ArrayList()
        mContacts = HashMap()
    }
}
