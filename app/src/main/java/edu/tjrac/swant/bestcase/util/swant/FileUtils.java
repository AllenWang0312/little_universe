package edu.tjrac.swant.bestcase.util.swant;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by wpc on 2017/4/14.
 */

public class FileUtils {
    //包名 路径下文件会在 app卸载的时候删除  并且外界不可访问
    public static final String packagename = "edu.tjrac.swant.bestcase";
    public static final String APP_DATA_PATH = "/data/data/" + packagename;

    public static final String SDCARD_PATH = Environment.getExternalStorageDirectory().getPath();

    public static final String SD_ed = SDCARD_PATH + "/ed";
    public static final String SD_local = SD_ed + "/local_user";
    public static final String SD_local_record = SD_local + "/record";
    public static final String SD_local_project= SD_local + "/project";


    public static void fileSystemCheck() {
        createOrExistsDir(new File(SDCARD_PATH, "ed"));

        createOrExistsDir(new File(SD_ed, "local_user"));

        createOrExistsDir(new File(SD_local, "project"));
        createOrExistsDir(new File(SD_local, "record"));

        createOrExistsDir(new File(SD_local_record, "健康"));
        createOrExistsDir(new File(SD_local_record, "学习"));
        createOrExistsDir(new File(SD_local_record, "工作"));
        createOrExistsDir(new File(SD_local_record, "生活"));
    }

    public static boolean writeByteArrayToFile(byte[] bytes, String dirPath, String filename) {
        createOrExistsDir(dirPath);
        createFileIfNotExist(dirPath, filename);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(dirPath, filename));
            fos.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                fos.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }


    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static long getTotalSpace(File path) {
        if (path == null) {
            return -1;
        }
        //如果这个sdk大于9 那就使用系统的api
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {

            return path.getTotalSpace();
        } else//小于9 系统没有这个api 我们就自己算吧。
        {
            final StatFs statFs = new StatFs(path.getPath());
            return statFs.getBlockSize() * statFs.getBlockCount();
        }
    }


    public static boolean createOrExistsDir(String dirPath) {
        return createOrExistsDir(getFileByPath(dirPath));
    }

    public static File getFileByPath(String filePath) {
        return StringUtils.isSpace(filePath) ? null : new File(filePath);
    }

    public static boolean createOrExistsDir(File file) {
        // 如果存在，是目录则返回true，是文件则返回false，不存在则返回是否创建成功
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }

    private static void createFileIfNotExist(String dirPath, String filename) {
        File file = new File(dirPath, filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
