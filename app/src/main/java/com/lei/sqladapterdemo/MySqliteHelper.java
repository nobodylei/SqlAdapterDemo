package com.lei.sqladapterdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yanle on 2018/2/8.
 */

public class MySqliteHelper extends SQLiteOpenHelper{


    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public MySqliteHelper(Context context) {
        super(context,Constant.DATABASE_NAME,null,Constant.DATABASE_VERSION);
    }

    /*
    数据库创建时回调的方法
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+ Constant.TABLE_NAME +"( " +
                Constant._ID + " integer primary key, " +
                Constant.NAME + " varchar(10)," +
                Constant.AGE + " integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
