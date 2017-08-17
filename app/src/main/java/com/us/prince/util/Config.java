package com.us.prince.util;

import android.app.Activity;
import android.os.Environment;

import java.util.HashMap;

public class Config {
    public static String TAG = "PocketExpert";
    public static final String PREF_FILE = TAG + "_PREF";
    /**
     * Database operations variable define here.
     */
    public static String LEVEL = "LEVEL";
    public static String DB_NAME = "pocexpdb.db";
    /*
    *  Network call variable define here.
    * */
    // Socket timeout is set to 1 minutes
    public static int TIMEOUT_SOCKET = 60000;
    // 1 = Android, 2 = Ios
    public static int DEVICE_TYPE = 1;


    // Create a directory in SD CARD
    public static String APP_HOME = Environment.getExternalStorageDirectory().getPath() + "/" + TAG;
    // A directory to store logs
    public static String DIR_LOG = APP_HOME + "/log";
    // preference file name
    public static String DIR_USERDATA = APP_HOME + "/userdata";


    // API BASIC INFO

    public static final String API_KEY = "ZEBRA_2016";
    public static final String API_HEADER = "AUTH-KEY:" + API_KEY;
    public static final String API_VERSION = "v1";


    // LOCAL
    public static String HOST = "http://192.168.1.42/work/zebra";
    // Live
    //public static String HOST = "http://zebra.esprit-apps.com";
    public static String HOST_IMAGE = HOST + "/public/media/";
    public static String IMAGE_PATH_WEB_AVATARS = HOST_IMAGE + "avatar/";
    public static String IMAGE_PATH_WEB_WALLPAPERS = HOST_IMAGE + "wallpaper/";
    public static String IMAGE_PATH_WEB_BANNER = HOST_IMAGE + "banner/";
    public static String IMAGE_PATH_ICON = HOST_IMAGE + "feed_style/";

    public static String HOST_URL = HOST + "/api/" + API_VERSION;

    //for login
    public static final String API_LOGIN = "/users/login";
    public static String TAG_LOGIN = "TAG_LOGIN";


    /*
     * Activity and internet dialog operation
     * variable define here.
     */
    public static HashMap<String, Activity> screenStack;
    public static String PREF_IS_INTERNET_DIALOG_OPEN = "PREF_IS_INTERNET_DIALOG_OPEN";
    /**
     * Permission codes
     */
    public static final int CAMARA_PERMISSION_CODE = 150;
    public static final int GALLERY_PERMISSION_CODE = 160;
    public static final int OPEN_GALLERY_IMAGE_CODE = 170;
    public static final int OPEN_CAMARA_IMAGE_CODE = 180;
    /**
     * User Pref variable define here.
     */
    public static String PREF_UDID = "PREF_UDID";
    public static String PREF_USER_ID = "PREF_USER_ID";
    public static String PREF_FB_ID = "PREF_FB_ID";
    public static String PREF_FNAME = "PREF_FNAME";
    public static String PREF_LNAME = "PREF_LNAME";
    public static String PREF_EMAIL = "PREF_EMAIL";
    public static String PREF_PASSWORD = "PREF_PASSWORD";
    public static String PREF_PROFILE_PIC = "PREF_PROFILE_PIC";
    public static String PREF_CREDITS = "PREF_CREDITS";
    public static String PREF_BIO = "PREF_BIO";
    public static String PREF_SUBSCRIPTION_ID = "PREF_SUBSCRIPTION_ID";
    public static String PREF_EXP_DATE = "PREF_EXP_DATE";
    public static String PREF_PLAN_TYPE = "PREF_PLAN_TYPE";
    /**
     * 1 = Google
     * 2 = iTune
     * 3 = Paypal
     * 4 = Credit
     */
    public static String PREF_PAYMENT_MODE = "PREF_PAYMENT_MODE";
    /**
     * 1 = Live
     * 2 = Renew (Not for App use only use for web)
     * 3 = Cancelled
     * 4 = Expired
     */
    public static String PREF_PAYMENT_STATUS = "PREF_PAYMENT_STATUS";

    /**
     * For Contributor only
     */
    public static String PREF_WEB_LINK = "PREF_WEB_LINK";
    public static String PREF_FACEBOOK_LINK = "PREF_FACEBOOK_LINK";
    public static String PREF_TWITTER_LINK = "PREF_TWITTER_LINK";
    public static String PREF_LINKEDIN_LINK = "PREF_LINKEDIN_LINK";
    public static String PREF_YOUTUBE_LINK = "PREF_YOUTUBE_LINK";

    /**
     * 1 = Active
     * 0 = Deactive
     */
    public static String PREF_IS_ACTIVE = "PREF_IS_ACTIVE";
    /*
    *  1 = individual
    *  2 = business
    * */
    public static String PREF_PLAN_FOR = "PREF_PLAN_FOR";
    /*
     * 1 = monthly
     * 2 = yearly
     * */
    public static String PREF_DURATION = "PREF_DURATION";
    public static String PREF_DATE_CANCELLED = "PREF_DATE_CANCELLED";
    public static String PREF_SUBSCRIPTION_PRICE = "PREF_SUBSCRIPTION_PRICE";
    /*
     * 0 = Not register
     * 1 = register
     * */
    public static String PREF_IS_LOGIN = "PREF_IS_LOGIN";
    public static String PREF_LAST_UPDATED = "PREF_LAST_UPDATED";
    /*
    * 0 = Register user
    * 1 = not register / new user
    * */
    public static String PREF_IS_NEW = "PREF_IS_NEW";

    //Facebook
    public static final String PREF_FBLOGGEDIN = "PREF_FBLOGGEDIN";

    /*
    * 2 = CONTRIBUTOR
    * 3 = MEMBER
    * */
    public static String PREF_ROLE = "PREF_ROLE";
    /*
    * Common variable define here
    * */
    //Facebook
    public static int FACEBOOK_REQUEST = 130;

    /**
     * Questionnaire related variable define here
     */
    public static String PREF_CAT_ID = "PREF_CAT_ID";
    public static String PREF_MAIN_QUESTION_ID = "PREF_MAIN_QUESTION_ID";
    public static String PREF_TOPIC_ID = "PREF_TOPIC_ID";

    /*
    * CMS related variable define here
    * */
    public static String PREF_TERMS_AND_CONDITIONS = "PREF_TERMS_AND_CONDITIONS";
    public static String PREF_ABOUT_US = "PREF_ABOUT_US";
    public static String PREF_PRIVACY_POLICY = "PREF_PRIVACY_POLICY";

    /*
    *  API CALL
    * */
    public static int API_SUCCESS = 0;
    public static int API_FAIL = 1;

    public static int RESULT_OK = 0;
    public static int RESULT_FAIL = 1;

    public static int totalEvents;
    public static boolean isFBSDKinit = false;
}