package com.rku.demodatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE = "college";
    public static final String TABLE = "student";
    public static final String COL_1 = "id";
    public static final String COL_2 = "name";
    public static final String COL_3 = "surname";
    public static final String COL_4 = "marks";


    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists " + TABLE + "(id integer " +
                "primary key autoincrement, name text, surname text, marks integer)";

//        String sql = "create table if not exists " + TABLE + "(id integer" +
//                "primary key autoincrement, name text, surname text, marks integer)";

//        Log.i("sql", sql);
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table " + TABLE);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String surname, String marks){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_3,surname);
        values.put(COL_4,marks);
        values.put(COL_2,name);
        // insert into TABLE (name,surname,marks) values ("xyz","ABC",75);
        long result =  db.insert(TABLE,null,values);
        return (result == -1)?false:true;
    }

    public Cursor selectData() {
        SQLiteDatabase db = getWritableDatabase();
        //select * from Table

        return db.query(TABLE,
                null,
                null,
                null,
                null,
                null,
                null);



//        db.delete("student","branch=? and city=?",new String[]{"BCA","Junagadh"});
//
//        ContentValues values = new ContentValues();
//        values.put("name","Rajesh");
//        values.put("branch","BCA");
//        db.insert("student",null,values);
//











//        return  null;
    }
}
