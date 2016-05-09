package com.liao.developernews.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liao.developernews.R;
import com.liao.developernews.presenter.LauncherPagerAdapter;

public class LauncherActivity extends AppCompatActivity implements ILauncherView
{

    private ViewPager mViewPager;
    private LinearLayout mLinearGroup;
    private ImageView[] imgIndicators;

    private LauncherPagerAdapter mPagerAdapter;

    private static final String LAUNCHES_KEY = "first";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        //判断是否是第一次启动App
        if (!isFirstLaunched())
        {
            startMainPage();
        }

        initView();
        initEvent();
        initPageIndicator();
    }

    protected void initView()
    {
        mViewPager = (ViewPager) findViewById(R.id.viewPagerLauncher);
        //页面指示器的ViewGroup
        mLinearGroup = (LinearLayout) findViewById(R.id.viewGroup);
    }

    protected void initEvent()
    {
        mPagerAdapter = new LauncherPagerAdapter(this, this);
        mViewPager.setAdapter(mPagerAdapter);

        PageChangeListener changeListener = new PageChangeListener();
        mViewPager.addOnPageChangeListener(changeListener);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setCurrentItem(0);
    }
    /**
     * 初始化页面指示器
     */
    private void initPageIndicator()
    {
        imgIndicators = new ImageView[4];
        for (int i = 0; i < imgIndicators.length; i++)
        {
            ImageView imageView = new ImageView(this);
            if (i == 0)
            {
                imageView.setBackgroundResource(R.mipmap.ic_indicator_focused);
            }else
            {
                imageView.setBackgroundResource(R.mipmap.ic_indicator_unfocused);
            }
            imgIndicators[i] = imageView;
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            params.rightMargin = 10;
            params.leftMargin = 10;
            mLinearGroup.addView(imageView, params);
        }
    }

    private void setImageBackground(int position)
    {
        for (int i = 0; i < imgIndicators.length; i++)
        {
            if (i == position)
            {
                imgIndicators[i].setBackgroundResource(R.mipmap.ic_indicator_focused);
            }else
            {
                imgIndicators[i].setBackgroundResource(R.mipmap.ic_indicator_unfocused);
            }
        }
    }

    @Override
    public void startMainPage()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    protected boolean isFirstLaunched()
    {
        SharedPreferences settings = getSharedPreferences("", 0);
        boolean first_launches = settings.getBoolean(LAUNCHES_KEY, true);
        if (first_launches)
        {
            settings.edit().putBoolean(LAUNCHES_KEY, false).apply();
            return true;
        }else
        {
            return false;
        }
    }
    /**
     * 内部类，实现OnPageChangeListener接口
     */
    class PageChangeListener implements ViewPager.OnPageChangeListener
    {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageScrollStateChanged(int state) {}

        @Override
        public void onPageSelected(int position)
        {
            setImageBackground(position);
            TextView txtStart = (TextView) mPagerAdapter.getViewList().
                    get(position).findViewById(R.id.txtStart);
            //如果是最后一页
            if (position == imgIndicators.length - 1)
            {
                txtStart.setVisibility(View.VISIBLE);
            }else
            {
                txtStart.setVisibility(View.GONE);
            }
        }
    }
}
