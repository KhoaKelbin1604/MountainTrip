package com.example.coursework1.Observation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coursework1.R;
import com.example.coursework1.DatabaseHelper;

import java.util.ArrayList;

public class ObservationDetail extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseHelper databaseHelper;
    private ObservationAdapter observationAdapter;
    private SQLiteDatabase sqLiteDatabase2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation_detail);

        databaseHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recyclerView2);

        ArrayList<ObservationModel> model2s = getDetailsFromDatabase();

        observationAdapter = new ObservationAdapter(this, model2s);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(observationAdapter);

        Button btnBack = (Button) findViewById(R.id.buttonBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Back();
            }
        });
    }
    private ArrayList<ObservationModel> getDetailsFromDatabase(){
        ArrayList<ObservationModel> model2s = new ArrayList<>();
        sqLiteDatabase2 = databaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase2.rawQuery("SELECT Comment, Observation, editTime FROM observation", null);
        if (cursor.moveToFirst()) {
            int commentIndex = cursor.getColumnIndex("comment");
            int observationIndex = cursor.getColumnIndex("observation");
            int editTimeIndex = cursor.getColumnIndex("editTime");

            if ( commentIndex >= 0 && observationIndex >= 0 && editTimeIndex >= 0 ) {
                do {
                    String comment = cursor.getString(commentIndex);
                    String observation = cursor.getString(observationIndex);
                    String editTime = cursor.getString(editTimeIndex);


                    ObservationModel detailModel2 = new ObservationModel();
                    detailModel2.setComment(comment);
                    detailModel2.setObservation(observation);
                    detailModel2.setEditTime(editTime);

                    model2s.add(detailModel2);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return model2s;
    }

    private void Back(){
        Intent p = new Intent(this, MainActivity2.class);
        startActivity(p);
    }
}