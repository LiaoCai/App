package com.liao.developernews.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.liao.developernews.R;

/**
 * Created by liao on 2016-4-26 0026.
 */
public class MainActivity extends AppCompatActivity
{
    private DrawerLayout mDrawerLayout;
    private ImageButton mImgBtnAvatar;

    private LinearLayout mLinearHomePage;
    private LinearLayout mLinearGiftExchange;
    private LinearLayout mLinearMyShare;
    private LinearLayout mLinearSetting;

    private Fragment mFragmentHomePage;
    private Fragment mFragmentGiftExchange;
    private Fragment mFragmentMyShare;

    private int currentSelectedItemId = R.id.item_home_page;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        setDefaultSelectedItemAndFragment(currentSelectedItemId);
    }

    /**
     * 都是findViewById
     */
    private void initView()
    {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mImgBtnAvatar = (ImageButton) findViewById(R.id.imgBtnAvatar);

        mLinearHomePage = (LinearLayout) findViewById(R.id.item_home_page);
        mLinearGiftExchange = (LinearLayout) findViewById(R.id.item_gift_exchange);
        mLinearMyShare = (LinearLayout) findViewById(R.id.item_my_shares);
        mLinearSetting = (LinearLayout) findViewById(R.id.item_setting);
    }

    /**
     * 实例化内部类，设置View的点击事件
    */
    private void initEvent()
    {
        ItemOnClickListener clickListener = new ItemOnClickListener();
        mLinearGiftExchange.setOnClickListener(clickListener);
        mLinearMyShare.setOnClickListener(clickListener);
        mLinearHomePage.setOnClickListener(clickListener);
        mLinearSetting.setOnClickListener(clickListener);
    }

    /**
     * 点击不同的Item项改变Fragment显示
     * @param resId 处理点击事件的View ID
     */
    protected void changeFragment(int resId)
    {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragment(transaction);
        switch (resId)
        {
            case R.id.item_home_page:
                if (mFragmentHomePage == null)
                {
                    mFragmentHomePage = new FragmentHomePage();
                    transaction.add(R.id.main_content_frame, mFragmentHomePage);
                }
                transaction.show(mFragmentHomePage);
                break;
            case R.id.item_gift_exchange:
                if (mFragmentGiftExchange == null)
                {
                    mFragmentGiftExchange = new FragmentGiftExchange();
                    transaction.add(R.id.main_content_frame, mFragmentGiftExchange);
                }
                transaction.show(mFragmentGiftExchange);
                break;
            case R.id.item_my_shares:
                if (mFragmentMyShare == null)
                {
                    mFragmentMyShare = new FragmentMyShare();
                    transaction.add(R.id.main_content_frame, mFragmentMyShare);
                }
                transaction.show(mFragmentMyShare);
                break;
            case R.id.item_setting:

            default:
                break;
        }
        transaction.commit();
    }

    /**
     * 设置默认选中的Item项和Fragment
     * @param resId View的ID
     */
    protected void setDefaultSelectedItemAndFragment(int resId)
    {
        changeFragment(resId);
        mLinearHomePage.setSelected(true);
    }
    /**
     * 隐藏所有Fragment
     * @param transaction 传入transaction
     */
    protected void hideFragment(FragmentTransaction transaction)
    {
        if (mFragmentHomePage != null)
        {
            transaction.hide(mFragmentHomePage);
        }
        if (mFragmentGiftExchange != null)
        {
            transaction.hide(mFragmentGiftExchange);
        }
        if (mFragmentMyShare != null)
        {
            transaction.hide(mFragmentMyShare);
        }
    }

    /**
     * 清除已选中的状态
     */
    protected void clearAlreadySelectedItem()
    {
        mLinearHomePage.setSelected(false);
        mLinearGiftExchange.setSelected(false);
        mLinearMyShare.setSelected(false);
    }

    @Override
    public void onBackPressed()
    {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START))
        {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * 内部类，实现OnClickListener接口
     */
    class ItemOnClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            if (v.getId() != currentSelectedItemId)
            {
                currentSelectedItemId = v.getId();
            }
            clearAlreadySelectedItem();
            changeFragment(v.getId());
            switch (v.getId())
            {
                //点击Item项的时候将该项设置为Selected状态
                case R.id.item_home_page:
                    mLinearHomePage.setSelected(true);
                    break;
                case R.id.item_gift_exchange:
                    mLinearGiftExchange.setSelected(true);
                    break;
                case R.id.item_my_shares:
                    mLinearMyShare.setSelected(true);
                    break;
                default:
                    break;
            }
            //将LeftDrawer缩回去
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }
    }

}
