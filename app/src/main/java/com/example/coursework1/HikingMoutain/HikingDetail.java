package com.example.coursework1.HikingMoutain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.Button;

import com.example.coursework1.DatabaseHelper;
import com.example.coursework1.R;

import java.util.ArrayList;

public class HikingDetail extends AppCompatActivity {

    RecyclerView recyclerView;
    HikingAdapter detailAdapter;
    DatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    Context context;
    private ArrayList <HikingModel> models;
    private ArrayList<HikingModel> filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiking_detail);

        databaseHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recyclerView);

        ArrayList<HikingModel> models = getDetailsFromDatabase();

        detailAdapter = new HikingAdapter(this, models);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(detailAdapter);

        SearchView searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                detailAdapter.filterList(newText);
                return true;
            }
        });

        Button buttonDeleteAll = (Button) findViewById(R.id.deleteAll);
       buttonDeleteAll.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               new AlertDialog.Builder(context)
                       .setTitle("Confirm Delete")
                       .setMessage("Are you sure you want to delete everything?")
                       .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               databaseHelper.deleteAllDetail();
                               models.clear();
                               models.addAll(getDetailsFromDatabase());
                           }
                       })
                       .setNegativeButton("No", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {

                           }
                       })
                       .show();
           }
       });

       Button buttonBack = (Button) findViewById(R.id.buttonHome);
       buttonBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Back();
           }
       });
    }
    private ArrayList<HikingModel> getDetailsFromDatabase(){
        ArrayList<HikingModel> models = new ArrayList<>();
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT Trip_id, Name, Elevation, Distance, Parking_available, td, Level, Date, Location, Description FROM hiking_details", null);


        if (cursor.moveToFirst()){
            int nameIndex = cursor.getColumnIndex("name");
            int elevationIndex = cursor.getColumnIndex("elevation");
            int distanceIndex = cursor.getColumnIndex("distance");
            int parking_availableIndex = cursor.getColumnIndex("parking_available");
            int tdIndex = cursor.getColumnIndex("td");
            int levelIndex = cursor.getColumnIndex("level");
            int dateIndex = cursor.getColumnIndex("date");
            int locationIndex = cursor.getColumnIndex("location");
            int descriptionIndex = cursor.getColumnIndex("description");

            if (nameIndex >= 0 && elevationIndex >= 0 && distanceIndex >= 0 && parking_availableIndex >= 0 && tdIndex >= 0 && levelIndex >= 0 && dateIndex >= 0 && locationIndex >= 0 && descriptionIndex >= 0){
                do{
                    String name = cursor.getString(nameIndex);
                    String elevation = cursor.getString(elevationIndex);
                    String distance = cursor.getString(distanceIndex);
                    String parking_available = cursor.getString(parking_availableIndex);
                    String td = cursor.getString(tdIndex);
                    String level = cursor.getString(levelIndex);
                    String date = cursor.getString(dateIndex);
                    String location = cursor.getString(locationIndex);
                    String description = cursor.getString(descriptionIndex);

                    HikingModel detailModel = new HikingModel();
                    detailModel.setName(name);
                    detailModel.setElevation(elevation);
                    detailModel.setDistance(distance);
                    detailModel.setParking_available(Integer.parseInt(parking_available));
                    detailModel.setTd(Integer.parseInt(td));
                    detailModel.setLevel(Integer.parseInt(level));
                    detailModel.setDate(date);
                    detailModel.setLocation(location);
                    detailModel.setDescription(description);

                    models.add(detailModel);
                }while (cursor.moveToNext());
            }
        }
        cursor.close();
        filteredList = new ArrayList<>(models);
        return models;
    }

    private void Back(){
        Intent f = new Intent(this, MainActivity.class);
        startActivity(f);
    }


}