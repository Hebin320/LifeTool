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
import com.hebin.lifetool.adapter.TabAdapter;
import com.hebin.lifetool.ui.fragment.ConstellationDayFragment;
import com.hebin.lifetool.ui.fragment.ConstellationMonthFragment;
import com.hebin.lifetool.ui.fragment.ConstellationWeekFragment;
import com.hebin.lifetool.ui.fragment.ConstellationYearFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class ConstellationDetailActivity extends AppCompatActivity {


    String title = "";
    public static final String[] tabTitle = new String[]{"今日运势", "明日运势", "今周运势", "下周运势", "月度运势", "年度运势"};
    @InjectView(R.id.ll_back)
    LinearLayout llBack;
    @InjectView(R.id.tv_public_title)
    TextView tvPublicTitle;
    @InjectView(R.id.tab_constellation)
    TabLayout tabConstellation;
    @InjectView(R.id.vp_constellation)
    ViewPager vpConstellation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constellation_detail);
        ButterKnife.inject(this);
        title = getIntent().getStringExtra("title");
        if (title != null) {
            tvPublicTitle.setText(title);
            initviews();
        }
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
        fragments.add(ConstellationDayFragment.newInstance(title, "today"));
        fragments.add(ConstellationDayFragment.newInstance(title, "tomorrow"));
        fragments.add(ConstellationWeekFragment.newInstance(title, "week"));
        fragments.add(ConstellationWeekFragment.newInstance(title, "nextweek"));
        fragments.add(ConstellationMonthFragment.newInstance(title, "month"));
        fragments.add(ConstellationYearFragment.newInstance(title, "year"));
        TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), fragments);
        vpConstellation.setAdapter(adapter);
        vpConstellation.setOffscreenPageLimit(5);
        tabConstellation.setupWithViewPager(vpConstellation);
        tabConstellation.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

}
