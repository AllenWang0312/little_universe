package edu.tjrac.swant.bestcase.manager.db;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import javax.annotation.Nullable;

import edu.tjrac.swant.bestcase.been.User;
import edu.tjrac.swant.bestcase.moudle.main.contacts.been.Contacts;
import edu.tjrac.swant.bestcase.been.dao.ContactsDao;
import edu.tjrac.swant.bestcase.been.dao.DaoMaster;
import edu.tjrac.swant.bestcase.been.dao.DaoSession;
import edu.tjrac.swant.bestcase.been.dao.NoticeDao;
import edu.tjrac.swant.bestcase.been.dao.TipsDao;
import edu.tjrac.swant.bestcase.been.dao.UserDao;
import edu.tjrac.swant.bestcase.moudle.main.record.been.Notice;
import edu.tjrac.swant.bestcase.moudle.main.record.been.Tips;

/**
 * Created by Administrator on 2017/9/29 0029.
 */

public class DBManager {

    private final static String dbName = "ed";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    /**
     * 插入一条记录
     *
     * @param user
     */
    public void insertUser(User user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.insert(user);
    }


    public TipsDao getTipsDao() {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        TipsDao mTipsDao = daoSession.getTipsDao();
        return mTipsDao;
    }

    public void insertTips(Tips record) {
        getTipsDao().insert(record);
    }

    public void insertNotice(Notice notice) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        NoticeDao mNoticeDao = daoSession.getNoticeDao();
        mNoticeDao.insert(notice);
//        mRecordDao.insert(notice);
    }

    /**
     * 插入用户集合
     *
     * @param users
     */
    public void insertUserList(List<User> users) {
        if (users == null || users.isEmpty()) {
            return;
        }
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        userDao.insertInTx(users);
    }


    public List<User> queryUserList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        UserDao userDao = daoSession.getUserDao();
        QueryBuilder<User> qb = userDao.queryBuilder();
        List<User> list = qb.list();

        return list;
    }

    public QueryBuilder<Tips> getTipsQueryBuilder() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        TipsDao Dao = daoSession.getTipsDao();
        return Dao.queryBuilder();
    }
    public QueryBuilder<Notice> getNoticeQueryBuilder() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        NoticeDao Dao = daoSession.getNoticeDao();
        return Dao.queryBuilder();
    }

    @SuppressLint("NewApi")
    public List<Tips> queryTipsList(@Nullable String tag) {
        List<Tips> list = null;
        if (tag != null) {
            list = getTipsQueryBuilder().where(TipsDao.Properties.Tag.eq(tag)).list();
        } else {
            list = getTipsQueryBuilder().list();
        }
        return list;
    }

    @SuppressLint("NewApi")
    public List<Notice> queryNoticeList(@Nullable String tag) {
        List<Notice> list = null;
        if (tag != null) {
            list = getNoticeQueryBuilder().where(NoticeDao.Properties.Tag.eq(tag)).list();
        } else {
            list = getNoticeQueryBuilder().list();
        }
        return list;
    }

    public List<Contacts> queryContactsList() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();

        ContactsDao userDao = daoSession.getContactsDao();
        QueryBuilder<Contacts> qb = userDao.queryBuilder();
        List<Contacts> list = qb.list();
        return list;
    }
}
