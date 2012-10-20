package com.example.sqliteconnect;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MainActivity_1 extends SQLiteOpenHelper {
	    private final static String DATABASE_NAME="sec_db";
	    private final static int DATABASE_VERSION=1;
	    private final static String TABLE_NAME="sec_pwd";
	    public final static String FIELD_ID="_id"; 
	    public final static String FIELD_TITLE="sec_Title";
	    
	    
	    public MainActivity_1(Context context) throws Exception
	    {
	        super(context, DATABASE_NAME,null, DATABASE_VERSION);
//	       SQLiteDatabase.openDatabase("D:"+File.separator+"sec_db.db" , null, SQLiteDatabase.OPEN_READWRITE);
			String dbDirPath = "/data/data/com.example.sqliteconnect/databases";
			  File dbDir = new File(dbDirPath);
			  if(!dbDir.exists()) // 如果不存在该目录则创建
			     dbDir.mkdir();
			  // 打开静态数据库文件的输入流
			  InputStream is = context.getResources().openRawResource(R.raw.sec_db);
			  // 打开目标数据库文件的输出流
			  FileOutputStream os = new FileOutputStream(dbDirPath+"/sec_db");
			  byte[] buffer = new byte[1024];
			  int count = 0;
			  // 将静态数据库文件拷贝到目的地
			  while ((count = is.read(buffer)) > 0) {
			    os.write(buffer, 0, count);
			  }
			  is.close();
			  os.close();
		  }
	    @Override
	    public void onCreate(SQLiteDatabase db) {
	        // TODO Auto-generated method stub
//	        String sql="Create table "+TABLE_NAME+"("+FIELD_ID+" integer primary key autoincrement,"
//	        +FIELD_TITLE+" text );";
//	        db.execSQL(sql);
	    	
	    }
	
	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	        // TODO Auto-generated method stub
	        String sql=" DROP TABLE IF EXISTS "+TABLE_NAME;
	        db.execSQL(sql);
//	        onCreate(db);
	    }
	/**
	 * 查询
	 * @return
	 */
	    public Cursor select()
	    {
	        SQLiteDatabase db=this.getReadableDatabase();
	        Cursor cursor=db.query(TABLE_NAME, null, null, null, null, null,  " _id desc");
	        return cursor;
	    }
	    /**
	     * 添加
	     * @param Title
	     * @return
	     */
	    public long insert(String Title)
	    {
	        SQLiteDatabase db=this.getWritableDatabase();
	        ContentValues cv=new ContentValues(); 
	        cv.put(FIELD_TITLE, Title);
	        long row=db.insert(TABLE_NAME, null, cv);
	        return row;
	    }
	    /**
	     * 删除
	     * @param id
	     */
	    public void delete(int id)
	    {
	        SQLiteDatabase db=this.getWritableDatabase();
	        String where=FIELD_ID+"=?";
	        String[] whereValue={Integer.toString(id)};
	        db.delete(TABLE_NAME, where, whereValue);
	    }
	    /**
	     * 更新
	     * @param id
	     * @param Title
	     */
	    public void update(int id,String Title)
	    {
	        SQLiteDatabase db=this.getWritableDatabase();
	        String where=FIELD_ID+"=?";
	        String[] whereValue={Integer.toString(id)};
	        ContentValues cv=new ContentValues(); 
	        cv.put(FIELD_TITLE, Title);
	        db.update(TABLE_NAME, cv, where, whereValue);
	    }	   
}
