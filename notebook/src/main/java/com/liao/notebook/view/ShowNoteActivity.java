package com.liao.notebook.view;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.liao.notebook.R;
import com.liao.notebook.presenter.NoteProvider;
import com.liao.notebook.utils.NoteDBHelper;
import com.liao.notebook.utils.ShowDialogOrMsg;

/**
 * Created by liao on 2016-5-3 0003.
 */
public class ShowNoteActivity extends AppCompatActivity
{
    private EditText mEditTitle;
    private EditText mEditContent;
    private ImageView mImageShow;

    private Cursor mCursor;

    private String picPath;

    private static final int REQUEST_LOAD_IMAGE = 1;
    private static final String KEY_POSITION = "position";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);
        setToolbar();
        initView();
        readFromDatabase();
    }

    private void setToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarShow);
        if (toolbar == null)
        {
            return;
        }
        toolbar.setTitle("详情");
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onBackPressed();
            }
        });
    }

    private void initView()
    {
        mEditTitle = (EditText) findViewById(R.id.etTitle);
        mEditContent = (EditText) findViewById(R.id.etContent);
        mImageShow = (ImageView) findViewById(R.id.ivShow);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_show_all, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int itemId = item.getItemId();
        switch (itemId)
        {
            case R.id.action_save:
                updateDatabase();
                finish();
                break;
            case R.id.action_insert:
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_LOAD_IMAGE);
                break;
            case R.id.action_delete:
                deleteRaw();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 点击RecyclerView的Item项进入Activity后，将position传递过来
     * 将游标移至position处
     */
    private void getCurrentCursor()
    {
        Intent data = getIntent();
        int position = data.getIntExtra(KEY_POSITION, 1);
        mCursor = getContentResolver().query(NoteProvider.URI_NOTE_ALL, null, null, null, null);
        if (mCursor == null)
        {
            return;
        }
        mCursor.moveToPosition(position);
    }

    /**
     * 读取数据库，并显示在当前Activity
     * 进入时隐藏输入法
     */
    private void readFromDatabase()
    {
        getCurrentCursor();
        String title = mCursor.getString(mCursor.getColumnIndex(NoteDBHelper.TITLE));
        String content = mCursor.getString(mCursor.getColumnIndex(NoteDBHelper.CONTENT));
        String picPath = mCursor.getString(mCursor.getColumnIndex(NoteDBHelper.IMAGE));

        mEditTitle.setText(title);
        mEditContent.setText(content);
        mImageShow.setImageBitmap(BitmapFactory.decodeFile(picPath));

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(mEditContent.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * 删除一行
     */
    private void deleteRaw()
    {
//        getContentResolver().delete(NoteProvider.URI_NOTE_ALL,
//                NoteDBHelper.TITLE + " = ?" + "AND " + NoteDBHelper.CONTENT + " = ?",
//                new String[]{title, content});
        getCurrentCursor();
        int id = mCursor.getInt(mCursor.getColumnIndex(NoteDBHelper.ID));

        getContentResolver().delete(NoteProvider.URI_NOTE_ALL, NoteDBHelper.ID + " = " + id, null);

        ShowDialogOrMsg.showToast(getApplicationContext(), "已删除");

        finish();

    }

    /**
     * 更新数据库
     */
    private void updateDatabase()
    {
        getCurrentCursor();
        int id = mCursor.getInt(mCursor.getColumnIndex(NoteDBHelper.ID));

        String title = mEditTitle.getText().toString();
        String content = mEditContent.getText().toString();

        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(content) && picPath == null)
        {
            ShowDialogOrMsg.showToast(getApplicationContext(), "无内容");
            return;
        }

        ContentValues cv = new ContentValues();
        cv.put(NoteDBHelper.TITLE, title);
        cv.put(NoteDBHelper.CONTENT, content);
        if (picPath != null)
        {
            cv.put(NoteDBHelper.IMAGE, picPath);
        }

        getContentResolver().update(NoteProvider.URI_NOTE_ALL, cv, NoteDBHelper.ID + " = " + id, null);

        ShowDialogOrMsg.showToast(getApplicationContext(), "已更新");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null)
        {
            return;
        }
        if (requestCode == REQUEST_LOAD_IMAGE && resultCode == RESULT_OK)
        {
            Uri selectPic = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectPic, filePathColumn, null, null, null);
            cursor.moveToFirst();
            picPath = cursor.getString(cursor.getColumnIndex(filePathColumn[0]));
            cursor.close();
            mImageShow.setImageBitmap(BitmapFactory.decodeFile(picPath));
        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if (mCursor != null && !mCursor.isClosed())
        {
            mCursor.close();
        }
    }

//    @Override
//    public void onBackPressed()
//    {
//        updateDatabase();
//        super.onBackPressed();
//    }
}
