package com.hebin.lifetool.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.format.Time;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hebin.lifetool.R;
import com.hebin.lifetool.adapter.HistoryAdapter;
import com.hebin.lifetool.biz.base.IBaseView;
import com.hebin.lifetool.entity.DataEntity;
import com.hebin.lifetool.entity.HistoryEntity;
import com.hebin.lifetool.presenter.HistoryPresenter;
import com.hebin.lifetool.utils.ToastUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class HistoryActivity extends AppCompatActivity implements IBaseView {


    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_public_title)
    TextView tvPublicTitle;
    @InjectView(R.id.ll_loading)
    LinearLayout llLoading;
    @InjectView(R.id.rv_list)
    RecyclerView rvList;

    String month = "";
    String day = "";
    Context context;
    HistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.inject(this);
        context = this;
        tvPublicTitle.setText("历史上的今天");
        getTime();
        setList();
        HistoryPresenter presenter = new HistoryPresenter(this);
        presenter.getList(context);
    }

    private void getTime() {
        Time t = new Time();
        t.setToNow();
        int mDay = t.monthDay;
        int mMonth = t.month + 1;
        month = mMonth + "";
        day = mDay + "";
    }

    private void setList() {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(manager);
    }

    @Override
    public DataEntity getData() {
        DataEntity dataEntity = new DataEntity();
        dataEntity.setMonth(month);
        dataEntity.setDay(day);
        return dataEntity;
    }

    @Override
    public void getSuccess(int type, Object T) {
        switch (type) {
            case 1:
                HistoryEntity entity = (HistoryEntity) T;
                List<HistoryEntity.ResultEntity> list = entity.getResult();
                if (adapter == null) {
                    adapter = new HistoryAdapter(context, list);
                    rvList.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void getFailed(int type, Object T) {
        noConnect();
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

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }
}
