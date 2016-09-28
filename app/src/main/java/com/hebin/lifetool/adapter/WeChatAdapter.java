package com.hebin.lifetool.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.hebin.lifetool.R;
import com.hebin.lifetool.custom.volley.BitmapCache;
import com.hebin.lifetool.entity.WeChatEntity;
import com.hebin.lifetool.listener.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 微信精选的Adapter
 */

public class WeChatAdapter extends RecyclerView.Adapter<WeChatAdapter.ViewHolder> {

    private Context context;
    private ImageLoader imageLoader;
    private MyItemClickListener listener;
    private List<WeChatEntity.ResultEntity.ListEntity> list = new ArrayList<>();

    public WeChatAdapter(Context context, List<WeChatEntity.ResultEntity.ListEntity> list) {
        this.context = context;
        this.list = list;
        RequestQueue queue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(queue, new BitmapCache());
    }

    public void setListener(MyItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wechat_new, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        WeChatEntity.ResultEntity.ListEntity listEntity = list.get(position);
        holder.ivNew.setImageUrl(listEntity.getFirstImg(), imageLoader);
        holder.ivNew.setDefaultImageResId(R.mipmap.ic_img_loading);
        holder.ivNew.setErrorImageResId(R.mipmap.ic_img_loading);
        holder.tvSource.setText(listEntity.getSource());
        holder.tvTitle.setText(listEntity.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @InjectView(R.id.iv_new)
        NetworkImageView ivNew;
        @InjectView(R.id.tv_title)
        TextView tvTitle;
        @InjectView(R.id.tv_source)
        TextView tvSource;

        MyItemClickListener listener;

        ViewHolder(View view, MyItemClickListener listener) {
            super(view);
            ButterKnife.inject(this, view);
            this.listener = listener;
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }
}
