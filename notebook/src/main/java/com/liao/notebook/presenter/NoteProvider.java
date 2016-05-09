package com.liao.notebook.presenter;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.liao.notebook.utils.NoteDBHelper;

/**
 * Created by liao on 2016-5-4 0004.
 */
public class NoteProvider extends ContentProvider
{
    private static final String AUTHORITY = "com.liao.notebook.presenter.noteprovider";
    public static final Uri URI_NOTE_ALL = Uri.parse("content://" + AUTHORITY + "/note");

    private static UriMatcher sMatcher;

    private SQLiteDatabase mDatabase;

    private static final int READABLE = 1;
    private static final int WRITABLE = 2;

    private static final int NOTE_ALL=0;
    private static final int NOTE_ONE=1;

    private SQLiteDatabase getDatabase(int permission)
    {
        NoteDBHelper mDBHelper = new NoteDBHelper(getContext());
        switch (permission)
        {
            case READABLE:
                return mDBHelper.getReadableDatabase();
            case WRITABLE:
                return mDBHelper.getWritableDatabase();
            default:
                break;
        }
        return null;
    }

    static
    {
        sMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sMatcher.addURI(AUTHORITY, "note/", NOTE_ALL);
        sMatcher.addURI(AUTHORITY, "note/#", NOTE_ONE);
    }
    @Override
    public boolean onCreate()
    {
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder)
    {
        switch (sMatcher.match(uri))
        {
            case NOTE_ALL:
                break;
            case NOTE_ONE:
                long id = ContentUris.parseId(uri);
                selection = "_id=?";
                selectionArgs = new String[]{String.valueOf(id)};
                break;
            default:
                throw new IllegalArgumentException("Wrong Uri" + uri);
        }
        mDatabase = getDatabase(READABLE);
        if (mDatabase == null)
        {
            return null;
        }
        Cursor cursor = mDatabase.query(NoteDBHelper.TABLE_NAME, projection, selection,
                selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), URI_NOTE_ALL);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri)
    {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values)
    {
        if (sMatcher.match(uri) != NOTE_ALL)
        {
            throw new IllegalArgumentException("Wrong Uri" + uri);
        }
        mDatabase = getDatabase(READABLE);
        if (mDatabase == null)
        {
            return null;
        }
        long rawId = mDatabase.insert(NoteDBHelper.TABLE_NAME, null, values);
        if (rawId > 0)
        {
            notifyDataSetChanged();
            return ContentUris.withAppendedId(uri, rawId);
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs)
    {
        if (mDatabase == null)
        {
            return 0;
        }
        int deleteResult = mDatabase.delete(NoteDBHelper.TABLE_NAME, selection, selectionArgs);
        notifyDataSetChanged();
        return deleteResult;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs)
    {
        if (mDatabase == null)
        {
            return 0;
        }
        int updateResult = mDatabase.update(NoteDBHelper.TABLE_NAME, values, selection, selectionArgs);
        notifyDataSetChanged();
        return updateResult;
    }

    private void notifyDataSetChanged()
    {
        getContext().getContentResolver().notifyChange(URI_NOTE_ALL, null);
    }
}
