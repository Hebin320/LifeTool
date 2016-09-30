package com.hebin.lifetool.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hebin.lifetool.R;
import com.hebin.lifetool.adapter.NewTopTabAdapter;
import com.hebin.lifetool.ui.fragment.NewTopFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class NewTopActivity extends AppCompatActivity {

    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_public_title)
    TextView tvPublicTitle;
    @InjectView(R.id.tab_newtop)
    TabLayout tabNewtop;
    @InjectView(R.id.vp_newtop)
    ViewPager vpNewtop;

    String title = "";
    public static final String[] tabTitle = new String[]{"娱乐", "科技", "时尚", "社会", "国内", "国际", "财经", "体育", "军事"};
    public static final String[] tabType = new String[]{"yule", "keji", "shishang", "shehui", "guonei", "guoji", "caijing", "tiyu", "junshi"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_top);
        ButterKnife.inject(this);
        initviews();
        tvPublicTitle.setText("新闻头条");
    }

    @OnClick({R.id.ll_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_back:
                finish();
                break;
        }
    }

    private void initviews() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < tabType.length; i++) {
            fragments.add(NewTopFragment.newInstance(tabType[i]));
        }
        NewTopTabAdapter adapter = new NewTopTabAdapter(getSupportFragmentManager(), fragments);
        vpNewtop.setAdapter(adapter);
        vpNewtop.setOffscreenPageLimit(9);
        tabNewtop.setupWithViewPager(vpNewtop);
        tabNewtop.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}
