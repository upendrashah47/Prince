package com.us.prince.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.us.prince.R;
import com.us.prince.util.Config;
import com.us.prince.util.Log;
import com.us.prince.util.Pref;
import com.us.prince.util.Utils;

/**
 * Created by Upen on 26/7/17 in Prince.
 */

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Context context;

    private EditText edtEmail;
    private EditText edtPassword;
    private TextView txtLogin;
    private TextView txtLoginWithFb;
    private TextView txtLoginWithGmail;
    private TextView txtForgotPassword;
    private TextView txtRegisterHere;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = LoginActivity.this;

        findViewById();
        initFirebase();

    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View view) {
        String validate;
        switch (view.getId()) {
            case R.id.txtLogin:
                validate = validation();
                if (validate == null) {
                    signInWithEmailAndPassword(edtEmail.getText().toString().trim(), edtPassword.getText().toString().trim());
                } else {
                    Toast.makeText(context, validate, Toast.LENGTH_SHORT).show();

                }

                break;

            case R.id.txtLoginWithFb:

                break;

            case R.id.txtLoginWithGmail:

                break;

            case R.id.txtForgotPassword:

                break;

            case R.id.txtRegisterHere:
                validate = validation();
                if (validate == null) {
                    createUserWithEmailAndPassword(edtEmail.getText().toString().trim(), edtPassword.getText().toString().trim());
                } else {
                    Toast.makeText(context, validate, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            Pref.setValue(context, Config.PREF_IS_LOGIN, 1);
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
        }
    }

    public void findViewById() {

        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        txtLogin = (TextView) findViewById(R.id.txtLogin);
        txtLoginWithFb = (TextView) findViewById(R.id.txtLoginWithFb);
        txtLoginWithGmail = (TextView) findViewById(R.id.txtLoginWithGmail);
        txtForgotPassword = (TextView) findViewById(R.id.txtForgotPassword);
        txtRegisterHere = (TextView) findViewById(R.id.txtRegisterHere);

        txtLogin.setOnClickListener(this);
        txtLoginWithFb.setOnClickListener(this);
        txtLoginWithGmail.setOnClickListener(this);
        txtForgotPassword.setOnClickListener(this);
        txtRegisterHere.setOnClickListener(this);

    }

    public String validation() {
        String valid = null;
        if (edtEmail.getText().toString().trim().equals("")) {
            valid = getResources().getString(R.string.validemail);
            this.edtEmail.requestFocus();
            this.edtEmail.setSelection(this.edtEmail.length());
        } else if (!Utils.Email_address_pattern.matcher(this.edtEmail.getText().toString().trim().toLowerCase()).matches()) {
            valid = getResources().getString(R.string.invalidEmail);
            this.edtEmail.requestFocus();
            this.edtEmail.setSelection(this.edtEmail.length());
        } else if (edtEmail.getText().toString().trim().length() > 100) {
            valid = getResources().getString(R.string.invalidEmailLength);
            this.edtEmail.requestFocus();
            this.edtEmail.setSelection(this.edtEmail.length());
        } else if (edtPassword.getText().toString().trim().equals("")) {
            valid = getResources().getString(R.string.validblankpassword);
            this.edtPassword.requestFocus();
            this.edtPassword.setSelection(this.edtPassword.length());
        } else if (edtPassword.getText().toString().trim().length() < 8 || edtPassword.getText().toString().trim().length() > 20) {
            valid = getResources().getString(R.string.invalidnewpasswordlength);
            this.edtPassword.requestFocus();
            this.edtPassword.setSelection(this.edtPassword.length());
        }
        return valid;
    }

    public void initFirebase() {
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void createUserWithEmailAndPassword(String email, String password) {

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Pref.setValue(context, Config.PREF_IS_LOGIN, 1);
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(context, "Authentication failed for registration.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void signInWithEmailAndPassword(String email, String password) {

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Pref.setValue(context, Config.PREF_IS_LOGIN, 1);
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(context, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
