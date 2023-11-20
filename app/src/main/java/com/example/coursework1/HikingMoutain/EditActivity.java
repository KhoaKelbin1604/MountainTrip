package com.example.coursework1.HikingMoutain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.coursework1.DatabaseHelper;
import com.example.coursework1.R;

public class EditActivity extends AppCompatActivity {
    private EditText nameTxt, eleTxt, distanceTxt, dateTxt, locationTxt, descriptionTxt;

    private RadioGroup radioGroup, radioGroup4, radioGroup2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

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
                if (td == R.id.radioButtonEasy) {
                    radioEasy.setChecked(true);
                    radioHard.setChecked(false);
                } else if (td == R.id.radioButtonHard) {
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
                if (level == R.id.radioButtonHigh) {
                    radioHigh.setChecked(true);
                    radioLow.setChecked(false);
                } else if (level == R.id.radioButtonLow) {
                    radioLow.setChecked(true);
                    radioHigh.setChecked(false);
                }
            }
        });

        EditText dateTxt = (EditText) findViewById(R.id.editTextDate);
        EditText locationTxt = (EditText) findViewById(R.id.editTextLocation);
        EditText descriptionTxt = (EditText) findViewById(R.id.editTextDescription);

        Button save = (Button) findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int trip_id = getIntent().getIntExtra("ID", -1);
                String name = nameTxt.getText().toString();
                String elevation = eleTxt.getText().toString();
                String distance = distanceTxt.getText().toString();
                String parking_available = String.valueOf(radioGroup.getCheckedRadioButtonId());
                String td = String.valueOf(radioGroup4.getCheckedRadioButtonId());
                String level = String.valueOf(radioGroup2.getCheckedRadioButtonId());
                String date = dateTxt.getText().toString();
                String location = locationTxt.getText().toString();
                String description = descriptionTxt.getText().toString();

                DatabaseHelper databaseHelper = new DatabaseHelper(EditActivity.this);
                databaseHelper.updateDetail(trip_id, name, elevation, distance, parking_available, td, level, date, location, description);
                finish();
            }
        });
    }

}