package edu.tjrac.swant.bestcase.manager;

import android.os.Environment;
import android.util.Log;

/**
 * Created by wpc on 2017/5/20.
 */

public class FileHelper {
    public static  final String  packageName="edu.tjrac.swant.bestcase";
    public static final String APP_DATA_PATH = "/data/data/" + packageName;


    public static String getSDcardPath() {
        String str = Environment.getExternalStorageDirectory().getPath();
        Log.i("getSDcardPath", str);
        return str;
    }

}
