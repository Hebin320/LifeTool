package com.hebin.lifetool.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hebin.lifetool.R;
import com.hebin.lifetool.adapter.ConstellationAdapter;
import com.hebin.lifetool.listener.MyItemClickListener;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ConstellationListActivity extends AppCompatActivity implements MyItemClickListener {

    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_public_title)
    TextView tvPublicTitle;
    @InjectView(R.id.rv_list)
    RecyclerView rvList;

    Context context;
    int[] list = {
            R.mipmap.ic_xz_1, R.mipmap.ic_xz_2, R.mipmap.ic_xz_3, R.mipmap.ic_xz_4, R.mipmap.ic_xz_5, R.mipmap.ic_xz_6,
            R.mipmap.ic_xz_7, R.mipmap.ic_xz_8, R.mipmap.ic_xz_9, R.mipmap.ic_xz_10, R.mipmap.ic_xz_11, R.mipmap.ic_xz_12
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constellation_list);
        ButterKnife.inject(this);
        context = this;
        tvPublicTitle.setText("星座列表");
        ConstellationAdapter adapter = new ConstellationAdapter(context, list);
        GridLayoutManager manager = new GridLayoutManager(context, 2);
        rvList.setLayoutManager(manager);
        rvList.setAdapter(adapter);
        adapter.setListener(this);
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }

    @Override
    public void onItemClick(View view, int postion) {
        String title = "";
        switch (postion) {
            case 0:
                title = "白羊座";
                break;
            case 1:
                title = "金牛座";
                break;
            case 2:
                title = "双子座";
                break;
            case 3:
                title = "巨蟹座";
                break;
            case 4:
                title = "狮子座";
                break;
            case 5:
                title = "处女座";
                break;
            case 6:
                title = "天秤座";
                break;
            case 7:
                title = "天蝎座";
                break;
            case 8:
                title = "射手座";
                break;
            case 9:
                title = "摩羯座";
                break;
            case 10:
                title = "水瓶座";
                break;
            case 11:
                title = "双鱼座";
                break;
            default:
                break;
        }
        Intent intent = new Intent();
        intent.putExtra("title", title);
        intent.setClass(ConstellationListActivity.this, ConstellationDetailActivity.class);
        startActivity(intent);
    }
}
