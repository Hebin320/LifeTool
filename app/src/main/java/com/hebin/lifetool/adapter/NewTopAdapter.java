package com.hebin.lifetool.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.hebin.lifetool.R;
import com.hebin.lifetool.custom.volley.BitmapCache;
import com.hebin.lifetool.entity.NewTopEntity;
import com.hebin.lifetool.entity.WeChatEntity;
import com.hebin.lifetool.listener.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 新闻头条的Adapter
 */

public class NewTopAdapter extends RecyclerView.Adapter<NewTopAdapter.ViewHolder> {

    private Context context;
    private ImageLoader imageLoader;
    private MyItemClickListener listener;
    private List<NewTopEntity.ResultEntity.DataEntity> list = new ArrayList<>();

    public NewTopAdapter(Context context, List<NewTopEntity.ResultEntity.DataEntity> list) {
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
        NewTopEntity.ResultEntity.DataEntity listEntity = list.get(position);
        holder.ivNew.setImageUrl(listEntity.getThumbnail_pic_s03(), imageLoader);
        holder.ivNew.setDefaultImageResId(R.mipmap.ic_img_loading);
        holder.ivNew.setErrorImageResId(R.mipmap.ic_img_loading);
        holder.tvSource.setText(listEntity.getAuthor_name());
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
