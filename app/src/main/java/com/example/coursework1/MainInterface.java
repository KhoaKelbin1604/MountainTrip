package com.example.coursework1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.coursework1.HikingMoutain.HikingDetail;
import com.example.coursework1.HikingMoutain.MainActivity;

import android.view.View;
import android.widget.Button;

public class MainInterface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_interface);

        Button buttonDiary = (Button) findViewById(R.id.buttonTripdiary);
        buttonDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DiaryTrip();
            }
        });

        Button buttonViewDiary = (Button) findViewById(R.id.buttonViewDiary);
        buttonViewDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewTrip();
            }
        });
    }

    private void DiaryTrip(){
        Intent main = new Intent(this, MainActivity.class);
        startActivity(main);
    }
    private void ViewTrip(){
        Intent view = new Intent(this, HikingDetail.class);
        startActivity(view);
    }
}