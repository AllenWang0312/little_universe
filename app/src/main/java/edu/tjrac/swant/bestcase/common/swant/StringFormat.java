package edu.tjrac.swant.bestcase.common.swant;

import java.text.DecimalFormat;

/**
 * Created by wpc on 2017/4/1.
 */

public class StringFormat {

    public static String TwoDecimal(double db) {
        DecimalFormat df = new DecimalFormat("######0.00"); //保留两位小数点
        String str2 = df.format(db);
        return str2;
    }

    public static String FourDecimal(double db) {
        DecimalFormat df = new DecimalFormat("######0.0000"); //保留两位小数点
        String str1 = df.format(db);
        return str1;
    }
}
