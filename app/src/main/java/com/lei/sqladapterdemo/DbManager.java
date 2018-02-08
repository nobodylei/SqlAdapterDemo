package com.lei.sqladapterdemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by yanle on 2018/2/8.
 */

public class DbManager {
    private static MySqliteHelper helper;

    public static MySqliteHelper getIntance(Context context) {
        if(helper == null) {
            helper = new MySqliteHelper(context);
        }
        return helper;
    }

    /**
     * 根据sql语句查询cursor对象
     * @param db 数据库对象
     * @param sql 查询的sql语句
     * @param selectionArgs 查询条件的占位符
     * @return 查询结果
     */
    public static Cursor selectDataBySql(SQLiteDatabase db,String sql,String[] selectionArgs) {
        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(sql,selectionArgs);
        }
        return cursor;
    }

    /**
     * 将查询的Cursor对象转换为List对集合
     * @param cursor 游标对象
     * @return 集合对象
     */
    public static List<Person> cursorToList(Cursor cursor) {
        List<Person> list = new ArrayList<Person>();
        //moveToNext() 如果返回true表示下一条记录存在，否则表示游标中的数据读取完毕
        while(cursor.moveToNext()) {
            //getColumnIndex(String columnName)根据参数中指定的字段名称，获取字段下标
            int columnIndex = cursor.getColumnIndex(Constant._ID);
            //getInt(int columnIndex) 根据参数中指定的字段下标，
            //获取对应int类型的value
            int _id = cursor.getInt(columnIndex);

            String name = cursor.getString(cursor.getColumnIndex(Constant.NAME));
            int age = cursor.getInt(cursor.getColumnIndex(Constant.AGE));

            Person person = new Person(_id,name,age);
            list.add(person);
        }
        cursor.close();
        return list;
    }
}
