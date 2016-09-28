package com.hebin.lifetool.utils;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

/**
*  Button、TextView等控件为View，
*  LineaLayout等布局为ViewGroup；
* */

public class LostFocus {

    /**
     * 点击控件，隐藏输入法
     */
    public static void lostByView(final Context context, final View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
                return false;
            }
        });
    }


    /**
     * 点击布局，隐藏输入法
     */
    public static void lostByViewGroup(final Context context, final ViewGroup viewGroup) {
        viewGroup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(viewGroup.getApplicationWindowToken(), 0);
                return false;
            }
        });
    }

    /**
     * 使ListView、GirdView、Recyclerview失去焦点，
     * 解决初始进入列表滚动到最前端的Bug
     */
    public static void LostListFocus(ViewGroup viewGroup) {
        viewGroup.setFocusable(true);
        viewGroup.setFocusableInTouchMode(true);
        viewGroup.requestFocus();
    }


}
