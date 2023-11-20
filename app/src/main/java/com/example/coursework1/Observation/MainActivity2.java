package com.example.coursework1.Observation;



import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Button;

import com.example.coursework1.DatabaseHelper;
import com.example.coursework1.HikingMoutain.HikingDetail;
import com.example.coursework1.R;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {
    private EditText editTime, editDay;
    private Calendar selectedDate2 = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        editTime = (EditText) findViewById(R.id.editTime);
        editTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int hour = calendar.get(Calendar.HOUR);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity2.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                                String selectedTime = String.format("%02d:%02d", hour, minute);
                                editTime.setText(selectedTime);
                                Toast.makeText(MainActivity2.this,
                                        "Selected Time: " + selectedTime, Toast.LENGTH_SHORT).show();
                            }
                        },
                        hour,
                        minute,
                        true
                );
                timePickerDialog.show();
            }
        });

        Button savebtn = (Button) findViewById(R.id.btnSave);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveObservation();
            }
        });

        Button viewbtn = (Button) findViewById(R.id.buttonView);
        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View();
            }
        });

        Button btnBack = (Button) findViewById(R.id.buttonHome);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackMain();
            }
        });
    }

    private void saveObservation(){
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity2.this);
        EditText cmtTxt = (EditText) findViewById(R.id.editComment);
        EditText observationTxt = (EditText) findViewById(R.id.editTextObservation);
        EditText timeTxt = (EditText) findViewById(R.id.editTime);

        String comment = cmtTxt.getText().toString();
        String observation = observationTxt.getText().toString().trim();
        String time = timeTxt.getText().toString().trim();

        if(observation.isEmpty() || time.isEmpty()){
            Toast.makeText(MainActivity2.this, "Please input the information in all required field", Toast.LENGTH_SHORT).show();
        } else {
            long observation_id = databaseHelper.insertObservation(comment,observation, time);
            Toast.makeText(this, "Observation has been created id:"+ observation_id, Toast.LENGTH_LONG).show();

            Intent d = new Intent(this, ObservationDetail.class);
            startActivity(d);
        }

    }

    private void View(){
        Intent v = new Intent(this, ObservationDetail.class);
        startActivity(v);
    }

    private void BackMain(){
        Intent b = new Intent(this, HikingDetail.class);
        startActivity(b);
    }


}