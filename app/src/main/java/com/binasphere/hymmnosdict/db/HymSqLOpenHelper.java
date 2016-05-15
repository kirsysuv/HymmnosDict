package com.binasphere.hymmnosdict.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.binasphere.hymmnosdict.common.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HymSqLOpenHelper extends SQLiteOpenHelper {

    private static final String DEBUG_TAG =HymSqLOpenHelper.class.getSimpleName();
    private static boolean mLoaded;
    private Context mContext;

    public HymSqLOpenHelper(Context context) {
        super(context, DBInfo.DB_NAME, null, DBInfo.DB_VERSION);
        mContext = context;
        if (!mLoaded) {
            getLoaded();
        }
    }

    public boolean getLoaded() {
        LogUtil.d(DEBUG_TAG, "getLoaded run");
        // 获取数据库文件的路径，文件不存在也会获得
        File file = mContext.getDatabasePath(DBInfo.DB_NAME);
        // 数据库文件已经存在，不需要载入
        if (file.exists()) {
            return mLoaded = true;
        }
        // 获取数据库路径， /data/data/包名/databases
        File path = new File(file.getParent());
        // 确保创建数据库文件夹
        if (!path.exists()) {
            path.mkdirs();
        }
        // 从assets文件夹中复制数据库
        InputStream is;
        FileOutputStream fos;
        try {
            is = mContext.getAssets().open(DBInfo.DB_NAME);
            fos = new FileOutputStream(file);
            byte[] buf = new byte[1024 * 8];
            int len;
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.close();
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("从Assets中获取数据库失败");
        }

        return mLoaded = true;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
