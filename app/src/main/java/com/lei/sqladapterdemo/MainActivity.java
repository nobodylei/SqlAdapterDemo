package com.lei.sqladapterdemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.io.File;
import java.util.List;

/**
 * 演示sdcard中数据表中的数据适配到listview中
 */
public class MainActivity extends AppCompatActivity {
    private MySqliteHelper helper;
    private ListView lv;
    private SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = DbManager.getIntance(this);
        lv = findViewById(R.id.lv);
        //获取数据库查询的数据源
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath()
//                + File.separator + "info.db";

        //openDatabase(String path, CursorFactory factory, int flags,）
        //  String path 表示当前打开数据库的存放路径
        //  CursorFactory factory 游标工厂，可以为null
        //  int flags 表示打开数据库的操作模式
//        db = SQLiteDatabase.openDatabase(path,null,SQLiteDatabase.OPEN_READONLY);
//        Cursor cursor = db.rawQuery("select * from " + Constant.TABLE_NAME,null);

        //2，将数据加载到适配器中
        /*
        SimpleCursorAdapter(Context context, int layout, Cursor c, String[] from,
            int[] to, int flags)
            context 上下文对象
            int layout 表示适配器控件中每项item的布局id
            Cursor c 表示Cursor数据源
            String[] from 表示Cursor中数据表字段的数组
            int[] to 表示展示字段对应值的控件资源的id
            int flags 设置适配器的标志
         */
        db = helper.getWritableDatabase();
        String sql = "select * from " + Constant.TABLE_NAME;
        Cursor cursor = cursor = db.query(Constant.TABLE_NAME,null,Constant._ID + ">?",new String[]{"10"},
                null,null,Constant._ID + " desc");
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.layout,
                cursor,new String[]{Constant._ID,Constant.NAME,Constant.AGE},
                new int[]{R.id.tv_id,R.id.tv_name,R.id.tv_age},
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);//观察者模式

        //3，将适配器的数据加载到控件
        lv.setAdapter(adapter);
//        cursor.close();
       db.close();

    }
    public void createDB(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = null;
        List<Person> list;
        switch(view.getId()) {
            case R.id.button:
                for(int i = 1;i <= 50;i ++) {
                    String sql = "insert into " + Constant.TABLE_NAME + " values(" +
                            i+ ",'zhangsan"+ i +"',20)";
                    db.execSQL(sql);
                }
                break;
            case R.id.button2:
                db = helper.getWritableDatabase();
//                String sql = "select * from " + Constant.TABLE_NAME;
//                Log.i("tag",sql);
//                cursor = DbManager.selectDataBySql(db,sql,null);
//                SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,R.layout.layout,
//                        cursor,new String[]{Constant._ID,Constant.NAME,Constant.AGE},
//                        new int[]{R.id.tv_id,R.id.tv_name,R.id.tv_age},
//                        SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);//观察者模式
//
//                //3，将适配器的数据加载到控件
//                lv.setAdapter(adapter);
//                cursor.close();
                cursor = db.query(Constant.TABLE_NAME,null,Constant._ID + ">?",new String[]{"10"},
                        null,null,Constant._ID + " desc");
                list = DbManager.cursorToList(cursor);
                for(Person person:list) {
                    Log.i("tag", person.toString());
                }

                break;
        }

       db.close();
    }
}
