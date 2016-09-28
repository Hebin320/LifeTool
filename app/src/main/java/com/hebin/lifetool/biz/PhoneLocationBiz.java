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
import com.hebin.lifetool.entity.DataEntity;
import com.hebin.lifetool.entity.PhoneLocationEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 手机号码归属地查询Biz
 */

public class PhoneLocationBiz implements IBaseBiz {
    @Override
    public void getData(Context context, Object T, final IBaseOnListener onListener) {
        DataEntity dataEntity = (DataEntity) T;
        final String path = Baseurl.phone + dataEntity.getPhone() ;
        System.out.println("Hebin" + path);
        MyJsonObjectRequest json = new MyJsonObjectRequest(path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getString("resultcode").equals("200")) {
                        Gson gson = new Gson();
                        PhoneLocationEntity phoneLocationEntity = gson.fromJson(response.toString(), PhoneLocationEntity.class);
                        onListener.getSuccess(phoneLocationEntity);
                    } else if (response.getString("resultcode").equals("202")) {
                        onListener.getFailed(202);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
