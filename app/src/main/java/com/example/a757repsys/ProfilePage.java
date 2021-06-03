package com.example.a757repsys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class ProfilePage extends AppCompatActivity {

    ImageView backBtn3, viewUserInfo, updateprofilebtn, changepasswordbtn;
    TextView viewProfile, viewName1, viewName2, viewEmail1, viewEmail2, viewContact1, viewContact2, viewAddress1, viewAddress2, viewEditProfile, viewChangePassword;

    //FIREBASE
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private static final String Users = "Users";
    private String uid;
    private static final String first = "first";
    private static final String last = "last";
    FirebaseFirestore db;
    FirebaseAuth mAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        uid = mAuth.getCurrentUser().getUid();
        DocumentReference Docref = db.collection("Users").document(uid);

       // database = FirebaseDatabase
        //        .getInstance("https://repsys-14ce6-default-rtdb.asia-southeast1.firebasedatabase.app/");

        //FIREBASE

        backBtn3 = findViewById(R.id.imgBackBtn3);
        viewUserInfo = findViewById(R.id.imgViewUserInformation);
        updateprofilebtn = findViewById(R.id.imgViewUpdateProfileBtn);
        changepasswordbtn = findViewById(R.id.imgViewChangePasswordBtn);

        viewProfile = findViewById(R.id.txtViewProfile);
        viewName1 = findViewById(R.id.txtViewProfileName);
        viewName2 = findViewById(R.id.txtViewProfileName2);//this

        viewEmail1 = findViewById(R.id.txtViewProfileEmail1);
        viewEmail2 = findViewById(R.id.txtViewProfileEmail2);//this

        viewContact1 = findViewById(R.id.txtViewProfileContanctNo1);
        viewContact2 = findViewById(R.id.txtViewProfileContanctNo2);//this

        viewAddress1 = findViewById(R.id.txtViewProfileAddress1);
        viewAddress2 = findViewById(R.id.txtViewProfileAddress2);//this

        viewEditProfile = findViewById(R.id.txtViewEditInformation);
        viewChangePassword = findViewById(R.id.txtViewChangePassword);

        Docref.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {

                viewAddress2.setText(value.getString("Address"));
                viewContact2.setText(value.getString("Contact"));
                viewEmail2.setText(value.getString("Email"));
                String fname= value.getString("FirstName");
                String lname = value.getString("LastName");
                viewName2.setText(fname + " "+lname);

            }
        });



        backBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePage.this,MainMenu1.class));
                finish();
            }
        });

        backBtn3.setOnTouchListener(new View.OnTouchListener() {
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

        //how to update profile information
        updateprofilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePage.this,UpdateProfilePage.class));
                finish();
            }
        });

        updateprofilebtn.setOnTouchListener(new View.OnTouchListener() {
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


        changepasswordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilePage.this,ChangePasswordPage.class));
                finish();
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

}
/*mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String firstname = snapshot.child("firstName").getValue(String.class);
                String lastname = snapshot.child("lastName").getValue(String.class);
                String contactno = snapshot.child("phoneNumber").getValue(String.class);
                String address = snapshot.child("address").getValue(String.class);
                String passemail=snapshot.child("email").getValue(String.class);
                viewName2.setText(firstname+" "+lastname);

                viewEmail2.setText(passemail);

                viewContact2 .setText(contactno);

                viewAddress2.setText(address);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/