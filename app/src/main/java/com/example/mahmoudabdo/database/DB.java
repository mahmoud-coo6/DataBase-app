package com.example.mahmoudabdo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

/**
 * Created by Mahmoud Abdo on 7/27/2017.
 */

public class DB {
    private final Context context;
    private SQLiteDatabase db;
    MySQL mySQL;
    DB(Context context){

         mySQL=new MySQL(context,"mydb",null,2);
        this.context=context;
    }

    public DB open(){
        db=mySQL.getWritableDatabase();
        return this;
    }

    public DB addStudent(String student){
        ContentValues values=new ContentValues();
        values.put("NAME",student);
        long id= db.insert(MySQL.STUDENT_TABLE,MySQL.STUDENT_NAME,values);
        return this;
//        SQLiteDatabase database=db.wri


    }

    public Cursor getStudent(){
      //  Cursor cursor=db.rawQuery("select NAME from STUDENTS",null);
        Cursor cursor=db.rawQuery("select * from STUDENTS",null);
        return cursor;
    }

    public Cursor getStudent(int id){
        Cursor cursor=db.rawQuery("select NAME from STUDENTS WHERE ID = ",new String[]{id+""});
        return cursor;

    }

    public int updateStudent(String studentid, String studentname) {
        ContentValues values=new ContentValues();
        values.put(MySQL.STUDENT_NAME,studentname);
        return db.update(MySQL.STUDENT_TABLE,values,MySQL.STUDENT_ID+"=?",new String[]{studentid});

    }

    public  int deleteStudent (String stdid){
        return  db.delete(MySQL.STUDENT_TABLE,MySQL.STUDENT_ID+"=?",new String[]{stdid});
    }

    public  long addStdprepare(String studentname){
        SQLiteStatement sqLiteStatement=db.compileStatement("insert into "+MySQL.STUDENT_TABLE+ "("+MySQL.STUDENT_NAME+")"+ " values(?)");
        sqLiteStatement.bindString(1,studentname);
        return sqLiteStatement.executeInsert();
    }
    public void close(){
        db.close();
    }

}
