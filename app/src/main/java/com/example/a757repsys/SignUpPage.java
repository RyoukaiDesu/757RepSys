package com.example.a757repsys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class SignUpPage extends AppCompatActivity {

    ImageView backbtn1, repsys, registerbtn;
    TextView header, emailView, fnView, lnView, contactView, addView, passView, confirmpassView, registerView, txtViewTandC;
    EditText email, fn, ln, contact, add, pass, confirmpass;
    CheckBox box;

    String Fname;
    String Lname ;
    String Contact;
    String Add;

    //FIREBASE
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    //private static final String USER = "Users";
    //private Users uSER;
    private static final String TAG = "SignUpPage";
    //private Users uSER;
    FirebaseUser currentuser;
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        mAuth = FirebaseAuth.getInstance();

        backbtn1 = findViewById(R.id.imgBackBtn1);
        repsys = findViewById(R.id.imgViewRepSys);
        registerbtn = findViewById(R.id.imgViewRegisterBtn);
        header = findViewById(R.id.txtViewCreateAcc);
        emailView = findViewById(R.id.txtViewEmailAdd);
        fnView = findViewById(R.id.txtViewFirstName);
        lnView = findViewById(R.id.txtViewLastName);
        contactView = findViewById(R.id.txtViewContactNo);
        addView = findViewById(R.id.txtViewAddress);
        passView = findViewById(R.id.txtViewPassword);
        confirmpassView = findViewById(R.id.txtViewSignUpPassword2);
        registerView = findViewById(R.id.txtViewRegister);

        email = findViewById(R.id.editTxtSignUpEmailAdd);
        fn = findViewById(R.id.editTxtFirstName);
        ln = findViewById(R.id.editTxtLastName);
        contact = findViewById(R.id.editTextContactNo);
        add = findViewById(R.id.editTxtAddress);
        pass = findViewById(R.id.editTxtSignUpPassword);
        confirmpass = findViewById(R.id.editTxtSignUpConfirmPassword);
        txtViewTandC = findViewById(R.id.txtViewTermsAndConditions);
        box = findViewById(R.id.chkBxTermsAndConditions);
//FIREBASE






        backbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpPage.this,LoginScreen.class));
                finish();
            }
        });

        backbtn1.setOnTouchListener(new View.OnTouchListener() {
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



        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String conpass = confirmpass.getText().toString();
                String tempemail = email.getText().toString();
                String password = pass.getText().toString();

                Fname = fn.getText().toString();

                Lname = ln.getText().toString();

                Contact = contact.getText().toString();

                Add = add.getText().toString();

                if (TextUtils.isEmpty(tempemail)||TextUtils.isEmpty(password)||TextUtils.isEmpty(conpass)){
                    Toast.makeText(getApplicationContext(), "Enter Email and Password",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                //Data checking and Validation for Sign Up
                //checkDataEntered();

                //registerUser(tempemail,password);
                if (conpass.equals(password)){
                    registerUser(tempemail,password);
                }else {
                    Toast.makeText(getApplicationContext(), "Password Does Not Match",
                            Toast.LENGTH_SHORT).show();
                }



                                 /*//Add a new document with a generated ID
                        db.collection(USER)
                                .add(userdetails)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        //Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                        Toast.makeText(getApplicationContext(), "Data Sent"+documentReference,
                                                Toast.LENGTH_LONG).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        //Log.w(TAG, "Error adding document", e);
                                        Toast.makeText(getApplicationContext(), "Data Send failed.",
                                                Toast.LENGTH_LONG).show();
                                    }
                                });*/


            }
        });

        registerbtn.setOnTouchListener(new View.OnTouchListener() {
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

        txtViewTandC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpPage.this,TermsAndConditionsPage.class));
            }
        });

    }
    /*@Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }*/

Boolean isEmpty(EditText text) {
    CharSequence str = text.getText().toString();
    return (!TextUtils.isEmpty(str));
}
Boolean isPassword(EditText checkpass){
CharSequence cpass  = checkpass.getText().toString();
return ( !TextUtils.isEmpty(cpass));



}
    public void checkDataEntered( ){
        if (isEmpty(fn)) {
            Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
            t.show();
        }

        if (isEmpty(ln)) {
            ln.setError("Last name is required!");
        }

        if (isPassword(email) == false) {
            email.setError("Enter valid email!");
        }

    }


    public void registerUser(String tempemail,String password){
        mAuth.createUserWithEmailAndPassword(tempemail,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "Register:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            updateUI(user,tempemail,password);
                            startActivity(new Intent(SignUpPage.this,MainMenu1.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "Register:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void updateUI(FirebaseUser currentUser,String tempemail, String password){
        String key = currentUser.getUid();
        Map<String, Object> userdetails = new HashMap<>();
        userdetails.put("FirstName", Fname);
        userdetails.put("LastName", Lname);
        userdetails.put("Email", tempemail);
        userdetails.put("Password",password);
        userdetails.put("Contact", Contact);
        userdetails.put("Address", Add);
        db.collection("Users").document(key).set(userdetails)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "DATA SAVED",
                                Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "DATA NOT SAVED",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

}