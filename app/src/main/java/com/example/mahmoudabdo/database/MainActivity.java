package com.example.mahmoudabdo.database;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MySQL mySQL;
    ListView listView;
    DB db;
    SimpleCursorAdapter adapter;
    Cursor cursor;
    AlertDialog.Builder build;
    AlertDialog.Builder alert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          listView=(ListView)findViewById(R.id.list);
//        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
//        listView.setAdapter(adapter);

//        MySQL db=new MySQL(this,"my db",null,1);
//        SQLiteDatabase database =  db.getWritableDatabase();
//
//        ContentValues values=new ContentValues();
//        values.put("NAME","ALi");
//
//        database.insert("STUDENTS",null,values);
//
//        database.close();


        db=new DB(this);
        db.open();
       // int affectedrow=   db.updateStudent("1","ali");
        db.addStudent("sami");
        cursor=  db.getStudent();
        // cursor.moveToFirst();
      //  String pos=cursor.getString(cursor.getColumnIndex("ID"));
        while (cursor.moveToNext()){
            String name= cursor.getString(cursor.getColumnIndex(MySQL.STUDENT_NAME));
          //  adapter.add(name);
         //   Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
        }

//        int affectedrow=db.updateStudent("1","Rami");
//        Toast.makeText(this, affectedrow+" udate is done", Toast.LENGTH_SHORT).show();
//
//        int deleteaffected=db.deleteStudent("1");
//        Toast.makeText(this, deleteaffected+" delete is done", Toast.LENGTH_SHORT).show();
//
//        long id=  db.addStdprepare("sami");
//        Toast.makeText(this, deleteaffected+" add prepare is done", Toast.LENGTH_SHORT).show();

        String[] from = {MySQL.STUDENT_ID,
                MySQL.STUDENT_NAME};

        int[] to = {R.id.tvId,R.id.tvName};
        adapter = new SimpleCursorAdapter(
                this,R.layout.my_list_item,
                db.getStudent(),from,to,
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
//        adapter=new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,db.getStudent(),from,to,SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                                                @Override
                                                public boolean onItemLongClick(AdapterView<?> adapterView, final View view, final int i, long l) {

                                                    //invoking AlertDialog box
                                                    build = new AlertDialog.Builder(MainActivity.this);
                                                    build.setTitle("Update/Delete staff ");
                                                    build.setMessage("Do you want to update/delete the record?(Hit back to cancel)");

                                                    //user select UPDATE
                                                    build.setNegativeButton("UPDATE",
                                                            new DialogInterface.OnClickListener() {

                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    db.open();
                                                                    alert = new AlertDialog.Builder(MainActivity.this);
                                                                    alert.setTitle("Alert Dialog With EditText"); //Set Alert dialog title here
                                                                    alert.setMessage("Enter Your Name Here"); //Message here

                                                                    // Set an EditText view to get user input
                                                                    final EditText input = new EditText(MainActivity.this);
                                                                    alert.setView(input);

                                                                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                        public void onClick(DialogInterface dialog, int whichButton) {
                                                                            cursor.moveToPosition(i);
                                                                            String id = cursor.getString(cursor.getColumnIndex(MySQL.STUDENT_ID));
                                                                            db.updateStudent(id, input.getText().toString());
                                                                            Toast.makeText(MainActivity.this, "updated", Toast.LENGTH_SHORT).show();
                                                                            displayData();
                                                                        } // End of onClick(DialogInterface dialog, int whichButton)
                                                                    }); //End of alert.setPositiveButton
                                                                    alert.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                                                                        public void onClick(DialogInterface dialog, int whichButton) {
                                                                            // Canceled.
                                                                            dialog.cancel();
                                                                        }
                                                                    }); //End of alert.setNegativeButton
                                                                    AlertDialog alertDialog = alert.create();
                                                                    dialog.cancel();
                                                                    alertDialog.show();
                                                                    dialog.cancel();
                                                                }
                                                            });//end UPDATE

                                                    //user select DELETE
                                                    build.setPositiveButton("DELETE",
                                                            new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    db.open();
                                                                    cursor.moveToPosition(i);
                                                                    String id = cursor.getString(cursor.getColumnIndex(MySQL.STUDENT_ID));
                                                                    db.deleteStudent(id);
                                                                    Toast.makeText(MainActivity.this, "deleted", Toast.LENGTH_SHORT).show();
                                                                    adapter.notifyDataSetChanged();
                                                                    displayData();
                                                                    dialog.cancel();
                                                                }
                                                            });//end DELETE
                                                    AlertDialog alert = build.create();
                                                    alert.show();

                                                    return true;
                                                }
                                            });

            //    db.open();
//                boolean ids = cursor.moveToPosition(i);
//                String id=cursor.getString(cursor.getColumnIndex(MySQL.STUDENT_ID));
//                db.deleteStudent(id);
//                Toast.makeText(MainActivity.this, "deleted", Toast.LENGTH_SHORT).show();
//                adapter.notifyDataSetChanged();
//                displayData();
//                return false;

      //  db.close();
    }

    private void displayData() {
    //  db.open();
//        //the SQL command to fetched all records from the table
//        Cursor mCursor = dataBase.rawQuery("SELECT * FROM "
//                + MySQL.STUDENT_TABLE, null);

//        //reset variables
//        stafid.clear();
//        nama.clear();
//        jbt.clear();

        //fetch each record
        if (cursor.moveToFirst()) {
            do {
                    String name= cursor.getString(cursor.getColumnIndex(MySQL.STUDENT_NAME));
                    //  adapter.add(name);
                    //   Toast.makeText(this, name, Toast.LENGTH_SHORT).show();

            } while (cursor.moveToNext());
            //do above till data exhausted
        }

//        //display to screen
//        String from[]={MySQL.STUDENT_NAME};
//        int to[]={android.R.id.text1};
//         adapter =new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,db.getStudent(),from,to,SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        String[] from = {MySQL.STUDENT_ID,
                MySQL.STUDENT_NAME};

        int[] to = {R.id.tvId,R.id.tvName};
        adapter = new SimpleCursorAdapter(
                this,R.layout.my_list_item,
                db.getStudent(),from,to,
                SimpleCursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(adapter);
//        if(cursor != null && !cursor.isClosed()){
//            cursor.close();
//        }
    };//end displayData

}
