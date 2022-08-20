package com.example.sqliteapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name, rollNumber, age;
    Button insert, update, delete, view;
    DBHelper DB;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        rollNumber = findViewById(R.id.rollNumber);
        age = findViewById(R.id.age);
        
        insert = findViewById(R.id.btnInsert);
        update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);

        DB = new DBHelper(this);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = name.getText().toString();
                String contactText = rollNumber.getText().toString();
                String dobText = age.getText().toString();

                Boolean checkInsertData = DB.insertUserData(nameText, contactText, dobText);

                if (checkInsertData == true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });


        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = name.getText().toString();
                String contactText = rollNumber.getText().toString();
                String dobText = age.getText().toString();

                Boolean checkUpdateData = DB.updateUserData(nameText, contactText, dobText);

                if (checkUpdateData == true)
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Not Updated", Toast.LENGTH_SHORT).show();
            }
        });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText = name.getText().toString();

                Boolean checkDeleteData = DB.deleteData(nameText);

                if (checkDeleteData == true)
                    Toast.makeText(MainActivity.this, "Entry Deleted!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Entry Not Deleted!", Toast.LENGTH_SHORT).show();
            }
        });


        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = DB.getData();
                
                if (res.getCount() == 0) {
                    Toast.makeText(MainActivity.this, "No Entry Exist!", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Name: " + res.getString(0) + "\n");
                    buffer.append("Contact: " + res.getString(1) + "\n");
                    buffer.append("Date of Birth: " + res.getString(2) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Student Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}