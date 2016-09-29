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

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 长途汽车信息
 */

public class BusInfoBiz implements IBaseBiz {

    @Override
    public void getData(Context context, Object T, final IBaseOnListener onListener) {
        DataEntity dataEntity = (DataEntity) T;
        String path = Baseurl.busInfo + dataEntity.getStation();
        System.out.println("Hebin" + path);
        MyJsonObjectRequest json = new MyJsonObjectRequest(path, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    if (response.getString("reason").equals("查询成功")) {
                        Gson gson = new Gson();
                        BusInfoEntity infoEntity = gson.fromJson(response.toString(), BusInfoEntity.class);
                        onListener.getSuccess(infoEntity);
                    } else {
                        onListener.getFailed(208202);
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
