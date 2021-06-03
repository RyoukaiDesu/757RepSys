package com.example.a757repsys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginScreen extends AppCompatActivity {

    ImageView login, forgotpassword, signup, brgylogo;
    TextView txtViewEmail, txtViewPassword;
    EditText email, password;

    //FIREBASE
    private FirebaseAuth mAuth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        //FIREBASE
        mAuth=FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();

        brgylogo = findViewById(R.id.imgViewBarangayLogo2);
        login = findViewById(R.id.imgViewLogInBtn);
        forgotpassword = findViewById(R.id.imgViewForgotPasswordBtn);
        signup = findViewById(R.id.imgViewSignUpBtn);
        txtViewEmail = findViewById(R.id.txtViewEmailAdd);
        txtViewPassword = findViewById(R.id.txtViewPassword);

        email = findViewById(R.id.editTxtEmailAdd);
        password = findViewById(R.id.editTxtPassword);

        //Autolog in if not logged out
        if (user!=null){
            startActivity(new Intent(LoginScreen.this,MainMenu1.class));
            finish();
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailchk = email.getText().toString().trim();
                String passchk = password.getText().toString().trim();

                // Checks User Credentials
                if (TextUtils.isEmpty(emailchk)||TextUtils.isEmpty(passchk)){
                    Toast.makeText(getApplicationContext(),"Email and Password Field Required!"
                            ,Toast.LENGTH_SHORT).show();
                }else
                    mAuth.signInWithEmailAndPassword(emailchk,passchk)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Login Successful"
                                        ,Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginScreen.this,MainMenu1.class));
                                finish();//possible error
                            }else{
                                Toast.makeText(getApplicationContext(), "Login Failed, Not Recognized"
                                        ,Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginScreen.this,LoginScreen.class));
                            }

                        }
                    });//end of ONCOMPLETE LISTENER

            }
        });//end of LOGIN CLICKLISTENER

        login.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return false;
            }
        });

        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginScreen.this,ForgetPasswordPage.class));
                finish();
            }
        });

        forgotpassword.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return false;
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginScreen.this,SignUpPage.class));
                finish();
            }
        });

        signup.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return false;
            }
        });


    }
}