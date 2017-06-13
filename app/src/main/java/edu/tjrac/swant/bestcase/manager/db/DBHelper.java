package edu.tjrac.swant.bestcase.manager.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wpc on 2017/5/20.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBConsts.CREATE_USER_TABLE);

        db.execSQL(DBConsts.CREATE_TIPS_TABLE);
        db.execSQL(DBConsts.CREATE_NOTICE_TABLE);
        db.execSQL(DBConsts.CREATE_NOTE_TABLE);
        db.execSQL(DBConsts.CREATE_DAILY_TABLE);

        db.execSQL(DBConsts.CREATE_CONTACT_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
