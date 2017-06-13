package edu.tjrac.swant.bestcase.manager;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wpc on 2017/6/13.
 */

public class SystemResProvider {

  public static   HashMap<String, List<String>> getSystemImgs(Context context) {
        HashMap<String, List<String>> imgs = new HashMap<>();
        Uri mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver mContentResolver = context.getContentResolver();
        //只查询jpeg和png的图片
        Cursor mCursor = mContentResolver.query(mImageUri, null,
                MediaStore.Images.Media.MIME_TYPE + "=? or "
                        + MediaStore.Images.Media.MIME_TYPE + "=?",
                new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_MODIFIED);
        if (mCursor.moveToFirst()) {
            do {
                String path = mCursor.getString(mCursor
                        .getColumnIndex(MediaStore.Images.Media.DATA));
                //获取该图片的父路径名
//                String parentName = new File(path).getParentFile().getName();
                String parentName = new File(path).getParentFile().getPath();
                //根据父路径名将图片放入到mGruopMap中
                if (!imgs.containsKey(parentName)) {
                    List<String> chileList = new ArrayList<String>();
                    chileList.add(path);
                    imgs.put(parentName, chileList);
                } else {
                    imgs.get(parentName).add(path);
                }
            } while (mCursor.moveToNext());
        }
        mCursor.close();
        return imgs;
    }
}
