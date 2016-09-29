package com.hebin.lifetool.ui.fragment;


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
import com.hebin.lifetool.entity.constellation.ConstellationyearEntity;
import com.hebin.lifetool.entity.DataEntity;
import com.hebin.lifetool.presenter.constellation.ConstellationYearPresenter;
import com.hebin.lifetool.utils.ToastUtils;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ConstellationYearFragment extends Fragment implements IBaseView {


    @InjectView(R.id.ll_loading)
    LinearLayout llLoading;
    @InjectView(R.id.iv_logo)
    ImageView ivLogo;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_title)
    TextView tvTitle;
    @InjectView(R.id.tv_date)
    TextView tvDate;
    @InjectView(R.id.tv_all)
    TextView tvAll;
    @InjectView(R.id.tv_info)
    JustifiedTextView tvInfo;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @InjectView(R.id.tv_mima_title)
    TextView tvMimaTitle;
    @InjectView(R.id.tv_mima)
    JustifiedTextView tvMima;
    private String mParam1;
    private String mParam2;


    public static ConstellationYearFragment newInstance(String param1, String param2) {
        ConstellationYearFragment fragment = new ConstellationYearFragment();
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
        View view = inflater.inflate(R.layout.fragment_constellation_year, container, false);
        ButterKnife.inject(this, view);
        ConstellationSetLogo setLogo = new ConstellationSetLogo();
        setLogo.setLogo(mParam1, ivLogo, tvName);
        showLoading();
        ConstellationYearPresenter presenter = new ConstellationYearPresenter(this);
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
                ConstellationyearEntity entity = (ConstellationyearEntity) T;
                tvDate.setText("日期   " + entity.getDate());
                tvAll.setText("幸运石  " + entity.getLuckyStone());
                tvMimaTitle.setText(entity.getMima().getInfo());
                tvMima.setText(entity.getMima().getText().get(0));
                tvInfo.setText("工作\n\n" + entity.getCareer().get(0) + "\n\n\n" +
                                "爱情\n\n" + entity.getLove().get(0) + "\n\n\n" +
                                "健康\n\n" + entity.getHealth().get(0) + "\n\n\n" +
                                "财运\n\n" + entity.getFinance().get(0)
                );
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
