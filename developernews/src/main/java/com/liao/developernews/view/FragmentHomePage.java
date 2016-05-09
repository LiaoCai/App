package com.liao.developernews.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liao.developernews.R;
import com.liao.developernews.presenter.FragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liao on 2016-4-27 0027.
 */
public class FragmentHomePage extends Fragment
{
    private List<Fragment> mFragmentList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_home_page, container, false);
        initFragmentList();
        initView(rootView);
        return rootView;
    }

    protected void initFragmentList()
    {
        Fragment fragmentLeft = new FragmentHomeLeft();
        Fragment fragmentMiddle = new FragmentHomeMiddle();
        Fragment fragmentRight = new FragmentHomeRight();

        mFragmentList = new ArrayList<Fragment>();
        mFragmentList.add(fragmentLeft);
        mFragmentList.add(fragmentMiddle);
        mFragmentList.add(fragmentRight);
    }

    protected void initView(View rootView)
    {
        //初始化ToolBar
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        //加载右上角搜索按钮
        toolbar.inflateMenu(R.menu.menu_toolbar);
        toolbar.setTitle(R.string.app_title);
        //实例化FragmentPagerAdapter
        FragmentPagerAdapter fragmentAdapter = new FragmentAdapter(
                getActivity().getSupportFragmentManager(),
                mFragmentList, getResources().getStringArray(R.array.home_page_title));
        //初始化ViewPager，并设置Adapter
        ViewPager mViewPagerHome = (ViewPager) rootView.findViewById(R.id.viewPagerHome);
        mViewPagerHome.setAdapter(fragmentAdapter);
        //缓存页数
        mViewPagerHome.setOffscreenPageLimit(2);
        //设置默认显示第一页
        mViewPagerHome.setCurrentItem(0);
        //初始化TabLayout
        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        //设置
        tabLayout.setupWithViewPager(mViewPagerHome);

    }


}
