package com.example.coursework1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.coursework1.HikingMoutain.HikingModel;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "hiking_details";
    private static final String TRIP_TABLE_NAME = "hiking_details";
    private static final String TRIP_ID_COLUMN_NAME = "trip_id";
    private static final String TRIP_NAME_COLUMN_NAME = "name";
    private static final String ELEVATION_COLUMN_ELEVATION = "elevation";
    private static final String DISTANCE_COLUMN_DISTANCE = "distance";
    private static final String PARKING_COLUMN_PARKING_AVAILABLE = "parking_available";
    private static final String TECHNICAL_COLUMN_TECHNICAL_DIFFICULTY = "td";
    private static final String LEVEL_COLUMN_LEVEL = "level";
    private static final String DATE_COLUMN_DATE = "date";
    private static final String LOCATION_COLUMN_LOCATION = "location";
    private static final String DESCRIPTION_COLUMN_DESCRIPTION = "description";

    private static final String OBSERVATIONTABLE_NAME = "observation";
    private static final String OBID_COLUMN_NAME = "observation_id";
    private static final String HIKING_COLUMN_NAME = "trip_id";
    private static final String COMMENT_COLUMN_COMMENT = "comment";
    private static final String OBSERVATION_COLUMN_OBSERVATION = "observation";
    private static final String TIME_COLUMN_TIME = "editTime";
    private SQLiteDatabase database;

    private static final String DATABASE_CREATE_QUERY = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT)",
            TRIP_TABLE_NAME, TRIP_ID_COLUMN_NAME, TRIP_NAME_COLUMN_NAME, ELEVATION_COLUMN_ELEVATION,
            DISTANCE_COLUMN_DISTANCE, PARKING_COLUMN_PARKING_AVAILABLE, TECHNICAL_COLUMN_TECHNICAL_DIFFICULTY,
            LEVEL_COLUMN_LEVEL, DATE_COLUMN_DATE, LOCATION_COLUMN_LOCATION, DESCRIPTION_COLUMN_DESCRIPTION);

    private static final String OBSERVATION_CREATE_QUERY = String.format(
            "CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "%s TEXT REFERENCES %s(%s), " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "%s TEXT, " +
                    "FOREIGN KEY (%s) REFERENCES %s (%s))",
            OBSERVATIONTABLE_NAME, OBID_COLUMN_NAME, HIKING_COLUMN_NAME, TRIP_TABLE_NAME, TRIP_ID_COLUMN_NAME,
            COMMENT_COLUMN_COMMENT, OBSERVATION_COLUMN_OBSERVATION, TIME_COLUMN_TIME,
            HIKING_COLUMN_NAME, TRIP_TABLE_NAME, TRIP_ID_COLUMN_NAME
    );


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DATABASE_CREATE_QUERY);
        db.execSQL(OBSERVATION_CREATE_QUERY);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TRIP_TABLE_NAME);
        Log.w(this.getClass().getName(), TRIP_TABLE_NAME+ " database upgrade to version " + newVersion + " old data lost");

        db.execSQL("DROP TABLE IF EXISTS " + OBSERVATIONTABLE_NAME);
        Log.w(this.getClass().getSimpleName(), OBSERVATIONTABLE_NAME + " database upgrade to version " + newVersion + " old data lost");

        onCreate(db);
    }
    public long insertDetails(String name, String elevation, String distance, String parking_available, String td, String level, String date, String location, String description) {
        ContentValues rowValues = new ContentValues();
        rowValues.put(TRIP_NAME_COLUMN_NAME, name);
        rowValues.put(ELEVATION_COLUMN_ELEVATION, elevation);
        rowValues.put(DISTANCE_COLUMN_DISTANCE, distance);
        rowValues.put(PARKING_COLUMN_PARKING_AVAILABLE, parking_available);
        rowValues.put(TECHNICAL_COLUMN_TECHNICAL_DIFFICULTY, td);
        rowValues.put(LEVEL_COLUMN_LEVEL, level);
        rowValues.put(DATE_COLUMN_DATE, date);
        rowValues.put(LOCATION_COLUMN_LOCATION, location);
        rowValues.put(DESCRIPTION_COLUMN_DESCRIPTION, description);

        return database.insertOrThrow(TRIP_TABLE_NAME, null, rowValues);
    }

    public String getDetails() {
        Cursor result = database.query(TRIP_TABLE_NAME,
                new String[]{TRIP_ID_COLUMN_NAME , TRIP_NAME_COLUMN_NAME , ELEVATION_COLUMN_ELEVATION, DISTANCE_COLUMN_DISTANCE, PARKING_COLUMN_PARKING_AVAILABLE, TECHNICAL_COLUMN_TECHNICAL_DIFFICULTY, LEVEL_COLUMN_LEVEL, DATE_COLUMN_DATE, LOCATION_COLUMN_LOCATION, DESCRIPTION_COLUMN_DESCRIPTION},
                null, null, null, null, TRIP_NAME_COLUMN_NAME );

        String resultText = "";
        result.moveToFirst();
        while (!result.isAfterLast()) {
            int id = result.getInt(0);
            String name = result.getString(1);
            String elevation = result.getString(2);
            String distance = result.getString(3);
            String parking_available = result.getString(4);
            String td = result.getString(5);
            String level = result.getString(6);
            String date = result.getString(7);
            String location = result.getString(8);
            String description = result.getString(9);

            resultText += id + " " + name + " " + elevation + " " + distance + " " + parking_available + " " + td + " " + level + " " + date + " " + location + " " + description + "\n";
            result.moveToNext();
        }
        return resultText;
    }

    public int updateDetail(int trip_id, String name, String elevation, String distance, String parking_available, String td, String level, String date, String location, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues Values = new ContentValues();
        Values.put(TRIP_NAME_COLUMN_NAME, name);
        Values.put(ELEVATION_COLUMN_ELEVATION, elevation);
        Values.put(DISTANCE_COLUMN_DISTANCE, distance);
        Values.put(PARKING_COLUMN_PARKING_AVAILABLE, parking_available);
        Values.put(TECHNICAL_COLUMN_TECHNICAL_DIFFICULTY, td);
        Values.put(LEVEL_COLUMN_LEVEL, level);
        Values.put(DATE_COLUMN_DATE, date);
        Values.put(LOCATION_COLUMN_LOCATION, location);
        Values.put(DESCRIPTION_COLUMN_DESCRIPTION, description);

        int result = db.update(TRIP_TABLE_NAME, Values, TRIP_ID_COLUMN_NAME + " = ?", new String[]{String.valueOf(trip_id)});
        db.close();
        return result;
    }

    public long insertObservation( String comment, String observation, String editTime) {
        ContentValues rowValue2 = new ContentValues();
        rowValue2.put(COMMENT_COLUMN_COMMENT, comment);
        rowValue2.put(OBSERVATION_COLUMN_OBSERVATION, observation);
        rowValue2.put(TIME_COLUMN_TIME, editTime);

        return database.insertOrThrow(OBSERVATIONTABLE_NAME, null, rowValue2);
    }
    public String getObservation() {
        Cursor results = database.query(
                OBSERVATIONTABLE_NAME,
                new String[]{OBID_COLUMN_NAME, COMMENT_COLUMN_COMMENT, OBSERVATION_COLUMN_OBSERVATION, TIME_COLUMN_TIME},
                null, null, null, null, OBSERVATION_COLUMN_OBSERVATION);

        String resultText = "";
        results.moveToFirst();
        while (!results.isAfterLast()) {
            int id = results.getInt(0);
            String comment = results.getString(1);
            String observation  = results.getString(2);
            String editTime = results.getString(3);

            resultText += id + " " + comment+ " " + observation + " " + editTime  + "\n";
            results.moveToNext();
        }
        return resultText;
    }

    public void deleteDetail(int trip_id) {
        SQLiteDatabase database = getWritableDatabase();
        int rowsAffected = database.delete(TRIP_TABLE_NAME, TRIP_ID_COLUMN_NAME  + "=?", new String[]{String.valueOf(trip_id)});
        Log.d("DatabaseHelper", "Rows affected: " + rowsAffected);
        database.close();
    }

    public void deleteAllDetail(){
        SQLiteDatabase db2 = getWritableDatabase();
        db2.delete(TRIP_TABLE_NAME, null, null);
    }


}
