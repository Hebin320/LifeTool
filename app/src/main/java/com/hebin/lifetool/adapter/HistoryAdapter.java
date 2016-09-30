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
import com.hebin.lifetool.entity.HistoryEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private Context context;
    private ImageLoader imageLoader;
    private List<HistoryEntity.ResultEntity> list = new ArrayList<>();

    public HistoryAdapter(Context context, List<HistoryEntity.ResultEntity> list) {
        this.context = context;
        this.list = list;
        RequestQueue queue = Volley.newRequestQueue(context);
        imageLoader = new ImageLoader(queue, new BitmapCache());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_history, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HistoryEntity.ResultEntity resultEntity = list.get(position);
        holder.ivLogo.setDefaultImageResId(R.mipmap.ic_img_loading);
        holder.ivLogo.setErrorImageResId(R.mipmap.ic_img_loading);
        holder.ivLogo.setImageUrl(resultEntity.getPic(), imageLoader);
        holder.tvTitle.setText(resultEntity.getTitle());
        holder.tvInfo.setText(resultEntity.getDes());
        holder.tvTime01.setText(resultEntity.getYear() + "年" + resultEntity.getMonth() + "月" + resultEntity.getDay() + "日");
        holder.tvTime02.setText(resultEntity.getLunar());
        holder.ivLogo.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_logo)
        NetworkImageView ivLogo;
        @InjectView(R.id.tv_title)
        TextView tvTitle;
        @InjectView(R.id.tv_info)
        TextView tvInfo;
        @InjectView(R.id.tv_time_01)
        TextView tvTime01;
        @InjectView(R.id.tv_time_02)
        TextView tvTime02;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
