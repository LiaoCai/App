package com.liao.notebook.view;

import android.content.ContentValues;
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
import android.widget.EditText;
import android.widget.ImageView;

import com.liao.notebook.R;
import com.liao.notebook.presenter.NoteProvider;
import com.liao.notebook.utils.NoteDBHelper;
import com.liao.notebook.utils.ShowDialogOrMsg;

/**
 * Created by liao on 2016-5-4 0004.
 */
public class NewNoteActivity extends AppCompatActivity
{
    private static final int REQUEST_LOAD_IMAGE = 1;

    private EditText mEditTitle;
    private EditText mEditContent;
    private ImageView mImageShow;

    private String picPath;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all);
        setToolbar();
        initView();
        mEditContent.requestFocus();
    }

    private void setToolbar()
    {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarShow);
        if (toolbar == null)
        {
            return;
        }
        toolbar.setTitle("新建");
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
        int id = item.getItemId();
        switch (id)
        {
            case R.id.action_save:
                writeToDatabase();
                break;
            case R.id.action_insert:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_LOAD_IMAGE);
                break;
            case R.id.action_delete:
                mEditTitle.setText("");
                mEditContent.setText("");
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void writeToDatabase()
    {
        String title = mEditTitle.getText().toString();
        String content = mEditContent.getText().toString();

        if (TextUtils.isEmpty(title) && TextUtils.isEmpty(content) && picPath == null)
        {
            ShowDialogOrMsg.showToast(getApplicationContext(), "无内容");
            return;
        }else
        {
            ContentValues cv = new ContentValues();
            cv.put(NoteDBHelper.TITLE, title);
            cv.put(NoteDBHelper.CONTENT, content);
            cv.put(NoteDBHelper.IMAGE, picPath);
            getContentResolver().insert(NoteProvider.URI_NOTE_ALL, cv);
        }
        ShowDialogOrMsg.showToast(getApplicationContext(), "已保存");

        finish();
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

//    @Override
//    public void onBackPressed()
//    {
//        writeToDatabase();
//        super.onBackPressed();
//    }
}
