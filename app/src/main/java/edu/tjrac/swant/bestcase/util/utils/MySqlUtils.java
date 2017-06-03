package edu.tjrac.swant.bestcase.util.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by cimcenter on 2017/4/13.
 */

public class MySqlUtils {
    public static String TABLE_NAME1 = "table_"+"user";
    public static String TABLE_NAME_QQ = "table_"+"user"+"qq";
    public static String TABLE_NAME_WEIXI = "table_"+"user"+"weixi";
    public static String TABLE_NAME_WEIBO = "table_"+"user"+"weibo";
    private static Connection mConn;
    private static Statement mStmt;
    private static String sql;
    private static ResultSet mRs;
    private static boolean IS_ZHU = false;

    /**
     * 获取连接
     * @param table
     */
    public static  void  getConnect(final String table, final String openID, final String name, final String accessToken,
                            final String gender, final String province, final String city, final String config){
        new Thread(){
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                //获取连接
                mConn = null;
                try {
                    mConn = DriverManager
                            .getConnection(
                                    "jdbc:mysql://rdsz4kl5za13u8d0m3i8spublic.mysql.rds.aliyuncs.com:3306/chnspec",
                                    "spec3205722251", "spec3205722251");
                    mStmt = mConn.createStatement();
                    L.e("mstem=="+mStmt);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //插入数据（手机号） 建表
                if(mConn!= null){
                    sql = String
                            .format("CREATE TABLE IF NOT EXISTS %s (ID INT(20)NOT NULL UNIQUE AUTO_INCREMENT ," +
                                            "openID VARCHAR(300),name VARCHAR(300),accessToken VARCHAR(300)," +
                                            "gender VARCHAR(300),province VARCHAR(300),city VARCHAR(300),config VARCHAR(300))",
                                    table);
                    try {
                        mStmt.executeUpdate(sql);

                        String sql = "SELECT COUNT(*) FROM "+ table;
                        L.e("spl=="+sql);
                        mStmt.executeQuery(sql);
                        L.e("表存在");

                        sql = "SELECT * from "+table+" where openid ='"+openID+"'";
                        L.e("spl=="+sql);
                        mRs= mStmt.executeQuery(sql);
                        if(mRs.next()){
                            IS_ZHU = true;
                            L.e("IS_ZHU000=="+IS_ZHU);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                L.e("继续");
                //插入数据
                L.e("IS_ZHU=="+IS_ZHU);
                if(IS_ZHU){
                    return;
                }else {
                    try {
                        sql = String
                                .format("insert into %s(openID,name,accessToken,gender,province,city,config) values(\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\') ",
                                        table, openID,name,accessToken,gender,province,city,config);
                        mStmt.executeUpdate(sql);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }



}
