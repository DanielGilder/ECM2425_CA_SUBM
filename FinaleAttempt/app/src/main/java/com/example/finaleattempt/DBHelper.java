package com.example.finaleattempt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import  android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


//This class is used by the app to create, store and retrieve data from a SQLite Database.
public class DBHelper extends SQLiteOpenHelper{
    public DBHelper(Context context){

        super(context, "Recipedata.db", null, 1);

    }

    //Define and create database structure
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Recipedetails(name TEXT primary key, imageURL TEXT, recipeURL TEXT, description TEXT)");
    }



    //This method deletes the table if their already exists a table with the name Recipedetails
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Recipedetails"); //Delete the table if their already exists a table with the name Recipedetails
    }


    //Creates a database object and ContentValues object then stores the data at the required feilds
    public Boolean insertRecipeData(String name, String imageURL, String recipeURL, String description){



        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("imageURL", imageURL);
        contentValues.put("recipeURL", recipeURL);
        contentValues.put("description", description);

        //The following lines check to see if the results are empty or not
        long result = DB.insert("Recipedetails", null, contentValues);
        if(result == -1){
            return false;
        }

        else {
            return true;
        }
    }

    //This method retrieves all the the recipes all the saved recipes from the database which can be accessed in the Cursor object
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Recipedetails",null);
        return cursor;
    }


}
