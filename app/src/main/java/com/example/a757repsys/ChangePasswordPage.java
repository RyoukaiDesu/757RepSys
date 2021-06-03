package com.example.a757repsys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class ChangePasswordPage extends AppCompatActivity {

    ImageView backBtn4, viewCPUserInfo, changepasswordbtn;
    TextView viewChangePassword, viewOldPasword, viewNewPassword, viewConfirmPassword, viewUpdatePassword;
    EditText oldpassword, newpassword, confirmpassword;
    FirebaseFirestore db;
    FirebaseUser user;
    FirebaseAuth mAuth;
    DocumentReference Docref;
    Map<String, Object> reportDetails = new HashMap<>();
    String uid,compare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password_page);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();

        uid = mAuth.getCurrentUser().getUid();
        Docref= db.collection("Users").document(uid);

        backBtn4 = findViewById(R.id.imgBackBtn4);
        viewCPUserInfo = findViewById(R.id.imgViewUserInformation2);
        changepasswordbtn = findViewById(R.id.imgViewCPChangePasswordBtn);
        viewChangePassword = findViewById(R.id.txtViewChangePassword);
        viewOldPasword = findViewById(R.id.txtViewCPOldPassword);
        viewNewPassword = findViewById(R.id.txtViewCPNewPassword);
        viewConfirmPassword = findViewById(R.id.txtViewCPConfirmPassword);
        viewUpdatePassword = findViewById(R.id.txtViewCPUpdatePassword);

        oldpassword = findViewById(R.id.editTxtCPOldPassword);
        newpassword = findViewById(R.id.editTxtCPNewPassword);
        confirmpassword = findViewById(R.id.editTxtCPConfirmPassword);

        backBtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangePasswordPage.this,ProfilePage.class));
                finish();
            }
        });

        backBtn4.setOnTouchListener(new View.OnTouchListener() {
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


        String oldPass = oldpassword.getText().toString();

        Docref.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                 compare = value.getString("Password");

            }
        });

        //How to change password in FIREBASE FIrestore

        changepasswordbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (compare.equals(oldPass)){
                    updatepassword();
                }else{
                    Toast.makeText(getApplicationContext(), "Old Password does not Match",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        changepasswordbtn.setOnTouchListener(new View.OnTouchListener() {
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

    public void updatepassword(){
        String newPassword = newpassword.getText().toString();
        user.updatePassword(newPassword)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(), "Password is changed",
                                Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed to change password",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}