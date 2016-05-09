package com.liao.notebook.presenter;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liao.notebook.R;
import com.liao.notebook.model.NoteBean;
import com.liao.notebook.utils.NoteDBHelper;
import com.liao.notebook.utils.ShowDialogOrMsg;

/**
 * Created by liao on 2016-5-4 0004.
 */
public class NoteBookAdapter extends RecyclerViewCursorAdapter<NoteBookAdapter.NoteBookViewHolder>
{
    private LayoutInflater mInflater;

    private OnItemClickListener mClickListener;
    private OnItemLongClickListener mLongClickListener;
    /**
     * Recommended constructor.
     *
     * @param context The context
     * @param c       The cursor from which to get the data.
     * @param flags   Flags used to determine the behavior of the adapter;
     *                Currently it accept {@link #FLAG_REGISTER_CONTENT_OBSERVER}.
     */
    public NoteBookAdapter(Context context, Cursor c, int flags)
    {
        super(context, c, flags);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public void onBindViewHolder(final NoteBookViewHolder holder, Cursor cursor)
    {
        NoteBean note = new NoteBean();
        note.setId(cursor.getString(cursor.getColumnIndex(NoteDBHelper.ID)));
        note.setTitle(cursor.getString(cursor.getColumnIndex(NoteDBHelper.TITLE)));
        note.setContent(cursor.getString(cursor.getColumnIndex(NoteDBHelper.CONTENT)));
        note.setImgUri(cursor.getString(cursor.getColumnIndex(NoteDBHelper.IMAGE)));

        holder.tvTitle.setText(note.getTitle());
        holder.tvContent.setText(note.getContent());
        holder.ivContent.setImageBitmap(ShowDialogOrMsg.getImageThumbnail(note.getImgUri(), 100, 100));

        if (mClickListener != null)
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    mClickListener.onItemClick(v, holder.getLayoutPosition());
                }
            });
        }

        if (mLongClickListener != null)
        {
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
            {
                @Override
                public boolean onLongClick(View v)
                {
                    mLongClickListener.onItemLongClick(v, holder.getLayoutPosition());
                    return true;
                }
            });
        }
    }

    @Override
    protected void onContentChanged()
    {

    }

    @Override
    public NoteBookViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new NoteBookViewHolder(mInflater.inflate(R.layout.item_note, parent, false));
    }

    public class NoteBookViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tvTitle;
        private TextView tvContent;
        private ImageView ivContent;

        public NoteBookViewHolder(View itemView)
        {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
            ivContent = (ImageView) itemView.findViewById(R.id.ivContent);
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener
    {
        void onItemLongClick(View view, int position);
    }

    public void onItemClickListener(OnItemClickListener clickListener)
    {
        this.mClickListener = clickListener;
    }

    public void onItemLongClickListener(OnItemLongClickListener longClickListener)
    {
        this.mLongClickListener = longClickListener;
    }
}
