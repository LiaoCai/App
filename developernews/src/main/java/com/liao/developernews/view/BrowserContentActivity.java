package com.liao.developernews.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.liao.developernews.R;

/**
 * Created by liao on 2016-5-1 0001.
 */
public class BrowserContentActivity extends AppCompatActivity
{
    private static final String URL = "url";
    private static final String TITLE = "title";

    private WebView mWebView;

    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser_content);
        initView();
        setWebView();
        setToolbar();
    }

    private void initView()
    {
        mWebView = (WebView) findViewById(R.id.webViewContent);
        mToolbar = (Toolbar) findViewById(R.id.toolbarContent);
    }

    private void setWebView()
    {
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl(getIntent().getStringExtra(URL));
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebView.getSettings().setJavaScriptEnabled(true);
    }

    private void setToolbar()
    {
        mToolbar.setTitle("浏览界面");
        mToolbar.setTitleTextAppearance(this, R.style.toolbar_title);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onKeyDown(KeyEvent.KEYCODE_BACK, null);
            }
        });
        setSupportActionBar(mToolbar);

    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK && mWebView.canGoBack())
        {
            mWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_browser_content, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_share:
                Toast.makeText(getApplicationContext(), "分享", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_item_refresh:
                mWebView.reload();
                break;
            case R.id.action_item_open_use_browser:
                break;
            case R.id.action_item_report:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
