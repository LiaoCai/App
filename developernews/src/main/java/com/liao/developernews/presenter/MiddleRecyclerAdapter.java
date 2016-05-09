package com.liao.developernews.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.liao.developernews.R;
import com.liao.developernews.model.ImagePic;

import java.util.List;

/**
 * Created by liao on 2016-4-29 0029.
 */
public class MiddleRecyclerAdapter extends RecyclerView.Adapter<MiddleRecyclerAdapter.MyViewHolder>
{
    private List<ImagePic> mImageList;
    private LayoutInflater inflater;

    private OnImageItemClickListener mClickListener;

    /**
     * 构造方法
     * @param imageList 传入List
     * @param context 传入Context
     */
    public MiddleRecyclerAdapter(List<ImagePic> imageList, Context context)
    {
        this.mImageList = imageList;
        this.inflater = LayoutInflater.from(context);
    }

    public void onImageItemClickListener(OnImageItemClickListener clickListener)
    {
        this.mClickListener = clickListener;
    }

    @Override
    public MiddleRecyclerAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new MyViewHolder(inflater.inflate(R.layout.item_test, parent, false));
    }

    @Override
    public void onBindViewHolder(final MiddleRecyclerAdapter.MyViewHolder holder, int position)
    {
        holder.mImageView.setImageResource(mImageList.get(position).getUrl());

        if (mClickListener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mClickListener.onImageItemClick(v, holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        return mImageList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView mImageView;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.imageTest);
        }
    }

    /**
     * 接口
     */
    public interface OnImageItemClickListener
    {
        void onImageItemClick(View view, int position);
    }
}
