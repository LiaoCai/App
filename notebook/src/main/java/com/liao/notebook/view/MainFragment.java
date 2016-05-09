package com.liao.notebook.view;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liao.notebook.R;
import com.liao.notebook.presenter.NoteBookAdapter;
import com.liao.notebook.presenter.NoteProvider;
import com.liao.notebook.utils.NoteDBHelper;
import com.liao.notebook.utils.ShowDialogOrMsg;

/**
 * 显示记事本列表的主布局
 * Created by liao on 2016-5-2 0002.
 */
public class MainFragment extends Fragment
{
    private RecyclerView mRvNote;

    private NoteBookAdapter mNoteAdapter;
    private Cursor mCursor;
    private static final String KEY_POSITION = "position";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        mRvNote = (RecyclerView) rootView.findViewById(R.id.rvNote);
        initLoader();
        initRvNoteEvent();
        return rootView;
    }

    private void initRvNoteEvent()
    {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity());
        mRvNote.setLayoutManager(manager);

        if (mNoteAdapter == null)
        {
            mCursor = getActivity().getContentResolver().query(
                    NoteProvider.URI_NOTE_ALL, null, null, null, null);

            mNoteAdapter = new NoteBookAdapter(getActivity(), mCursor, 1);
        }

        mNoteAdapter.onItemClickListener(new NoteBookAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                Intent intent = new Intent(getActivity(), ShowNoteActivity.class);
                intent.putExtra(KEY_POSITION, position);
                getActivity().startActivity(intent);
            }
        });
        mNoteAdapter.onItemLongClickListener(new NoteBookAdapter.OnItemLongClickListener()
        {
            @Override
            public void onItemLongClick(View view, int position)
            {
                mCursor = getContext().getContentResolver().query(
                        NoteProvider.URI_NOTE_ALL, null, null, null, null);
                if (mCursor == null)
                {
                    return;
                }
                mCursor.moveToPosition(position);
                String title = mCursor.getString(mCursor.getColumnIndex(NoteDBHelper.TITLE));
                int id = mCursor.getInt(mCursor.getColumnIndex(NoteDBHelper.ID));

                ShowDialogOrMsg.showDialog(getActivity(), title, R.mipmap.ic_delete_warning, id);
            }
        });

        mRvNote.setAdapter(mNoteAdapter);

    }

    private void initLoader()
    {
        getLoaderManager().initLoader(1, null, new LoaderManager.LoaderCallbacks<Cursor>()
        {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args)
            {
                return new CursorLoader(getActivity(), NoteProvider.URI_NOTE_ALL, null, null, null, null);
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data)
            {
                mNoteAdapter.swapCursor(data);
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader)
            {
                mNoteAdapter.swapCursor(null);
            }
        });
    }

    @Override
    public void onStart()
    {
        super.onStart();
        getActivity().getContentResolver().notifyChange(NoteProvider.URI_NOTE_ALL, null);
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        if (mCursor != null && !mCursor.isClosed())
        {
            mCursor.close();
        }
    }
}
