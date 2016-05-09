package com.liao.developernews.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.liao.developernews.R;
import com.liao.developernews.model.ItemBean;
import com.liao.developernews.presenter.CustomRecycleAdapter;
import com.liao.developernews.utils.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现主界面第一页功能
 * Created by liao on 2016-4-27 0027.
 */
public class FragmentHomeLeft extends Fragment
{
    /**
     * 展示首页的轮播图片
     */
    private ViewPager mViewPager;
    /**
     * 装载轮播图片的ViewGroup
     */
    private LinearLayout mImageGroup;
    /**
     * 轮播图片的指示器
     */
    private ImageView[] imgIndicators;
    /**
     * 显示在轮播图片上的文字
     */
    private TextView mTextOnImage;
    /**
     * 将每一幅图以View添加到List中
     */
    private List<View> mViewList;
    /**
     * 泛型为ItemBean的List
     */
    private List<ItemBean> mItemList;

    private static final String URL = "url";
    private static final String TITLE = "title";

    private String[] texts = {"这是第一页", "这是第二页", "这是第三页" ,"这是第四页"};

    private int[] imagesId = {
            R.drawable.item1, R.drawable.item2, R.drawable.item3, R.drawable.item4};

    private RecyclerView mRecyclerViewLeft;

    private CustomRecycleAdapter mRecyclerAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_home_left, container, false);
        mRecyclerViewLeft = (RecyclerView) rootView.findViewById(R.id.recycleViewLeft);
        addViewToList();
        addItemToList();
        initRecyclerViewEvent();
        addHeaderViewForRecyclerView();
        initViewPageEvent();
        initPageIndicator();
        return rootView;
    }

    /**
     * 设置RecyclerView
     */
    protected void initRecyclerViewEvent()
    {
        // RecyclerView的布局方式
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerViewLeft.setLayoutManager(mLayoutManager);
        // RecyclerView的适配器
        mRecyclerAdapter = new CustomRecycleAdapter(mItemList, getActivity());
        mRecyclerViewLeft.setAdapter(mRecyclerAdapter);
        // 设置点击事件
        mRecyclerAdapter.onItemClickListener(new CustomRecycleAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                ItemBean itemBean = mItemList.get(position);
                Intent intent = new Intent(getActivity(), BrowserContentActivity.class);
                intent.putExtra(URL, itemBean.getUrl());
                intent.putExtra(TITLE, itemBean.getTitle());
                getActivity().startActivity(intent);
            }
        });
        // 分割线
        DividerItemDecoration decoration = new DividerItemDecoration(DividerItemDecoration.VERTICAL);
        decoration.setSize(2);
        decoration.setColor(0xFFDDDDDD);
        mRecyclerViewLeft.addItemDecoration(decoration);
    }

    /**
     * 设置RecyclerView的头部
     */
    protected void addHeaderViewForRecyclerView()
    {
        LayoutInflater inflater = getLayoutInflater(null);
        View headerView = inflater.inflate(R.layout.item_picture, mRecyclerViewLeft, false);
        // findViewById操作
        mViewPager = (ViewPager) headerView.findViewById(R.id.viewPagerImage);
        mImageGroup = (LinearLayout) headerView.findViewById(R.id.imageGroup);
        mTextOnImage = (TextView) headerView.findViewById(R.id.txtOnImage);
        // 显示文字
        mTextOnImage.setText(texts[0]);
        // 加载头布局
        mRecyclerAdapter.setHeaderView(headerView);
    }

    /**
     * 为ViewPager设置适配器和监听器
     */
    protected void initViewPageEvent()
    {
        // 轮播图片的监听器
        PageChangeListener changeListener = new PageChangeListener();
        mViewPager.addOnPageChangeListener(changeListener);
        // 轮播图片的适配器
        HomePageAdapter pageAdapter = new HomePageAdapter();
        mViewPager.setAdapter(pageAdapter);
        // 当前显示第一页
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(2);
    }
    /**
     * 初始化List<View> 并添加View对象
     */
    protected void addViewToList()
    {
        mViewList = new ArrayList<View>();
        // 将图片添加到ImageView中
        for (int i : imagesId)
        {
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageResource(i);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            mViewList.add(imageView);
        }
    }
    /**
     * 添加ItemBean到List
     */
    protected void addItemToList()
    {
        mItemList = new ArrayList<ItemBean>();

        for (int i = 0; i < 20; i++)
        {
            ItemBean bean = new ItemBean();
            bean.setTitle("java从入门到放弃，android从入门到转行");
            bean.setComment(i + "");
            bean.setLike(i + "");
            bean.setName("若等闲");
            bean.setUrl("http://toutiao.io/");
            mItemList.add(bean);
        }
    }
    /**
     * 初始化页面指示器
     */
    protected void initPageIndicator()
    {
        imgIndicators = new ImageView[4];
        for (int i = 0; i < imgIndicators.length; i++)
        {
            ImageView imageView = new ImageView(getActivity());
            if (i == 0)
            {
                imageView.setBackgroundResource(R.mipmap.ic_indicator_focused);
            } else
            {
                imageView.setBackgroundResource(R.mipmap.ic_indicator_unfocused);
            }
            imgIndicators[i] = imageView;
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            params.leftMargin = 10;
            params.rightMargin = 10;
            mImageGroup.addView(imageView, params);
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
            // 同时也切换文字显示
            mTextOnImage.setText(texts[position]);

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
    }
    /**
     * 内部类，继承PagerAdapter
     */
    class HomePageAdapter extends PagerAdapter
    {
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
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            container.removeView(mViewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position)
        {
            container.addView(mViewList.get(position), 0);
            return mViewList.get(position);
        }
    }

}
