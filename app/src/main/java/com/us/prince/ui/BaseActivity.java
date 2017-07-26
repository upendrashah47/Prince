package com.us.prince.ui;

import android.app.Activity;
import android.os.Bundle;

import com.us.prince.R;
import com.us.prince.util.Utils;


public abstract class BaseActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
    }

    public abstract int getLayout();

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Utils.freeMemory();
//        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    @Override
    protected void onDestroy() {
        Utils.freeMemory();
        super.onDestroy();
    }
}