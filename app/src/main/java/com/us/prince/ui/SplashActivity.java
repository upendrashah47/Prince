package com.us.prince.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.us.prince.R;
import com.us.prince.backend.ResponseListener;
import com.us.prince.util.Config;
import com.us.prince.util.Log;
import com.us.prince.util.Pref;
import com.us.prince.util.Utils;

public class SplashActivity extends BaseActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = SplashActivity.this;

//        Log.WHICH_HOST = Utils.getResourceSting(context, R.string.apiHostData);
        Utils.systemUpgrade(SplashActivity.this);
        Utils.getDeviceID(context);
//        if (Utils.isOnline(context)) {
//            syncAPI();
//        } else {
        try {
            Thread splashThread = new Thread() {
                public void run() {
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        Log.error(getClass().getName() + ":splashThread", e);
                    }
                    handler.sendEmptyMessage(0);
                }
            };
            splashThread.start();
        } catch (Exception e) {
            Log.error(this.getClass() + "::splashThread::", e);
        } finally {
            Utils.freeMemory();
        }
//        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

//    private void syncAPI() {
//        if (Utils.isOnline(context)) {
//            syncAPI = new SyncAPI(context, responseListener);
//            syncAPI.execute();
//        } else {
//            Pref.setValue(context, Config.PREF_IS_INTERNET_DIALOG_OPEN, 0);
//            if (Pref.getValue(context, Config.PREF_IS_LOGIN, 0) == 1) {
//                if (Pref.getValue(context, Config.PREF_ROLE, 0) == 2) {
//                    Utils.intentCall(SplashActivity.this, ContributorDashboardActivity.class, true);
//                } else if (Pref.getValue(context, Config.PREF_ROLE, 0) == 3) {
//                    Utils.intentCall(SplashActivity.this, MeetingActivity.class, true);
//                }
//            } else {
//                Utils.intentCall(SplashActivity.this, LoginActivity.class, true);
//            }
//        }
//    }

//    ResponseListener responseListener = new ResponseListener() {
//        @Override
//        public void onResponce(String tag, int result, Object obj) {
//            if (result == Config.API_SUCCESS) {
//                if (tag.equals(Utils.getResourceSting(context, R.string.syncTAG))) {
//                    if (Pref.getValue(context, Config.PREF_IS_LOGIN, 0) == 1) {
//                        if (Pref.getValue(context, Config.PREF_ROLE, 0) == 2) {
//                            Utils.intentCall(SplashActivity.this, ContributorDashboardActivity.class, true);
//                        } else if (Pref.getValue(context, Config.PREF_ROLE, 0) == 3) {
//                            if (Pref.getValue(context, Config.PREF_PAYMENT_STATUS, 0) == 4) {
//                                Utils.intentCall(SplashActivity.this, ProfileActivity.class, true);
//                            } else {
//                                Utils.intentCall(SplashActivity.this, MeetingActivity.class, true);
//                            }
//                        }
//                    } else {
//                        Utils.intentCall(SplashActivity.this, LoginActivity.class, true);
//                    }
//                }
//            }
//            syncAPI = null;
//        }
//    };

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
//            syncAPI();
            if (Pref.getValue(context, Config.PREF_IS_LOGIN, 0) == 1) {
                Intent intent = new Intent(context, HomeActivity.class);
                startActivity(intent);

            } else {
                Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
            }
        }
    };
}