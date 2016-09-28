package com.hebin.lifetool.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hebin.lifetool.R;
import com.hebin.lifetool.adapter.WeChatAdapter;
import com.hebin.lifetool.base.BaseMethod;
import com.hebin.lifetool.biz.base.IBaseView;
import com.hebin.lifetool.entity.DataEntity;
import com.hebin.lifetool.entity.WeChatEntity;
import com.hebin.lifetool.listener.MyItemClickListener;
import com.hebin.lifetool.presenter.WeChatPresenter;
import com.hebin.lifetool.utils.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class WeChatNewActivity extends AppCompatActivity implements IBaseView, MyItemClickListener {

    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_public_title)
    TextView tvPublicTitle;
    @InjectView(R.id.ll_loading)
    LinearLayout llLoading;
    @InjectView(R.id.rv_wechat_new)
    XRecyclerView rvWechatNew;

    int page = 1;
    Context context;
    boolean isClean = true;
    WeChatAdapter adapter;
    WeChatPresenter presenter;
    List<WeChatEntity.ResultEntity.ListEntity> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_we_chat_new);
        ButterKnife.inject(this);
        context = this;
        setList();
        tvPublicTitle.setText("微信精选");
        presenter = new WeChatPresenter(this);
        showLoading();
        presenter.getList(context);
    }

    private void setList() {
        BaseMethod.setRecyclerView(context, rvWechatNew);
        rvWechatNew.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page = 1;
                isClean = true;
                presenter.getList(context);
            }

            @Override
            public void onLoadMore() {
                page++;
                if (page <= 25) {
                    presenter.getList(context);
                } else {
                    rvWechatNew.loadMoreComplete();
                    rvWechatNew.noMoreLoading();
                }
            }
        });
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
    public DataEntity getData() {
        DataEntity dataEntity = new DataEntity();
        dataEntity.setPage(page);
        return dataEntity;
    }

    @Override
    public void getSuccess(int type, Object T) {
        rvWechatNew.refreshComplete();
        rvWechatNew.loadMoreComplete();
        switch (type) {
            case 1:
                WeChatEntity weChatEntity = (WeChatEntity) T;
                List<WeChatEntity.ResultEntity.ListEntity> list = weChatEntity.getResult().getList();
                if (isClean) {
                    mList.clear();
                }
                mList.addAll(list);
                if (adapter == null) {
                    adapter = new WeChatAdapter(context, mList);
                    rvWechatNew.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
                adapter.setListener(this);
                isClean = false;
                break;
        }
    }

    @Override
    public void getFailed(int type, Object T) {
        rvWechatNew.refreshComplete();
        rvWechatNew.loadMoreComplete();
        switch (type) {
            case 404:
                noConnect();
                break;
        }
    }

    @Override
    public void showLoading() {
        llLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        llLoading.setVisibility(View.GONE);
    }

    @Override
    public void noConnect() {
        ToastUtils.getInstance().showNoNet();
    }

    @Override
    public void isConnect() {

    }

    @Override
    public void onItemClick(View view, int postion) {
        Intent intent = new Intent();
        intent.putExtra("title", "文章详情");
        intent.putExtra("url", mList.get(postion).getUrl());
        intent.setClass(this, WebViewActivity.class);
        startActivity(intent);
    }
}
