package com.example.hp.blogapp;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private EditText loginEmailText,loginPassText;
    private Button loginButton;
    private ImageButton loginRegButton;
    private FirebaseAuth mAuth;
    private TextView tvLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        loginEmailText = findViewById(R.id.lname);
        loginPassText = findViewById(R.id.passw);
        loginButton = findViewById(R.id.lbutton);
        loginRegButton=findViewById(R.id.btRegister);
        tvLogin=findViewById(R.id.tvLogin);
        loginRegButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg = new Intent(MainActivity.this,register.class);
                Pair[] pairs    = new Pair[1];
                pairs[0] = new Pair<View,String>(tvLogin,"tvLogin");
                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                startActivity(reg);
                finish();
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //On clicking LOGIN login the user
                String email = loginEmailText.getText().toString().trim();
                String pasword= loginPassText.getText().toString().trim();
                if(!TextUtils.isEmpty(email)  && !TextUtils.isEmpty(pasword)){

                    mAuth.signInWithEmailAndPassword(email,pasword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Welcome",Toast.LENGTH_LONG).show();
                                sendToMain();
                            }
                            else{
                                String error = Objects.requireNonNull(task.getException()).getMessage();
                                Toast.makeText(MainActivity.this,error,Toast.LENGTH_LONG).show();
                            }

                        }
                    });
                }
                else{
                    String errorF = "Please fill all details";
                    Toast.makeText(getApplicationContext(),errorF,Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser curUser = mAuth.getCurrentUser();
        //If user already logged in send back to Main
        if(curUser != null){
            sendToMain();
        }
    }

    private void sendToMain() {
        Intent main = new Intent(MainActivity.this, Dashboard.class);
        startActivity(main);
    }

}

