package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ibrahim on 31/03/2019.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context){
        super(context, Database.DATABASE_NAME, null, Database.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("DROP TABLE student;");
        db.execSQL(Database.Student.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //Method to insert values to the tables
    public long insert(String table, ContentValues values){
        return getWritableDatabase().insert(table, null, values);
    }

    //Method to update values in the tables
    public int update(String table, ContentValues values, String whereClause, String[] whereArgs){
        return getWritableDatabase().update(table, values, whereClause, whereArgs);
    }

    //Method to delete records from the tables
    public int delete(String table, String whereClause, String[] whereArgs){
        return getWritableDatabase().delete(table, whereClause, whereArgs);
    }


    //Method to read records from the tables
    public Cursor read(String table, String columns[], String whereClause, String[] whereArgs,
                       String groupByClause, String havingClause, String orderByClause, String limit){
        return getReadableDatabase().query(table, columns, whereClause, whereArgs, groupByClause,
                havingClause, orderByClause, limit);
    }
}
