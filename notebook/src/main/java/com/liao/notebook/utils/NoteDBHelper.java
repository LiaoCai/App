package com.liao.notebook.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by liao on 2016-5-3 0003.
 */
public class NoteDBHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "notebook.db";
    public static final String TABLE_NAME = "notes";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String IMAGE = "image";
    public static final String ID = "_id";
    public static final int VERSION = 1;

    public NoteDBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "("
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + TITLE + " TEXT,"
                + CONTENT + " TEXT,"
                + IMAGE + " TEXT" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
