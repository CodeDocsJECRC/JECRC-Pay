package com.example.admin.navbottom;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private int counter =5;
    private EditText e1,e2;
    private TextView SIGNUP;
    private Button login;
    private ProgressDialog progressDialog;
    private TextView t;
    String namegive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e1=(EditText)findViewById(R.id.username);
        e2=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.loginn);
        firebaseAuth= FirebaseAuth.getInstance();
        SIGNUP=(TextView)findViewById(R.id.button2);
        t=(TextView)findViewById(R.id.textView);
        progressDialog=new ProgressDialog(this);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.white);

        //to check current user
        if(firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));

        }

        SIGNUP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),reg_activity.class);
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
                    Toast.makeText(Login.this, ""+e, Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    private void validate()
    {   String  User=e1.getText().toString().trim();
        String Pass=e2.getText().toString().trim();



        progressDialog.setMessage("Loading...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(User,Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {    //ADDCLICK ON LISTENER IS USED TO CHECK THAT THE TASK IS SUCCESSFUL OR NOT
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                { progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Login success!", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    finish();

                }
                else
                {progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
                    counter--;
                    t.setText("No. of Attempts Remaining "+ String.valueOf(counter));
                    if(counter==0)
                        login.setEnabled(false);

                }
            }
        });
    }

}

