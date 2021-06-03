package com.example.a757repsys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainMenu1 extends AppCompatActivity {

    Button newreport, reportstatus;
    ImageView viewReport, uploadbtn, submitbtn, cameralogo, profilebtn, logoutbtn;
    TextView viewEmergency, viewReportCategory, viewLocation, viewReportDesc, refNumTest;
    Spinner emergency, report;
    EditText txtLocation, txtReportDesc;
    ArrayAdapter<CharSequence> adapter1, adapter2;
    String reportN,reportE;
    Reporter Reporter;
    int maxid,temp1,temp2;

    //FIREBASE
    FirebaseUser USER;
    FirebaseDatabase database;
    FirebaseAuth mAuth;
    //DatabaseReference mDatabase,mDatabase2;
    FirebaseFirestore db;

    Map<String, Object> reportDetails = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu1);
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();


        newreport = findViewById(R.id.btnNewReport);
        reportstatus = findViewById(R.id.btnReportStatus);
        viewReport = findViewById(R.id.imgViewReport);
        uploadbtn = findViewById(R.id.imgViewUploadBtn);

        submitbtn = findViewById(R.id.imgViewSubmitBtn);//sendreport

        cameralogo = findViewById(R.id.imgViewCameraLogo);
        profilebtn = findViewById(R.id.imgViewProfileBtn);
        logoutbtn = findViewById(R.id.imgViewLogOutBtn);

        viewEmergency = findViewById(R.id.txtViewEmergencyCategory);
        viewReportCategory = findViewById(R.id.txtViewReportCategory);

        viewLocation = findViewById(R.id.txtViewLocation);
        viewReportDesc = findViewById(R.id.txtViewReportDescription);
        emergency = findViewById(R.id.spnEmegencyCategory);
        report = findViewById(R.id.spnReportCategory);

        txtLocation = findViewById(R.id.editTxtLocation);
        txtReportDesc = findViewById(R.id.editTxtReportDescription);
        refNumTest = findViewById(R.id.editTextTestRefNumber);


        adapter1 = ArrayAdapter.createFromResource(this,R.array.Emergency, android.R.layout.simple_spinner_item);
        adapter2 = ArrayAdapter.createFromResource(this,R.array.Reports, android.R.layout.simple_spinner_item);

        emergency.setAdapter(adapter1);
        report.setAdapter(adapter2);
        refNumTest.setEnabled(false);



        emergency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView)parentView.getChildAt(0)).setTextColor(Color.BLACK);
                reportE = parentView.getItemAtPosition(position).toString();
                temp1=position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //test sender

        report.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                ((TextView)parentView.getChildAt(0)).setTextColor(Color.BLACK);
                reportN = parentView.getItemAtPosition(position).toString();
                temp2=position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String loc = txtLocation.getText().toString();
                String desc = txtReportDesc.getText().toString();
                //insert logic to choose one
                if (temp1==0&&temp2==0){
                    Toast.makeText(getApplicationContext(), "Please Select a Report Category",
                            Toast.LENGTH_SHORT).show();
                }else if (temp1!=0&&temp2==0){
                    sendEreport(loc,desc);
                }else if (temp2!=0&&temp1==0){
                    sendNreport(loc,desc);
                }else
                    Toast.makeText(getApplicationContext(), "Please Select One Report Category"+temp2,
                            Toast.LENGTH_SHORT).show();


            }
        });


        reportstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu1.this,MainMenu2.class));
                finish();
            }
        });

        //how to upload images into FIRESTORE
        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        uploadbtn.setOnTouchListener(new View.OnTouchListener() {
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



        submitbtn.setOnTouchListener(new View.OnTouchListener() {
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

        profilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu1.this,ProfilePage.class));

                finish();
            }
        });

        profilebtn.setOnTouchListener(new View.OnTouchListener() {
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

        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(MainMenu1.this,LoginScreen.class));
                finish();
            }
        });

        logoutbtn.setOnTouchListener(new View.OnTouchListener() {
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
    public void sendEreport(String loc, String desc) {

        //String keyId = currentUser.getUid();
        //sendreport.setSpinner(emergency.getSelectedItem().toString());
        //USER = mAuth.getCurrentUser();
        //sendreport(USER);
        //Toast.makeText(getApplicationContext(),"Selected Report Category "+reportN,Toast.LENGTH_LONG).show();
        //Toast.makeText(getApplicationContext(),"Selected Report Category "+reportE,Toast.LENGTH_LONG).show();
        reportDetails.put("Report Location", loc);
        reportDetails.put("Report Description", desc);
        reportDetails.put("Report Category", reportE);
        reportDetails.put("Report Status","URGENT");
        reportDetails.put("Admin Reply","");
        reportDetails.put("User Reply","");

        String reportUID = db.collection("collection_name").document().getId();

        db.collection("Emergency Reports").document(reportUID).set(reportDetails)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Report SENT, Reference Code: ",
                                Toast.LENGTH_SHORT).show();
                        refNumTest.setEnabled(true);

                        refNumTest.setText(reportUID);

                    }
                });
    }

    public void sendNreport(String loc, String desc){

        reportDetails.put("Report Location", loc);
        reportDetails.put("Report Description", desc);
        reportDetails.put("Report Category", reportN);
        reportDetails.put("Report Status","Pending");

        String reportUID = db.collection("collection_name").document().getId();

        db.collection("Normal Reports").document(reportUID).set(reportDetails)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Report SENT, Reference Code: ",
                                Toast.LENGTH_SHORT).show();
                        refNumTest.setEnabled(true);

                        refNumTest.setText(reportUID);
                    }
                });
    }


}