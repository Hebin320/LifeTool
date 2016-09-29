package com.hebin.lifetool.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hebin.lifetool.R;
import com.hebin.lifetool.entity.BusInfoEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class BusInfoAdapter extends RecyclerView.Adapter<BusInfoAdapter.ViewHolder> {

    private Context context;
    private List<BusInfoEntity.ResultEntity.ListEntity> list = new ArrayList<>();

    public BusInfoAdapter(Context context, List<BusInfoEntity.ResultEntity.ListEntity> list) {
        this.context = context;
        this.list = list;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_businfo, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(list.get(position).getName());
        holder.tvTel.setText("联系方式："+list.get(position).getTel());
        holder.tvAdds.setText("地址："+list.get(position).getAdds());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.tv_title)
        TextView tvTitle;
        @InjectView(R.id.tv_tel)
        TextView tvTel;
        @InjectView(R.id.tv_adds)
        TextView tvAdds;

        ViewHolder(View view) {
            super(view);
            ButterKnife.inject(this, view);
        }
    }
}
