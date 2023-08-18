package com.noeliacoboaguilar.agenda3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "MyTasks.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "task_list";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "task_name";
    private static final String COLUMN_DATE = "task_date";
    private static final String COLUMN_COMPLETE = "task_complete";

    MyDataBaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_DATE + " TEXT, " +
                        COLUMN_COMPLETE + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addTask(String name, String date, String complete) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_COMPLETE, complete);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1) {
            Toast.makeText(context, R.string.fail, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, R.string.add_sucess, Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null) {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    void updateData(String row_id, String name, String date, String complete) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_COMPLETE, complete);

        long result = db.update(TABLE_NAME, cv, "id=?", new String[]{row_id});
        if(result == -1) {
            Toast.makeText(context, R.string.fail, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, R.string.update, Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "id=?", new String[]{row_id});
        if(result == -1) {
            Toast.makeText(context, R.string.fail, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, R.string.delete_sucess, Toast.LENGTH_SHORT).show();
        }
    }
}
