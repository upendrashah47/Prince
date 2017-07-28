package com.us.prince.ui;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.us.prince.R;
import com.us.prince.util.Log;

/**
 * Created by Upen on 26/7/17 in Prince.
 */

public class HomeActivity extends BaseActivity {

    private Context context;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = HomeActivity.this;

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        Log.print("================== "+user.getEmail());
    }

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }
}
