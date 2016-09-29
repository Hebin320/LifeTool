package com.hebin.lifetool.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hebin.lifetool.R;
import com.hebin.lifetool.adapter.BusInfoAdapter;
import com.hebin.lifetool.biz.base.IBaseView;
import com.hebin.lifetool.entity.BusInfoEntity;
import com.hebin.lifetool.entity.DataEntity;
import com.hebin.lifetool.presenter.BusInfoPresenter;
import com.hebin.lifetool.utils.LostFocus;
import com.hebin.lifetool.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class BusActivity extends AppCompatActivity implements IBaseView {

    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_public_title)
    TextView tvPublicTitle;
    @InjectView(R.id.ll_loading)
    LinearLayout llLoading;
    @InjectView(R.id.et_phone)
    EditText etPhone;
    @InjectView(R.id.iv_search)
    ImageView ivSearch;
    @InjectView(R.id.iv_delete)
    ImageView ivDelete;
    @InjectView(R.id.rv_list)
    RecyclerView rvList;


    Context context;
    String station = "";
    boolean isClean = true;
    BusInfoAdapter adapter;
    List<BusInfoEntity.ResultEntity.ListEntity> mList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);
        ButterKnife.inject(this);
        context = this;
        tvPublicTitle.setText("长途汽车信息");
        etPhone.addTextChangedListener(textWatcher);
        setList();
    }

    private void setList() {
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(manager);
    }

    @OnClick({R.id.ll_back, R.id.iv_search, R.id.iv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.iv_search:
                mList.clear();
                station = etPhone.getText().toString();
                if (TextUtils.isEmpty(station)) {
                    ToastUtils.getInstance().showToast("请输入要搜索的城市");
                } else {
                    showLoading();
                    LostFocus.lostByView(context, ivSearch);
                    BusInfoPresenter presenter = new BusInfoPresenter(this);
                    presenter.getList(context);
                }
                break;
            case R.id.iv_delete:
                etPhone.setText("");
                break;
        }
    }

    @Override
    public DataEntity getData() {
        DataEntity dataEntity = new DataEntity();
        dataEntity.setStation(station);
        return dataEntity;
    }

    @Override
    public void getSuccess(int type, Object T) {
        switch (type) {
            case 1:
                BusInfoEntity entity = (BusInfoEntity) T;
                List<BusInfoEntity.ResultEntity.ListEntity> list = entity.getResult().getList();
                mList.addAll(list);
                if (adapter == null) {
                    adapter = new BusInfoAdapter(context, mList);
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
        switch (type) {
            case 404:
                noConnect();
                break;
            case 208202:
                if (adapter != null) {
                    adapter.notifyDataSetChanged();
                }
                ToastUtils.getInstance().showToast("查询不到该汽车站相关信息");
                break;
            default:
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

    TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.length() > 0) {
                ivDelete.setVisibility(View.VISIBLE);
            } else {
                ivDelete.setVisibility(View.GONE);
            }
        }
    };
}
