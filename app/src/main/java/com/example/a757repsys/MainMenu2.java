package com.example.a757repsys;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class MainMenu2 extends AppCompatActivity {

    Button newreport2, reportstatus2;
    ImageView viewReportStatus, searchbtn, submitbtn;
    TextView viewReportID, viewReply, viewReportCategory, viewReportDescription, viewLocation, viewReply2, viewSearch, viewSubmit,viewRepCat,viewRepDesc,viewStat,viewRep;
    private String RUID;
    EditText txtReportID, txtReply;
    FirebaseFirestore db;
    DocumentReference Docref,Docref2;
    Map<String, Object> reportDetails = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu2);

        db = FirebaseFirestore.getInstance();

        newreport2 = findViewById(R.id.btnNewReport2);
        reportstatus2 = findViewById(R.id.btnReportStatus2);
        viewReportStatus = findViewById(R.id.imgViewReportStatus);
        searchbtn = findViewById(R.id.imgViewSearchBtn);
        submitbtn = findViewById(R.id.imgViewSubmitBtn2);
        //not displaytxt
        viewReportID = findViewById(R.id.txtViewReportID);
        viewReply = findViewById(R.id.txtViewReply);
        viewReportCategory = findViewById(R.id.txtViewReportCategory2);
        viewReportDescription = findViewById(R.id.txtViewReportDescription2);
        viewLocation = findViewById(R.id.txtViewLocation);
        viewReply2 = findViewById(R.id.txtViewReply2);
        viewSearch = findViewById(R.id.txtViewSearch2);
        viewSubmit = findViewById(R.id.txtViewSubmit2);
        //pull locations
        viewRepCat = findViewById(R.id.textView5);
        viewRepDesc = findViewById(R.id.textView10);
        viewStat = findViewById(R.id.textView4);
        viewRep = findViewById(R.id.textView7);
        //edit texts
        txtReportID = findViewById(R.id.editTxtReportID);
        txtReply = findViewById(R.id.editTxtReply);

        newreport2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainMenu2.this,MainMenu1.class));
                finish();
            }
        });

        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RUID = txtReportID.getText().toString();

                //how to search for documents in 2 different collections FIRESTORE DATABASE
                Docref = db.collection("Emergency Reports").document(RUID);

                Docref2 = db.collection("Normal Reports").document(RUID);


                Docref.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (value!=null){
                            viewRepDesc.setText(value.getString("Report Description"));
                            viewRepCat.setText(value.getString("Report Category"));
                            viewStat.setText(value.getString("Report Status"));
                            viewRep.setText(value.getString("Admin Reply"));
                        }
                    }
                });

                Docref2.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                    @Override
                    public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                        viewRepDesc.setText(value.getString("Report Description"));
                        viewRepCat.setText(value.getString("Report Category"));
                        viewStat.setText(value.getString("Report Status"));
                        viewRep.setText(value.getString("Admin Reply"));
                    }
                });

            }
        });
//end of search onclick listener
        searchbtn.setOnTouchListener(new View.OnTouchListener() {
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

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replier();
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
    }

   public void replier(){
       String rep = txtReply.getText().toString();
       reportDetails.put("User Reply", rep);
       db.collection("Emergency Reports").document(RUID).update(reportDetails).addOnSuccessListener(new OnSuccessListener<Void>() {
           @Override
           public void onSuccess(Void aVoid) {

           }
       });
    }
}