package com.liao.developernews.presenter;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.liao.developernews.R;
import com.liao.developernews.view.ILauncherView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liao on 2016-4-26 0026.
 */
public class LauncherPagerAdapter extends PagerAdapter implements View.OnClickListener
{
    private List<View> mViewList;
    private ILauncherView mLauncherView;

    public LauncherPagerAdapter(Context context, ILauncherView launcherView)
    {
        this.mLauncherView = launcherView;
        mViewList = new ArrayList<View>();
        TypedArray array = context.getResources().obtainTypedArray(R.array.launcher_images);
        //初始化每页显示的View
        for (int i = 0; i < array.length(); i++)
        {
            View view = LayoutInflater.from(context).inflate(R.layout.activity_launcher_page_items, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imagePage);
            imageView.setImageResource(array.getResourceId(i, 0));
            view.findViewById(R.id.txtStart).setOnClickListener(this);
            mViewList.add(view);
        }
        array.recycle();
    }

    public List<View> getViewList()
    {
        return mViewList;
    }

    @Override
    public int getCount()
    {
        return mViewList == null ? 0 : mViewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        container.addView(mViewList.get(position), 0);
        return mViewList.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView(mViewList.get(position));
    }

    @Override
    public void onClick(View v)
    {
        mLauncherView.startMainPage();
    }
}
