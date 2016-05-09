package com.liao.notebook.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liao.notebook.R;
import com.liao.notebook.model.NoteBean;

import java.util.List;

/**
 * Created by liao on 2016-5-2 0002.
 */
public class NoteRecyclerAdapter extends RecyclerView.Adapter<NoteRecyclerAdapter.MyViewHolder>
{
    private List<NoteBean> mNoteList;
    private LayoutInflater mInflater;
    private OnNoteItemClickListener mClickListener;

    public NoteRecyclerAdapter(List<NoteBean> noteList, Context context)
    {
        mNoteList = noteList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new MyViewHolder(mInflater.inflate(R.layout.item_note, parent, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position)
    {
        NoteBean bean = mNoteList.get(position);
        holder.tvTitle.setText(bean.getTitle());
        holder.tvContent.setText(bean.getContent());
//        holder.ivContent.setBackgroundResource(bean.getImgId());

        if (mClickListener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mClickListener.onNoteItemClick(v, holder.getAdapterPosition());
                }
            });
        }
    }

    @Override
    public int getItemCount()
    {
        return mNoteList == null ? 0 : mNoteList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tvTitle;
        private TextView tvContent;
        private ImageView ivContent;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            ivContent = (ImageView) itemView.findViewById(R.id.ivContent);
        }
    }

    public interface OnNoteItemClickListener
    {
        void onNoteItemClick(View view, int position);
    }

    public void OnNoteItemClickListener(OnNoteItemClickListener listener)
    {
        this.mClickListener = listener;
    }
}

