package com.rku.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    EditText edtName, edtSurname, edtMarks;
    TextView txtData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        edtName = findViewById(R.id.edtName);
        edtSurname = findViewById(R.id.edtSurname);
        edtMarks = findViewById(R.id.edtMarks);
        txtData = findViewById(R.id.txtData);
    }

    public void saveRecords(View view) {
        String valName, valSurname, valMarks;
        valName = edtName.getText().toString();
        valSurname = edtSurname.getText().toString();
        valMarks = edtMarks.getText().toString();

        //Validation for inputs

        if(databaseHelper.insertData(valName,valSurname,valMarks)){
            Toast.makeText(this, "Data inserted successfully.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Data insertion failed.", Toast.LENGTH_SHORT).show();
        }
    }


    public void readRecords(View view) {

        Cursor cursor = databaseHelper.selectData();
        String data = "";
        if(cursor != null && cursor.getCount()>0) {
            cursor.moveToFirst();
            do{
                String valName = cursor.getString(1);
                String valSurname = cursor.getString(2);
                int valMarks = cursor.getInt(3);

                data += valName + " " + valSurname + " ("+valMarks+")\n";
            }while(cursor.moveToNext());

            txtData.setText(data);

            //Toast.makeText(this, String.valueOf(count), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "No Record Found", Toast.LENGTH_SHORT).show();
        }
    }
}