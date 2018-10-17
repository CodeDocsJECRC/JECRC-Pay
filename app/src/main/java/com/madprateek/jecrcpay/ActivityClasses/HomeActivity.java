package com.madprateek.jecrcpay.ActivityClasses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.madprateek.jecrcpay.HelperClasses.SessionManager;
import com.madprateek.jecrcpay.R;


public class HomeActivity extends AppCompatActivity {

    SessionManager session;
    private Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mToolbar = (Toolbar) findViewById(R.id.homeToolBar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("JECRC Pay");

        session = new SessionManager(HomeActivity.this);
        session.checkLogin();
    }
}
