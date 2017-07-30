package com.example.mahmoudabdo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Mahmoud Abdo on 7/27/2017.
 */

public class MySQL extends SQLiteOpenHelper {

    public static final String STUDENT_TABLE="STUDENTS";
    public static final String STUDENT_NAME="NAME";
    public static final String STUDENT_ID="_id";

    public MySQL(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE '"+STUDENT_TABLE+"' ('"+STUDENT_ID+"' INTEGER PRIMARY KEY AUTOINCREMENT , '"+STUDENT_NAME+"' TEXT );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVerision, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS STUDENTS");
        this.onCreate(db);

    }
}
