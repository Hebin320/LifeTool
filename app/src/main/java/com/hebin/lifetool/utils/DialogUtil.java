package com.hebin.lifetool.utils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.hebin.lifetool.R;
import com.hebin.lifetool.base.BaseMethod;


/**
 * 全局dialog
 */
public class DialogUtil {

    public static ProgressDialog progressDialog;

    public static void showpdialog(Context context) {
        showDialog(context);
    }


    //显示一个加载中的全屏对话框
    public static void showDialog(Context context) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            progressDialog.setContentView(R.layout.public_loading);
            LinearLayout layout = (LinearLayout) progressDialog.findViewById(R.id.ll_loading);
            layout.setVisibility(View.VISIBLE);
            Window window = progressDialog.getWindow();
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.width = (int) (BaseMethod.getSwidth((Activity) context) / 2.5);
            layoutParams.height = (int) (BaseMethod.getSwidth((Activity) context) / 2.5);
            window.setAttributes(layoutParams);
        }
    }

    //关闭progressdialog
    public static void closedialog() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

}
