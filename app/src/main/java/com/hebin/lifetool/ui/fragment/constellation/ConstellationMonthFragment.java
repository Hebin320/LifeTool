package com.hebin.lifetool.ui.fragment.constellation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hebin.lifetool.R;
import com.hebin.lifetool.biz.base.IBaseView;
import com.hebin.lifetool.entity.constellation.ConstellationmonthEntity;
import com.hebin.lifetool.entity.DataEntity;
import com.hebin.lifetool.presenter.constellation.ConstellationMonthPresenter;
import com.hebin.lifetool.utils.ToastUtils;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ConstellationMonthFragment extends Fragment implements IBaseView {


    @InjectView(R.id.ll_loading)
    LinearLayout llLoading;
    @InjectView(R.id.iv_logo)
    ImageView ivLogo;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_date)
    TextView tvDate;
    @InjectView(R.id.tv_info)
    JustifiedTextView tvInfo;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;


    public static ConstellationMonthFragment newInstance(String param1, String param2) {
        ConstellationMonthFragment fragment = new ConstellationMonthFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_constellation_month, container, false);
        ButterKnife.inject(this, view);
        ConstellationSetLogo setLogo = new ConstellationSetLogo();
        setLogo.setLogo(mParam1, ivLogo, tvName);
        showLoading();
        ConstellationMonthPresenter presenter = new ConstellationMonthPresenter(this);
        presenter.getData(getContext());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    @Override
    public DataEntity getData() {
        DataEntity dataEntity = new DataEntity();
        dataEntity.setConsname(mParam1);
        dataEntity.setType(mParam2);
        return dataEntity;
    }

    @Override
    public void getSuccess(int type, Object T) {
        switch (type) {
            case 1:
                ConstellationmonthEntity entity = (ConstellationmonthEntity) T;
                tvDate.setText("日期    " + entity.getDate());
                tvInfo.setText(
                        "综合: " + entity.getAll() + "\n" + "\n" +
                                "健康: " + entity.getHealth() + "\n" + "\n" +
                                "爱情: " + entity.getLove() + "\n" + "\n" +
                                "财运: " + entity.getMoney() + "\n" + "\n" +
                                "工作: " + entity.getWork());
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


}
