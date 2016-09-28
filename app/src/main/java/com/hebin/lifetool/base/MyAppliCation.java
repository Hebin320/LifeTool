package com.hebin.lifetool.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import com.hebin.lifetool.utils.ToastUtils;


public class MyAppliCation extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    public static Context getAppContext() {
        return MyAppliCation.context;
    }

    public void init(){
        MyAppliCation.context = getApplicationContext();
        ToastUtils.init(this);
        getResources();
    }

    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }
}
