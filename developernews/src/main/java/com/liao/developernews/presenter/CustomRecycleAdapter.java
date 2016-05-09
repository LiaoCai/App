package com.liao.developernews.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liao.developernews.R;
import com.liao.developernews.model.ItemBean;

import java.util.List;

/**
 * 自定义Adapter，可以实现addHeaderView
 * Created by liao on 2016-4-28 0028.
 */
public class CustomRecycleAdapter extends RecyclerView.Adapter<CustomRecycleAdapter.MyViewHolder>
{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private View headerView;
    private List<ItemBean> mItemList;
    private LayoutInflater mInflater;

    private OnItemClickListener clickListener;

    public void onItemClickListener(OnItemClickListener listener)
    {
        clickListener = listener;
    }
    /**
     * 构造方法
     * @param itemBeanList javaBean List
     * @param context 上下文
     */
    public CustomRecycleAdapter(List<ItemBean> itemBeanList, Context context)
    {
        mItemList = itemBeanList;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * 添加一个headerView
     * @param view 传入View
     */
    public void setHeaderView(View view)
    {
        headerView = view;
        notifyItemInserted(0);
    }

    /**
     * 获取Item类型，是普通Item项还是headerView
     * @param position 位置
     * @return 类型
     */
    @Override
    public int getItemViewType(int position)
    {
        if (headerView == null)
        {
            return TYPE_ITEM;
        }
        if (position == TYPE_HEADER)
        {
            return TYPE_HEADER;
        }
        return TYPE_ITEM;
    }

    /**
     * 创建MyViewHolder
     * @param parent 父容器
     * @param viewType View的类型
     * @return View
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if (headerView != null && viewType == TYPE_HEADER)
        {
            return new MyViewHolder(headerView);
        }
        return new MyViewHolder(mInflater.inflate(R.layout.item_bean, parent, false));
    }

    /**
     * 绑定数据
     * @param holder MyViewHolder对象
     * @param position 当前在第几项
     */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position)
    {
        if (getItemViewType(position) == TYPE_HEADER)
        {
            return;
        }
        if (holder != null)
        {
            ItemBean itemBean = mItemList.get(getCursorPosition(position));
            holder.tvTitle.setText(itemBean.getTitle());
            holder.tvLike.setText(itemBean.getLike());
            holder.tvComment.setText(itemBean.getComment());
            holder.tvName.setText(itemBean.getName());
//            holder.ivHeader.setImageResource(Integer.valueOf(itemBean.getHeaderPic()));
        }

        final int pos = getCursorPosition(position);

        if (!holder.itemView.hasOnClickListeners())
        {
            holder.itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    clickListener.onItemClick(v, pos);
                }
            });
        }
    }

    /**
     * 当前在第几项，先判断是否有headerView
     * @param position 位置
     * @return 当前位置
     */
    public int getCursorPosition(int position)
    {
        return headerView == null ? position : position - 1;
    }

    /**
     * 列表项数量
     * @return 列表数量，先判断是否有headerView
     */
    @Override
    public int getItemCount()
    {
        return headerView == null ? mItemList.size() : (mItemList.size() + 1);
    }

    /**
     * ViewHolder
     */
    class MyViewHolder extends RecyclerView.ViewHolder
    {
        private TextView tvTitle;
        private TextView tvLike;
        private TextView tvComment;
        private TextView tvName;
        private ImageView ivHeader;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            if (itemView == headerView)
            {
                return;
            }
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvLike = (TextView) itemView.findViewById(R.id.tvLike);
            tvComment = (TextView) itemView.findViewById(R.id.tvComment);
            tvName = (TextView) itemView.findViewById(R.id.tvName);
            ivHeader = (ImageView) itemView.findViewById(R.id.ivHeader);
        }
    }

    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
    }
}
