package com.hebin.lifetool.biz;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.hebin.lifetool.base.Baseurl;
import com.hebin.lifetool.biz.base.IBaseBiz;
import com.hebin.lifetool.biz.base.IBaseOnListener;
import com.hebin.lifetool.custom.volley.MyJsonObjectRequest;
import com.hebin.lifetool.custom.volley.VolleyController;
import com.hebin.lifetool.entity.BusInfoEntity;
import com.hebin.lifetool.entity.DataEntity;
import com.hebin.lifetool.entity.HistoryEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 长途汽车信息
 */

public class HistoryBiz implements IBaseBiz {

    @Override
    public void getData(Context context, Object T, final IBaseOnListener onListener) {
        DataEntity dataEntity = (DataEntity) T;
        String path = Baseurl.historyList + dataEntity.getMonth() + "&day=" + dataEntity.getDay();
        System.out.println("Hebin" + path);
        MyJsonObjectRequest json = new MyJsonObjectRequest(path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                HistoryEntity historyEntity = gson.fromJson(response.toString(), HistoryEntity.class);
                onListener.getSuccess(historyEntity);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onListener.getFailed(404);
            }
        });
        VolleyController.getInstance(context).addToRequestQueue(json);

    }
}
