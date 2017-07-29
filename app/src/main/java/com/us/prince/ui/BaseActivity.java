package com.us.prince.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.us.prince.util.Utils;


public abstract class BaseActivity extends AppCompatActivity {
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