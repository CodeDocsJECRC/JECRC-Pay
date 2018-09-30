package com.example.admin.navbottom.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.navbottom.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mfirebaseAuth;
    private int counter =5;
    private EditText mUsername,mPassword;
    private TextView SIGNUP;
    private Button login;
    private ProgressDialog progressDialog;
    private TextView mAttemptText;
    String namegive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername=(EditText)findViewById(R.id.username);
        mPassword=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.loginn);
        mfirebaseAuth= FirebaseAuth.getInstance();
        SIGNUP=(TextView)findViewById(R.id.button2);
        mAttemptText=(TextView)findViewById(R.id.textView);
        progressDialog=new ProgressDialog(this);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.white);

        //to check current user
        if(mfirebaseAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));

        }

        SIGNUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    validate();
                }catch (Exception e){
                    Toast.makeText(LoginActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void validate()
    {   String  User=mUsername.getText().toString().trim();
        String Pass=mPassword.getText().toString().trim();



        progressDialog.setMessage("Loading...");
        progressDialog.show();

        mfirebaseAuth.signInWithEmailAndPassword(User,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {    //ADDCLICK ON LISTENER IS USED TO CHECK THAT THE TASK IS SUCCESSFUL OR NOT
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                { progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "LoginActivity success!", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();

                }
                else
                {progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "LoginActivity Failed!", Toast.LENGTH_SHORT).show();
                    counter--;
                    mAttemptText.setText("No. of Attempts Remaining "+ String.valueOf(counter));
                    if(counter==0)
                        login.setEnabled(false);

                }
            }
        });
    }

}

