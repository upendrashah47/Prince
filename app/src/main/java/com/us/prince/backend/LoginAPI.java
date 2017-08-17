package com.us.prince.backend;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.us.prince.bean.UserBean;
import com.us.prince.util.Config;
import com.us.prince.util.Utils;

import java.util.HashMap;
import java.util.Map;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by hardikjani on 6/29/16.
 */
public class LoginAPI {


    RelativeLayout rel_login5;
    TextView txtmessage;


    public LoginAPI(Context context, final ResponseListener responseListener, String username, String password) {

        HashMap<String, String> mParams = new HashMap<String, String>();
        mParams.put("password", password);
        mParams.put("username", username);
       /* mParams.put("device_type", "2");
        mParams.put("udid", "adasdasdad454");
        mParams.put("push_id", "");
*/
        LoginRoutAPI apiMethod = Utils.getAdapter().create(LoginRoutAPI.class);
        apiMethod.getBean(mParams, new Callback<UserBean>() {
            @Override
            public void success(UserBean userBean, Response response) {


                if (response.getStatus() == 200) {

                    if (userBean.code == 0) {

                        responseListener.onResponce(Config.TAG_LOGIN, Config.RESULT_OK, userBean);
                    } else {

                        responseListener.onResponce(Config.TAG_LOGIN, Config.RESULT_FAIL, userBean.mesg);
                        System.out.println("==========oginbean.mesg====" + userBean.mesg);
                    }
                } else {
                    responseListener.onResponce(Config.TAG_LOGIN, Config.RESULT_FAIL, null);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("=======Login url==================" + error.getUrl());
                responseListener.onResponce(Config.TAG_LOGIN, Config.RESULT_FAIL, error.toString());
            }
        });
    }

    public interface LoginRoutAPI {
        @Headers(Config.API_HEADER)
        @POST(Config.API_LOGIN)
        void getBean(@Body Map<String, String> params, Callback<UserBean> callback);

//        @POST(Config.API_LIKE + "/{user_id}/{post_id}/{status}")
//        void getBean(@Path("user_id") String user_id, @Path("post_id") String post_id, @Path("status") String status, @Body Map<String, String> params, @Body Callback<PostBean> callback);

//        @GET(Config.API_SYNC)
//        void getBean(Callback<SycnBean> callback);
    }
}