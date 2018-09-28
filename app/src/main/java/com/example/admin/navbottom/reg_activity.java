package com.example.admin.navbottom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class reg_activity extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5;
    Button b;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        e1=(EditText)findViewById(R.id.name);
        e2=(EditText)findViewById(R.id.user);

        e4=(EditText)findViewById(R.id.pass);
        //  e5=(EditText)findViewById(R.id.editText10);
        progressDialog=new ProgressDialog(this);
        b=(Button)findViewById(R.id.button3);
        firebaseAuth= FirebaseAuth.getInstance();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validate())
                {

                    //upload the data in the database

                    String usernamewe=e2.getText().toString().trim();
                    String password=e4.getText().toString().trim();                 //trim is used to remove any white spaces
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();

                    firebaseAuth.createUserWithEmailAndPassword(usernamewe,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                userprofile();
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Account Created Successfully!", Toast.LENGTH_SHORT).show();
                                progressDialog.setMessage("Loading...");
                                progressDialog.show();
                                firebaseAuth.signOut();
                                finish();
                                progressDialog.setMessage("Loading...");
                                progressDialog.show();
                                Toast.makeText(reg_activity.this, "Please Login to continue!", Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(getApplicationContext(),Login.class);
                                startActivity(i);





                            }
                            else
                                Toast.makeText(getApplicationContext(), " Failed!", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });




                }
            }
        });
    }


    private Boolean validate() {
        Boolean result = false;
        String name = e1.getText().toString();
        String email = e2.getText().toString();
        // String phone = e3.getText().toString();

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText(this, "Please Enter Details!", Toast.LENGTH_SHORT).show();

        } else
            result = true;
        return result;
    }
    private void userprofile() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            UserProfileChangeRequest userProfileChangeRequest = new UserProfileChangeRequest.Builder()
                    .setDisplayName(e1.getText().toString())
                    .build();
            // Toast.makeText(signup.this, "name updated", Toast.LENGTH_SHORT).show();
            user.updateProfile(userProfileChangeRequest).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.d("Testing", "user profile updated");
                    }
                }
            });
        }
    }
}
