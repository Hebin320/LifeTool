package com.hebin.lifetool.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hebin.lifetool.R;
import com.hebin.lifetool.listener.MyItemClickListener;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 星座列表的Adapter
 */

public class ConstellationAdapter extends RecyclerView.Adapter<ConstellationAdapter.ViewHolder> {

    private Context context;
    private int[] list;
    private MyItemClickListener listener;

    public ConstellationAdapter(Context context, int[] list) {
        this.context = context;
        this.list = list;
    }

    public void setListener(MyItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_constellation, parent, false);
        ViewHolder viewHolder = new ViewHolder(view, listener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.ivConstellation.setImageResource(list[position]);
    }

    @Override
    public int getItemCount() {
        return list.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @InjectView(R.id.iv_constellation)
        ImageView ivConstellation;

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
