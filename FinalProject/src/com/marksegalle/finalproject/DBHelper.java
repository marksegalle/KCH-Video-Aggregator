package com.marksegalle.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper {
	

	
 public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_URL = "url";
    private static final String TAG = "DBHelper";
    
    private static final String DATABASE_NAME = "VideosDB";
    private static final String DATABASE_TABLE = "videos";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE =
        "create table videos (_id integer primary key autoincrement, "
        + "name text not null,"
        + "url text not null);";
        
    private final Context context; 
    // -- end initializers
    
    private Helper DBH;
    private SQLiteDatabase db;
    public DBHelper(Context ctx) 
    {
        this.context = ctx;
        DBH = new Helper(context);
    }
    private static class Helper extends SQLiteOpenHelper 
    {
        Helper(Context context) 
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) 
        {
            db.execSQL(DATABASE_CREATE);
         
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, 
                              int newVersion) 
        {
            Log.w(TAG, "Upgrading database from version " + oldVersion 
                  + " to "
                  + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS titles");
            onCreate(db);
        }
    }     
	
  //---opens the database---
    public DBHelper open() throws SQLException 
    {
        db = DBH.getWritableDatabase();
        return this;
    }

    //---closes the database---    
    public void close() 
    {
        DBH.close();
    }
    
    //---insert a quote into the database---
    public long insertVideo(String name, String url) 
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_URL, url);
        return db.insert(DATABASE_TABLE, null, initialValues);
    }
    
    //---deletes a particular quotes---
    public boolean deleteVideo(long rowId) 
    {
        return db.delete(DATABASE_TABLE, KEY_ID + "=" + rowId, null) > 0;
    }
    
    //---retrieves all the quotes---
    public Cursor getAllVideos() 
    {
        return db.query(DATABASE_TABLE, new String[] {
        		KEY_ID, 
        		KEY_NAME, 
        		KEY_URL}, 
                null, 
                null, 
                null, 
                null, 
                null);
    }
    
    //---retrieves a particular quote---
    public Cursor getVideo(String name) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                		KEY_ID,
                		KEY_NAME,
                		KEY_URL
                		}, 
                		KEY_NAME + "='" + name + "'", 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    
    public Cursor getVideo(int rowId) throws SQLException 
    {
        Cursor mCursor =
                db.query(true, DATABASE_TABLE, new String[] {
                		KEY_ID,
                		KEY_NAME,
                		KEY_URL
                		}, 
                		KEY_ID + "=" + rowId, 
                		null,
                		null, 
                		null, 
                		null, 
                		null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    
    //---updates an quote---
    public boolean updateVideo(long rowId, String name, String url) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_URL, url);
        return db.update(DATABASE_TABLE, args, 
                         KEY_ID + "=" + rowId, null) > 0;
    }



    
}

