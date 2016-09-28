package com.hebin.lifetool.ui.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hebin.lifetool.R;
import com.hebin.lifetool.widget.swipeview.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends AppCompatActivity implements SwipeFlingAdapterView.onFlingListener,
        SwipeFlingAdapterView.OnItemClickListener {

    @InjectView(R.id.swipe_view)
    SwipeFlingAdapterView swipeView;

    int[] headerIcons = {
            R.mipmap.ic_image1,
            R.mipmap.ic_image2,
            R.mipmap.ic_image3,
            R.mipmap.ic_image4,
            R.mipmap.ic_image5,
    };
    public final String[] title = {"星座运势", "手机号码查询", "微信精选", "测试", "测试"};


    private int cardWidth;
    private int cardHeight;
    private InnerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        initView();
        loadData();
    }

    private void initView() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float density = dm.density;
        cardWidth = (int) (dm.widthPixels - (4.8 * 18 * density));
        cardHeight = (int) (dm.heightPixels - (280 * density));
        swipeView.setFlingListener(this);
        swipeView.setOnItemClickListener(this);
        adapter = new InnerAdapter();
        swipeView.setAdapter(adapter);
    }


    @Override
    public void onItemClicked(MotionEvent event, View v, Object dataObject) {
        TextView tv = (TextView) v.findViewById(R.id.tv_title);
        Intent intent = new Intent();
        switch (tv.getText().toString()) {
            case "手机号码查询":
                intent.setClass(MainActivity.this, PhoneLocationActivity.class);
                startActivity(intent);
                break;
            case "微信精选":
                intent.setClass(MainActivity.this, WeChatNewActivity.class);
                startActivity(intent);
                break;
            case "星座运势":
                intent.setClass(MainActivity.this, ConstellationListActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

    @Override
    public void removeFirstObjectInAdapter() {
        adapter.remove(0);
    }

    @Override
    public void onLeftCardExit(Object dataObject) {
    }

    @Override
    public void onRightCardExit(Object dataObject) {
    }

    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {
        if (itemsInAdapter == 3) {
            loadData();
        }
    }

    @Override
    public void onScroll(float progress, float scrollXProgress) {
    }

    private void loadData() {
        new AsyncTask<Void, Void, List<Talent>>() {
            @Override
            protected List<Talent> doInBackground(Void... params) {
                ArrayList<Talent> list = new ArrayList<>(10);
                Talent talent;
                for (int i = 0; i < headerIcons.length; i++) {
                    talent = new Talent();
                    talent.headerIcon = headerIcons[i];
                    talent.strTitle = title[i];
                    list.add(talent);
                }
                return list;
            }

            @Override
            protected void onPostExecute(List<Talent> list) {
                super.onPostExecute(list);
                adapter.addAll(list);
            }
        }.execute();
    }


    private class InnerAdapter extends BaseAdapter {

        ArrayList<Talent> objs;

        public InnerAdapter() {
            objs = new ArrayList<>();
        }

        public void addAll(Collection<Talent> collection) {
            if (isEmpty()) {
                objs.addAll(collection);
                notifyDataSetChanged();
            } else {
                objs.addAll(collection);
            }
        }

        public void clear() {
            objs.clear();
            notifyDataSetChanged();
        }

        public boolean isEmpty() {
            return objs.isEmpty();
        }

        public void remove(int index) {
            if (index > -1 && index < objs.size()) {
                objs.remove(index);
                notifyDataSetChanged();
            }
        }


        @Override
        public int getCount() {
            return objs.size();
        }

        @Override
        public Talent getItem(int position) {
            if (objs == null || objs.size() == 0) return null;
            return objs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            Talent talent = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_new, parent, false);
                holder = new ViewHolder();
                convertView.setTag(holder);
                convertView.getLayoutParams().width = cardWidth;
                holder.portraitView = (ImageView) convertView.findViewById(R.id.portrait);
                holder.portraitView.getLayoutParams().height = cardHeight;
                holder.tvTitle = (TextView) convertView.findViewById(R.id.tv_title);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.portraitView.setImageResource(talent.headerIcon);
            holder.tvTitle.setText(talent.strTitle);
            return convertView;
        }
    }

    private static class ViewHolder {
        ImageView portraitView;
        TextView tvTitle;
    }

    public static class Talent {
        int headerIcon;
        String strTitle;
    }
}
