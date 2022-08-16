package com.pacman.Headbud.ui.Mood;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.pacman.Headbud.R;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


public class AddMoodActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String user = FirebaseAuth.getInstance().getUid();
    TextView title;
    TextView subtitle;
    TextView quote;
    TextView selectdate;
    TextView felt;
    TextView because;
    TextView descbox;
    ImageView calendar;

    Button happy;
    Button sad;
    Button scared;
    Button lovely;
    Button depressed;
    Button flushed;
    Button sick;
    Button devil;
    Button angry;
    Button addmood;


    int moodid = MoodActivity.getmoodid()+1;
    int boolselect = -1;

    String moodtype;


    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmood);

        title = findViewById(R.id.title);
        subtitle = findViewById(R.id.subtitle);
        quote = findViewById(R.id.titlequote);

        selectdate = findViewById(R.id.selectdate);
        felt = findViewById(R.id.felt);
        because = findViewById(R.id.because);
        calendar = findViewById(R.id.calendar);
        descbox = findViewById(R.id.descbox);

        happy = findViewById(R.id.happy);
        sad = findViewById(R.id.sad);
        scared = findViewById(R.id.scared);
        lovely = findViewById(R.id.lovely);
        depressed = findViewById(R.id.depressed);
        sick = findViewById(R.id.sick);
        flushed = findViewById(R.id.flushed);
        devil = findViewById(R.id.devil);
        angry = findViewById(R.id.angry);

        addmood = findViewById(R.id.addmood);


        selectdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(
                        AddMoodActivity.this,
                        dateSetListener,
                        year, month, day);
                d.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                d.show();

            }
        });


        dateSetListener = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;
                selectdate.setText(dayOfMonth + "/" + month + "/" + year);
                Log.d("AddMoodActivity", "date:" + dayOfMonth + "/" + month + "/" + year);
            }
        };


        happy.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moodtype = "happy";
                Toast.makeText(AddMoodActivity.this, "I feel happy", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }

            }
        });
        sad.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moodtype = "sad";
                Toast.makeText(AddMoodActivity.this, "I feel sad", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);



                }
            }
        });
        sick.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moodtype = "sick";
                Toast.makeText(AddMoodActivity.this, "I feel sick", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }
            }
        });
        lovely.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moodtype = "lovely";
                Toast.makeText(AddMoodActivity.this, "I feel loved", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }
            }
        });

        scared.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moodtype = "scared";
                Toast.makeText(AddMoodActivity.this, "I feel scared", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }
            }
        });
        devil.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moodtype = "devil";
                Toast.makeText(AddMoodActivity.this, "I feel devilish", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }

            }
        });
        flushed.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moodtype = "flushed";
                Toast.makeText(AddMoodActivity.this, "I feel embarrassed", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }
            }
        });
        depressed.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moodtype = "depressed";
                Toast.makeText(AddMoodActivity.this, "I feel depressed", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }
            }
        });
        angry.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                moodtype = "angry";
                Toast.makeText(AddMoodActivity.this, "I feel angry", Toast.LENGTH_SHORT).show();
                if(boolselect == 1){
                    addmood.setEnabled(true);

                }
            }
        });


        selectdate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolselect =1;
                if(moodtype !=null){
                    addmood.setEnabled(true);

                }

            }
        });




        addmood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = v.getId();
                if (i == R.id.addmood) {
                    createMood(user, selectdate.getText().toString(), descbox.getText().toString(), moodtype, moodid);
                    moodid++;

                    Intent j = new Intent(getApplicationContext(),MoodActivity.class);
                    startActivity(j);
                }
            }
        });



    }


    public void createMood(String UID, String date, String desc, String mood, final Integer moodnum) {
        Map<String, Object> user = new HashMap<>();
        user.put("id", moodnum);
        user.put("date", date);
        user.put("description", desc);
        user.put("moodtype", mood);


        db.collection("users").document(UID).collection("moodlog").document().set(user).addOnSuccessListener(this, new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.w("SUCCESS", "DocumentSnapshot added with ID:");
            }
        }).addOnFailureListener(this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("AddingMood Failed", "Error adding document", e);
            }
        });
    }
}







