package com.madprateek.jecrcpay.ActivityClasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.madprateek.jecrcpay.HelperClasses.SessionManager;
import com.madprateek.jecrcpay.R;

public class MainActivity extends AppCompatActivity {

    private EditText mNameText, mMobileText;
    private Button mSubmitBtn;
    private SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNameText = (EditText) findViewById(R.id.nameText);
        mMobileText = (EditText) findViewById(R.id.mobileNoText);
        mSubmitBtn = (Button) findViewById(R.id.submitBtn);
        session = new SessionManager(getApplicationContext());

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameText.getText().toString();
                String number = mMobileText.getText().toString();
                loginUser(name, number);
            }
        });
    }

    private void loginUser(String name, String number) {
        session.createLoginSession(name,number);
        Intent intent = new Intent(MainActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
