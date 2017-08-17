package com.us.prince.backend;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.us.prince.bean.UserBean;
import com.us.prince.util.Config;
import com.us.prince.util.Log;

import org.json.JSONObject;

import java.io.File;

/**
 * Created by user on 29/7/16.
 */
public class AddPostAPI extends AsyncTask<Void, Void, Integer> {
    public Context mCaller;
    public ResponseListener handler;
    public String mesg;
    public MultipartRequest multipartReq;
    public File file = null;
    public UserBean userBean;

    public AddPostAPI(Context caller, UserBean userBean, ResponseListener handler) {
        System.out.println("=============inside constructer===========");
        this.mCaller = caller;
        this.handler = handler;
        this.userBean = userBean;
    }

    @Override
    protected Integer doInBackground(Void... params) {
        int result = -1;
        try {
            multipartReq = new MultipartRequest(mCaller);
           /* if (this.addPostBean.banner != null && (this.addPostBean.banner != "" || !this.userBean.coverImage.equals("") || !this.userBean.coverImage.equals("null"))) {
                file = new File(Config.DIR_USERDATA+"/"+userBean.coverImage);
                if (file.exists()) {
                    multipartReq.addFile(Config.wallpaper, file.toString(), file.getName());
                }
            }

            if (this.userBean.avatar != null && (this.userBean.avatar != "" || !this.userBean.avatar.equals("") || !this.userBean.avatar.equals("null"))) {
                file = new File(Config.DIR_USERDATA+"/"+userBean.avatar);
                if (file.exists()) {
                    multipartReq.addFile(Config.avatar, file.toString(),
                            file.getName());
                }
            }*/

            // multipartReq.addString("user_id", String.valueOf(Pref.getValue(mCaller, Config.PREF_USER_ID, 0)));
            file = new File(Config.DIR_USERDATA + "/" + "filename");

            multipartReq.addString("title", "title");
            multipartReq.addString("content", "content");
            multipartReq.addString("cat_id", "cat_id");
            multipartReq.addFile("banner", file.toString(), file.getName());


            //  Config.API_ADDPOST = Config.HOST + Config.API_PROFILE_JSON +"/"+ String.valueOf(Pref.getValue(mCaller, Config.PREF_USERID, 0));
            result = parse(multipartReq.execute(Config.HOST_URL + Config.API_LOGIN + "/" + "user_id"));
        } catch (Exception e) {
            result = -1;
            mesg = "Unable to upload please try again.";
            Log.print("" + e.toString());
            Log.error(this.getClass() + "", e);
        }
        return result;
    }

    protected void onPostExecute(Integer result) {

        if (result == 0) {
            // successful
            this.handler.onResponce(Config.TAG_LOGIN, Config.RESULT_OK, userBean);
        } else if (result > 0) {
            //WebInterface.showAPIErrorAlert(mCaller, "Alert", this.mesg);
            Log.print("==========mesg.toString()======if result>0=======" + mesg.toString());
            this.handler.onResponce(Config.TAG_LOGIN, Config.RESULT_FAIL, userBean);
        } else {
            if (this.mCaller instanceof Activity) {
                if (result == -1 || result == -2 || result == -3) {
//					WebInterface.showAPIErrorAlert(mCaller,
//							Config.ERROR_NETWORK, this.mesg);
                } else if (result == -4) {
//					WebInterface.showAPIErrorAlert(mCaller,
//							Config.ERROR_UNKNOWN, this.mesg);
                }
//                Log.print("==========mesg.toString()=======else======"+mesg.toString());
                this.handler.onResponce(Config.TAG_LOGIN, Config.RESULT_FAIL, this.userBean);
            }
        }
    }

    public int parse(String response) {
        int code = 0;
        JSONObject jsonDoc = null;
        // DBSetter dbSetter;
        try {
            Log.print("=========== RESPONSE ========" + response);
            jsonDoc = new JSONObject(response);
            userBean.code = jsonDoc.getInt(String.valueOf("code"));
            userBean.mesg = jsonDoc.getString("mesg");
            if (code == 0) {
//                Log.print("=============parse=====================" + userBean.banner);
//                Log.print("===============parse===================" + userBean.title);
//                Log.print("=============parse=====================" + userBean.cat_id);
//                Log.print("===============parse===================" + userBean.content);


                // dbSetter = new DBSetter();

                /*Pref.setValue(mCaller, Config.PREF_USERNAME, userBean.username);
                Pref.setValue(mCaller, Config.PREF_EMAIL,userBean.email);
                Pref.setValue(mCaller,Config.PREF_WEBURL,userBean.websiteLink);
                Pref.setValue(mCaller,Config.PREF_CITY,userBean.city==null?"":userBean.city);
                Pref.setValue(mCaller,Config.PREF_BIO,userBean.description);

                File file = new File(Config.DIR_USERDATA+"/" +userBean.coverImage);
                if (file.exists()) {
                    File to = new File(Config.DIR_USERDATA+"/" + dbSetter.getString(jsonDoc, Config.wallpaper));
                    file.renameTo(to);
                    Pref.setValue(mCaller, Config.PREF_COVER_IMG, dbSetter.getString(jsonDoc, Config.wallpaper));
                }
                file = null;

                file = new File(Config.DIR_USERDATA+"/" +userBean.avatar);
                if (file.exists()) {
                    File to = new File(Config.DIR_USERDATA+"/" + dbSetter.getString(jsonDoc, Config.avatar));
                    file.renameTo(to);
                    Pref.setValue(mCaller, Config.PREF_AVATAR_IMG, dbSetter.getString(jsonDoc, Config.avatar));
                }
                file = null;*/

                Log.print("==============code===============" + code);
            }
        } catch (Exception e) {
            code = -4;
            Log.error(this.getClass() + " :: parse()", e);
            Log.print("=========================e=========" + e.toString());
            e.printStackTrace();
        } finally {
            response = null;
            /** release variables */
            jsonDoc = null;
        }
        return code;
    }
}
