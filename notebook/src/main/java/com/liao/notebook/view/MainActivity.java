package com.liao.notebook.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.liao.notebook.R;

public class MainActivity extends AppCompatActivity
{
    private Fragment mMainFragment;
    private FloatingActionButton mFloatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("记事本");
        setSupportActionBar(toolbar);
        initView();
        initEvent();
        showContent();
    }

    private void initView()
    {
        mFloatButton = (FloatingActionButton) findViewById(R.id.fab);
    }

    private void initEvent()
    {
        mFloatButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this, NewNoteActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 显示记事本列表的Fragment
     */
    private void showContent()
    {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if (mMainFragment == null)
        {
            mMainFragment = new MainFragment();
            transaction.add(R.id.frame_content, mMainFragment);
        }
        transaction.show(mMainFragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        switch (id)
        {
            case R.id.action_search:
                break;
            case R.id.action_editor:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
