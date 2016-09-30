package com.hebin.lifetool.ui.fragment.constellation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.hebin.lifetool.R;
import com.hebin.lifetool.biz.base.IBaseView;
import com.hebin.lifetool.entity.constellation.ConstellationdayEntity;
import com.hebin.lifetool.entity.DataEntity;
import com.hebin.lifetool.presenter.constellation.ConstellationDayPresenter;
import com.hebin.lifetool.utils.ToastUtils;
import com.uncopt.android.widget.text.justify.JustifiedTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class ConstellationDayFragment extends Fragment implements IBaseView {


    @InjectView(R.id.ll_loading)
    LinearLayout llLoading;
    @InjectView(R.id.rbar_all)
    RatingBar rbarAll;
    @InjectView(R.id.tv_health)
    TextView tvHealth;
    @InjectView(R.id.rbar_love)
    RatingBar rbarLove;
    @InjectView(R.id.tv_color)
    TextView tvColor;
    @InjectView(R.id.rbar_work)
    RatingBar rbarWork;
    @InjectView(R.id.tv_number)
    TextView tvNumber;
    @InjectView(R.id.rbar_rich)
    RatingBar rbarRich;
    @InjectView(R.id.tv_qfriend)
    TextView tvQfriend;
    @InjectView(R.id.tv_summary)
    JustifiedTextView tvSummary;
    @InjectView(R.id.tv_title)
    TextView tvTitle;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    @InjectView(R.id.iv_logo)
    ImageView ivLogo;
    @InjectView(R.id.tv_name)
    TextView tvName;
    @InjectView(R.id.tv_all)
    TextView tvAll;
    @InjectView(R.id.tv_love)
    TextView tvLove;
    @InjectView(R.id.tv_work)
    TextView tvWork;
    @InjectView(R.id.tv_rich)
    TextView tvRich;
    private String mParam1;
    private String mParam2;

    public static ConstellationDayFragment newInstance(String param1, String param2) {
        ConstellationDayFragment fragment = new ConstellationDayFragment();
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
        View view = inflater.inflate(R.layout.fragment_constellation, container, false);
        ButterKnife.inject(this, view);
        showLoading();
        ConstellationDayPresenter presenter = new ConstellationDayPresenter(this);
        presenter.getData(getContext());
        if (!mParam2.equals("today")) {
            tvTitle.setText("明日运势");
        }
        ConstellationSetLogo setLogo = new ConstellationSetLogo();
        setLogo.setLogo(mParam1, ivLogo, tvName);
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
                ConstellationdayEntity entity = (ConstellationdayEntity) T;
                tvSummary.setText(entity.getSummary());
                tvHealth.setText("健康指数   " + entity.getHealth());
                tvColor.setText("幸运颜色   " + entity.getColor());
                tvNumber.setText("幸运数字   " + entity.getNumber());
                tvQfriend.setText("速配好友   " + entity.getQFriend());
                rbarAll.setRating(getRating(entity.getAll()));
                rbarLove.setRating(getRating(entity.getLove()));
                rbarWork.setRating(getRating(entity.getWork()));
                rbarRich.setRating(getRating(entity.getMoney()));
                break;
            default:
                break;
        }
    }

    private float getRating(String string) {
        float rating = Float.parseFloat(string.substring(0, string.length() - 1)) / 20;
        return rating;
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
