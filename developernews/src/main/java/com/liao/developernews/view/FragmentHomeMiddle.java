package com.liao.developernews.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.liao.developernews.R;
import com.liao.developernews.model.ImagePic;
import com.liao.developernews.presenter.MiddleRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liao on 2016-4-27 0027.
 */
public class FragmentHomeMiddle extends Fragment
{
    private RecyclerView mRecyclerViewMid;

    private List<ImagePic> mImageList;

    private int[] mImages = {R.drawable.item1, R.drawable.item2, R.drawable.item3, R.drawable.item4};


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_home_middle, container, false);
        mRecyclerViewMid = (RecyclerView) rootView.findViewById(R.id.recycleViewMid);
        initList();
        initEvent();

        return rootView;
    }

    private void initEvent()
    {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        mRecyclerViewMid.setLayoutManager(manager);

        MiddleRecyclerAdapter adapter = new MiddleRecyclerAdapter(mImageList, getActivity());
        mRecyclerViewMid.setAdapter(adapter);

        adapter.onImageItemClickListener(new MiddleRecyclerAdapter.OnImageItemClickListener()
        {
            @Override
            public void onImageItemClick(View view, int position)
            {
                Toast.makeText(getContext(), position + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initList()
    {
        mImageList = new ArrayList<ImagePic>();
        for (int image : mImages)
        {
            ImagePic imagePic = new ImagePic();
            imagePic.setUrl(image);
            mImageList.add(imagePic);
        }
    }
}
