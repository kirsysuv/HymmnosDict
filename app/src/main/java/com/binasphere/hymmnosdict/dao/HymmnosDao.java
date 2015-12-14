package com.binasphere.hymmnosdict.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.binasphere.hymmnosdict.bean.HymmnosWord;
import com.binasphere.hymmnosdict.db.DBInfo;
import com.binasphere.hymmnosdict.db.HymSqLOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class HymmnosDao {
    private HymSqLOpenHelper mHelper;
    private static HymmnosDao dao;

    public HymmnosDao(Context context) {
        mHelper = new HymSqLOpenHelper(context);
    }

    public static HymmnosDao newInstance(Context context) {
        if (dao == null) {
            synchronized (HymmnosDao.class) {
                if (dao == null) {
                    dao = new HymmnosDao(context);
                }
            }
        }
        return dao;
    }

    public List<String> getAlphabets() {
        List<String> data = new ArrayList<String>();
        for (char i = 'A'; i < 'Z'; i++) {
            data.add(String.valueOf(i));
        }
        data.add(DBInfo.ALPHA_OTHERS);
        return data;
    }

    public List<String> getDialects() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        List<String> data = new ArrayList<String>();
        // SQL "select distinct dialect from t_dict"
        Cursor cursor = db.query(true, DBInfo.TABLE_NAME, new String[]{DBInfo.Colums.DIALECT}, null, null, null,
                null, null, null);
        while (cursor.moveToNext()) {
            data.add(cursor.getString(0));
        }
        cursor.close();
        return data;
    }

    public List<HymmnosWord> findAllWords() {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        List<HymmnosWord> data = new ArrayList<HymmnosWord>();
        Cursor cursor = db.query(DBInfo.TABLE_NAME, DBInfo.Projection, null, null, null, null, null);
        while (cursor.moveToNext()) {
            HymmnosWord hw = new HymmnosWord();
            hw.setHymmnos(cursor.getString(DBInfo.ProjectionIndex.HYMMNOS));
            hw.setExp_jp(cursor.getString(DBInfo.ProjectionIndex.EXP_JP));
            hw.setKatakana(cursor.getString(DBInfo.ProjectionIndex.KATAKANA));
            hw.setDialect(cursor.getString(DBInfo.ProjectionIndex.DIALECT));
            data.add(hw);
        }
        cursor.close();
        return data;

    }

    public List<HymmnosWord> findByWords(String words) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        List<HymmnosWord> data = new ArrayList<HymmnosWord>();
        Cursor cursor = db.query(DBInfo.TABLE_NAME, DBInfo.Projection, DBInfo.Colums.HYMMNOS + " like ?",
                new String[]{"%" + words + "%"}, null, null, null);
        while (cursor.moveToNext()) {
            HymmnosWord hw = new HymmnosWord();
            hw.setHymmnos(cursor.getString(DBInfo.ProjectionIndex.HYMMNOS));
            hw.setExp_jp(cursor.getString(DBInfo.ProjectionIndex.EXP_JP));
            hw.setKatakana(cursor.getString(DBInfo.ProjectionIndex.KATAKANA));
            hw.setDialect(cursor.getString(DBInfo.ProjectionIndex.DIALECT));
            data.add(hw);
        }
        cursor.close();
        return data;
    }

    public HymmnosWord findById(String id) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        HymmnosWord hw = new HymmnosWord();
        Cursor cursor = db.query(DBInfo.TABLE_NAME, DBInfo.Projection, DBInfo.Colums.ID + " =?", new String[]{id},
                null, null, null);
        if (cursor.moveToNext()) {
            hw.setHymmnos(cursor.getString(DBInfo.ProjectionIndex.HYMMNOS));
            hw.setExp_jp(cursor.getString(DBInfo.ProjectionIndex.EXP_JP));
            hw.setKatakana(cursor.getString(DBInfo.ProjectionIndex.KATAKANA));
            hw.setDialect(cursor.getString(DBInfo.ProjectionIndex.DIALECT));
            hw.setId(cursor.getString(DBInfo.ProjectionIndex.ID));
        }
        cursor.close();
        return hw;
    }

    public HymmnosWord findByHymmnos(String hymmnos) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        HymmnosWord hw = new HymmnosWord();
        Cursor cursor = db.query(DBInfo.TABLE_NAME, DBInfo.Projection, DBInfo.Colums.HYMMNOS + " =?",
                new String[]{hymmnos}, null, null, null);
        if (cursor.moveToNext()) {
            hw.setHymmnos(cursor.getString(DBInfo.ProjectionIndex.HYMMNOS));
            hw.setExp_jp(cursor.getString(DBInfo.ProjectionIndex.EXP_JP));
            hw.setKatakana(cursor.getString(DBInfo.ProjectionIndex.KATAKANA));
            hw.setDialect(cursor.getString(DBInfo.ProjectionIndex.DIALECT));
            hw.setId(cursor.getString(DBInfo.ProjectionIndex.ID));
        }
        cursor.close();
        return hw;
    }

    public List<HymmnosWord> findByDialect(String dialect) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        List<HymmnosWord> data = new ArrayList<HymmnosWord>();
        Cursor cursor = db.query(DBInfo.TABLE_NAME, DBInfo.Projection, DBInfo.Colums.DIALECT + " =?",
                new String[]{dialect}, null, null, null);
        while (cursor.moveToNext()) {
            HymmnosWord hw = new HymmnosWord();
            hw.setHymmnos(cursor.getString(DBInfo.ProjectionIndex.HYMMNOS));
            hw.setExp_jp(cursor.getString(DBInfo.ProjectionIndex.EXP_JP));
            hw.setKatakana(cursor.getString(DBInfo.ProjectionIndex.KATAKANA));
            hw.setDialect(cursor.getString(DBInfo.ProjectionIndex.DIALECT));
            data.add(hw);
        }
        cursor.close();
        return data;

    }

    public List<HymmnosWord> findByAlphabet(String alpha) {
        SQLiteDatabase db = mHelper.getWritableDatabase();
        List<HymmnosWord> data = new ArrayList<HymmnosWord>();
        Cursor cursor;
        if (DBInfo.ALPHA_OTHERS.equals(alpha)) {
            cursor = db.query(DBInfo.TABLE_NAME, DBInfo.Projection, DBInfo.Colums.ID + " <= ?",
                    new String[]{DBInfo.ID_OTHERS}, null, null, null);
        } else {
            cursor = db.query(DBInfo.TABLE_NAME, DBInfo.Projection, DBInfo.Colums.HYMMNOS + " like ?",
                    new String[]{alpha + "%"}, null, null, null);
        }
        while (cursor.moveToNext()) {
            HymmnosWord hw = new HymmnosWord();
            hw.setHymmnos(cursor.getString(DBInfo.ProjectionIndex.HYMMNOS));
            hw.setExp_jp(cursor.getString(DBInfo.ProjectionIndex.EXP_JP));
            hw.setKatakana(cursor.getString(DBInfo.ProjectionIndex.KATAKANA));
            hw.setDialect(cursor.getString(DBInfo.ProjectionIndex.DIALECT));
            data.add(hw);
        }
        cursor.close();
        return data;
    }

    public void close() {
        dao=null;
        mHelper.close();
    }
}
