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
import com.hebin.lifetool.entity.ConstellationdayEntity;
import com.hebin.lifetool.entity.DataEntity;

import org.json.JSONObject;

/**
 *
 */

public class ConstellationDayBiz implements IBaseBiz {
    @Override
    public void getData(Context context, Object T, final IBaseOnListener onListener) {
        DataEntity dataEntity = (DataEntity) T;
        String path = Baseurl.constellation + dataEntity.getConsname() + "&type=" + dataEntity.getType();
        System.out.println("Hebin" + path);
        MyJsonObjectRequest json = new MyJsonObjectRequest(path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Gson gson = new Gson();
                ConstellationdayEntity constellationdayEntity = gson.fromJson(response.toString(), ConstellationdayEntity.class);
                onListener.getSuccess(constellationdayEntity);
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
