package com.liao.developernews.presenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by liao on 2016-4-27 0027.
 */
public class FragmentAdapter extends FragmentPagerAdapter
{
    private List<Fragment> fragmentList;
    private String[] titles;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragmentList, String[] titles)
    {
        super(fm);
        this.fragmentList = fragmentList;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position)
    {
        return fragmentList.get(position);
    }

    @Override
    public int getCount()
    {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return titles[position];
    }
}
