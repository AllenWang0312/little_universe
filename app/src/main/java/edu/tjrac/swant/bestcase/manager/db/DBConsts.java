package edu.tjrac.swant.bestcase.manager.db;

/**
 * Created by wpc on 2017/5/20.
 */

public class DBConsts {
    public static final String db_name = "data.db";

    public static final String user_table_name = "user";
    public static final String tips_table_name = "tips";
    public static final String notice_table_name = "notice";
    public static final String daily_table_name = "daily";

    public static final String CREATE_USER_TABLE = "create table if not exists " + user_table_name + "("
            + "id integer primary key autoincrement,"
            + "service_id bigint unique,"
            + "name text unique,"
            + "portrait text,"
            + "password text,"
            + "hasLogin text,"
            + "tag text)";
    public static final String CREATE_TIPS_TABLE = "create table if not exists " + tips_table_name + "("
            + "user_id bigint,"
            + "create_time bigint unique,"
            + "last_alter_time bigint,"
            + "imp_lev int,"
            + "img_url text,"
            + "content text)";
    public static final String CREATE_NOTICE_TABLE = "create table if not exists " + notice_table_name + "("
            + "user_id bigint unique,"
            + "create_time bigint,"
            + "last_alter_time bigint,"
            + "open_notice int,"
            + "voice_audio_path text,"
            + "alert_times int,"
            + "when_notice bigint,"
            + "content text)";
    public static final String CREATE_DAILY_TABLE = "create table if not exists " + daily_table_name + "("
            + "user_id bigint unique,"
            + "create_time bigint,"
            + "last_alter_time bigint,"
            + "content text)";
    public static final String table_name = "contacts";

    public static final String CREATE_CONTACT_TABLE = "create table if not exists " + table_name + "(" +
            "id integer primary key autoincrement," +
            "user_id bigint unique," +
            "user_name text," +
            "user_phone_num text," +
            "group_name text," +
            "user_portrait_uri text," +
            "qq text," +
            "weichat text," +
            "weibo text," +
            "grade bigint)";
}
