package com.hebin.lifetool.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hebin.lifetool.R;
import com.hebin.lifetool.base.BaseMethod;
import com.hebin.lifetool.biz.base.IBaseView;
import com.hebin.lifetool.entity.DataEntity;
import com.hebin.lifetool.entity.PhoneLocationEntity;
import com.hebin.lifetool.presenter.PhoneLocationPresenter;
import com.hebin.lifetool.utils.DialogUtil;
import com.hebin.lifetool.utils.LostFocus;
import com.hebin.lifetool.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class PhoneLocationActivity extends AppCompatActivity implements IBaseView {


    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_public_title)
    TextView tvPublicTitle;
    @InjectView(R.id.et_phone)
    EditText etPhone;
    @InjectView(R.id.iv_search)
    ImageView ivSearch;
    @InjectView(R.id.iv_delete)
    ImageView ivDelete;
    @InjectView(R.id.tv_province)
    TextView tvProvince;
    @InjectView(R.id.tv_city)
    TextView tvCity;
    @InjectView(R.id.tv_areacode)
    TextView tvAreacode;
    @InjectView(R.id.tv_zip)
    TextView tvZip;
    @InjectView(R.id.tv_company)
    TextView tvCompany;
    @InjectView(R.id.tv_card)
    TextView tvCard;
    @InjectView(R.id.card_detail)
    CardView cardDetail;

    Context context;
    String phone = "";
    PhoneLocationPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_location);
        ButterKnife.inject(this);
        context = this;
        etPhone.addTextChangedListener(textWatcher);
        tvPublicTitle.setText("手机归属地查询");
        presenter = new PhoneLocationPresenter(this);
    }

    @OnClick({R.id.ll_back, R.id.iv_search, R.id.iv_delete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
            case R.id.iv_search:
                if (BaseMethod.isMobileNO(etPhone.getText().toString())) {
                    LostFocus.lostByView(context, ivSearch);
                    phone = etPhone.getText().toString();
                    presenter.getData(context);
                } else {
                    ToastUtils.getInstance().showToast("请输入正确的手机号码");
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
        dataEntity.setPhone(phone);
        return dataEntity;
    }

    @Override
    public void getSuccess(int type, Object T) {
        switch (type) {
            case 1:
                cardDetail.setVisibility(View.VISIBLE);
                PhoneLocationEntity locationEntity = (PhoneLocationEntity) T;
                PhoneLocationEntity.ResultEntity resultEntity = locationEntity.getResult();
                tvProvince.setText("省份： " + resultEntity.getProvince());
                tvCity.setText("城市： " + resultEntity.getCity());
                tvAreacode.setText("区号：" + resultEntity.getAreacode());
                tvZip.setText("邮编： " + resultEntity.getZip());
                tvCompany.setText("公司： " + resultEntity.getCompany());
                tvCard.setText("类型： " + resultEntity.getCard());
                break;
            default:
                break;
        }
    }

    @Override
    public void getFailed(int type, Object T) {
        cardDetail.setVisibility(View.GONE);
        switch (type) {
            case 202:
                ToastUtils.getInstance().showToast("请输入正确的手机号码");
                break;
            case 404:
                noConnect();
                break;
        }
    }

    @Override
    public void showLoading() {
        DialogUtil.showpdialog(context);
    }

    @Override
    public void hideLoading() {
        DialogUtil.closedialog();
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
