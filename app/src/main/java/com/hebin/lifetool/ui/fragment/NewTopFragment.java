package com.hebin.lifetool.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hebin.lifetool.R;
import com.hebin.lifetool.adapter.NewTopAdapter;
import com.hebin.lifetool.biz.base.IBaseView;
import com.hebin.lifetool.entity.DataEntity;
import com.hebin.lifetool.entity.NewTopEntity;
import com.hebin.lifetool.listener.MyItemClickListener;
import com.hebin.lifetool.presenter.NewTopPresenter;
import com.hebin.lifetool.ui.activity.WebViewActivity;
import com.hebin.lifetool.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NewTopFragment extends Fragment implements IBaseView, MyItemClickListener {


    @InjectView(R.id.ll_loading)
    LinearLayout llLoading;
    @InjectView(R.id.rv_list)
    RecyclerView rvList;

    private static final String ARG_PARAM1 = "param1";
    private String mParam1;
    private NewTopAdapter adapter;
    private List<NewTopEntity.ResultEntity.DataEntity> mList = new ArrayList<>();

    public static NewTopFragment newInstance(String param1) {
        NewTopFragment fragment = new NewTopFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_top, container, false);
        ButterKnife.inject(this, view);
        setList();
        NewTopPresenter presenter = new NewTopPresenter(this);
        presenter.getList(getContext());
        return view;
    }

    private void setList() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvList.setLayoutManager(manager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public DataEntity getData() {
        DataEntity dataEntity = new DataEntity();
        dataEntity.setType(mParam1);
        return dataEntity;
    }

    @Override
    public void getSuccess(int type, Object T) {
        switch (type) {
            case 1:
                NewTopEntity entity = (NewTopEntity) T;
                List<NewTopEntity.ResultEntity.DataEntity> list = entity.getResult().getData();
                mList.addAll(list);
                if (adapter == null) {
                    adapter = new NewTopAdapter(getContext(), list);
                    rvList.setAdapter(adapter);
                } else {
                    adapter.notifyDataSetChanged();
                }
                adapter.setListener(this);
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

    @Override
    public void onItemClick(View view, int postion) {
        Intent intent = new Intent();
        intent.putExtra("title", "文章详情");
        intent.putExtra("url", mList.get(postion).getUrl());
        intent.setClass(getContext(), WebViewActivity.class);
        startActivity(intent);
    }
}
