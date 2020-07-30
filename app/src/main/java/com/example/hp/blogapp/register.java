package com.example.hp.blogapp;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.StorageReference;

import java.util.Objects;

public class register extends AppCompatActivity {
    private ProgressBar progressReg;
    private EditText emailR,passR,passCon;
    private Button registerB;
    private TextView loggedIn;
    private FirebaseAuth mAuth;
    private FirebaseFirestore fireStore;
    private String user_id;
    private Uri main_uri;
    private Uri upload_uri;
    private StorageReference mStorageRef;
    private RelativeLayout rlayout;
    private Animation animation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        rlayout = findViewById(R.id.rlayout);
        animation = AnimationUtils.loadAnimation(this,R.anim.uptodowndiagonal);
        rlayout.setAnimation(animation);


        emailR = findViewById(R.id.email);
        passR = findViewById(R.id.pass);
        passCon = findViewById(R.id.repass);
        registerB =findViewById(R.id.sign_up);
        mAuth = FirebaseAuth.getInstance();
        registerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mail,pass,passC;
                mail = emailR.getText().toString().trim();
                pass = passR.getText().toString().trim();
                passC = passCon.getText().toString().trim();


                if(!TextUtils.isEmpty(mail) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(passC)){
                    if(pass.equals(passC)){
                        mAuth.createUserWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(register.this,"Welcome",Toast.LENGTH_LONG).show();
                                    Intent acS = new Intent(register.this,Dashboard.class);
                                    startActivity(acS);
                                    finish();
                                }
                                else{
                                    String error = Objects.requireNonNull(task.getException()).getMessage();
                                    Toast.makeText(register.this,"Error:"+error,Toast.LENGTH_LONG).show();
                                }

                            }
                        });

                    }
                    else{
                        Toast.makeText(register.this,"Passwords don't match",Toast.LENGTH_LONG).show();

                    }
                }

            }


        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser cur = mAuth.getCurrentUser();
        if(cur != null){
            sendToMain();
        }
    }

    private void sendToMain() {
        Intent main = new Intent(register.this,Dashboard.class);
        startActivity(main);
    }

    private void sendToLogin(){
        Intent log = new Intent(register.this,MainActivity.class);
        startActivity(log);
    }
}
