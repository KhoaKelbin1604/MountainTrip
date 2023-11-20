package com.example.coursework1.HikingMoutain;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.coursework1.DatabaseHelper;
import com.example.coursework1.MainInterface;
import com.example.coursework1.R;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private Calendar selectedDate = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editTextDay = findViewById(R.id.editTextDate);
        editTextDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {

                                selectedDate.set(Calendar.YEAR, year);
                                selectedDate.set(Calendar.MONTH, month);
                                selectedDate.set(Calendar.DAY_OF_MONTH, day);

                                editTextDay.setText(SimpleDateFormat.getDateInstance().format(selectedDate.getTime()));

                            }
                        },
                        selectedDate.get(Calendar.YEAR),
                        selectedDate.get(Calendar.MONTH),
                        selectedDate.get(Calendar.DAY_OF_MONTH)
                );
                datePickerDialog.show();
            }
        });

        Button savebtn = (Button) findViewById(R.id.save);
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDetail();
            }
        });

        Button viewbtn = (Button) findViewById(R.id.view);
        viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewDetail();
            }
        });

        Button homebtn = (Button) findViewById(R.id.buttonHome);
        homebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                home();
            }
        });
    }

    private void saveDetail(){
        DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
        EditText nameTxt = (EditText) findViewById(R.id.editTextName);
        EditText eleTxt = (EditText) findViewById(R.id.editTextElevation);
        EditText distanceTxt = (EditText) findViewById(R.id.editTextDistance);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        RadioButton radioButtonYes = findViewById(R.id.yes);
        RadioButton radioButtonNo = findViewById(R.id.no);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int parkingid) {
                if (parkingid == R.id.yes) {
                    radioButtonYes.setChecked(true);
                    radioButtonNo.setChecked(false);
                } else if (parkingid == R.id.no) {
                    radioButtonNo.setChecked(true);
                    radioButtonYes.setChecked(false);
                }
            }
        });

        RadioGroup radioGroup4 = findViewById(R.id.radioGroup4);
        RadioButton radioEasy = findViewById(R.id.radioButtonEasy);
        RadioButton radioHard = findViewById(R.id.radioButtonHard);
        radioGroup4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int td) {
                if (td == R.id.radioButtonEasy){
                    radioEasy.setChecked(true);
                    radioHard.setChecked(false);
                } else if (td == R.id.radioButtonHard){
                    radioHard.setChecked(true);
                    radioEasy.setChecked(false);
                }
            }
        });

        RadioGroup radioGroup2 = findViewById(R.id.radioGroup2);
        RadioButton radioHigh = findViewById(R.id.radioButtonHigh);
        RadioButton radioLow = findViewById(R.id.radioButtonLow);
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int level) {
                if(level == R.id.radioButtonHigh){
                    radioHigh.setChecked(true);
                    radioLow.setChecked(false);
                } else if (level == R.id.radioButtonLow){
                    radioLow.setChecked(true);
                    radioHigh.setChecked(false);
                }
            }
        });

        EditText dateTxt = (EditText) findViewById(R.id.editTextDate);
        EditText locationTxt = (EditText) findViewById(R.id.editTextLocation);
        EditText descriptionTxt = (EditText) findViewById(R.id.editTextDescription);


        String name = nameTxt.getText().toString().trim();
        String elevation = eleTxt.getText().toString().trim();
        String distance = distanceTxt.getText().toString().trim();
        String parking_available = String.valueOf(radioGroup.getCheckedRadioButtonId()).trim();
        String td = String.valueOf(radioGroup4.getCheckedRadioButtonId()).trim();
        String level = String.valueOf(radioGroup2.getCheckedRadioButtonId()).trim();
        String date = dateTxt.getText().toString().trim();
        String location = locationTxt.getText().toString().trim();
        String description = descriptionTxt.getText().toString().trim();

        if(name.isEmpty() || elevation.isEmpty() || distance.isEmpty() || parking_available.isEmpty()
        || td.isEmpty() || level.isEmpty() || date.isEmpty() || location.isEmpty() || description.isEmpty()){
            Toast.makeText(MainActivity.this, "Please input the information in all required field", Toast.LENGTH_SHORT).show();
        } else {
            long tripId = databaseHelper.insertDetails( name, elevation, distance, parking_available, td, level, date, location, description);
            Toast.makeText(this, "Traveler has been created with id:" + tripId, Toast.LENGTH_LONG).show();

            Intent a = new Intent(this, HikingDetail.class);
            startActivity(a);
        }

    }

    private void viewDetail(){
        Intent h = new Intent(this, HikingDetail.class);
        startActivity(h);
    }

    private void home(){
        Intent home = new Intent(this, MainInterface.class);
        startActivity(home);
    }
}