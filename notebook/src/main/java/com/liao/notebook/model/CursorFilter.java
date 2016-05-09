package com.liao.notebook.model;

import android.database.Cursor;
import android.widget.Filter;

/**
 * Created by liao on 2016-5-4 0004.
 */

public class CursorFilter extends Filter
{

    CursorFilterClient mClient;

    public interface CursorFilterClient
    {
        CharSequence convertToString(Cursor cursor);

        Cursor runQueryOnBackgroundThread(CharSequence constraint);

        Cursor getCursor();

        void changeCursor(Cursor cursor);
    }

    public CursorFilter(CursorFilterClient client)
    {
        mClient = client;
    }

    @Override
    public CharSequence convertResultToString(Object resultValue)
    {
        return mClient.convertToString((Cursor) resultValue);
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint)
    {
        Cursor cursor = mClient.runQueryOnBackgroundThread(constraint);

        FilterResults results = new FilterResults();
        if (cursor != null)
        {
            results.count = cursor.getCount();
            results.values = cursor;
        } else
        {
            results.count = 0;
            results.values = null;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results)
    {
        Cursor oldCursor = mClient.getCursor();

        if (results.values != null && results.values != oldCursor)
        {
            mClient.changeCursor((Cursor) results.values);
        }
    }
}

