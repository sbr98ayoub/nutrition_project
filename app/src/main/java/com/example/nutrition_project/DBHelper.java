package com.example.nutrition_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String dbName= "register.db";

    public DBHelper(@Nullable Context context) {
        super(context, dbName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table utilisateur(username TEXT PRIMARY KEY , email TEXT, password TEXT  )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists users");

    }

    public boolean insertData(String username, String email , String password){
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = myDB.insert("utilisateur",null,contentValues);
        if(result==-1) return  false;
        else return true ;

    }

    public boolean checkCredentials(String username, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("SELECT * FROM utilisateur WHERE username = ? AND password = ?", new String[]{username, password});
        if (cursor.getCount() > 0) {
            // Si un enregistrement correspondant est trouvé, les identifiants sont valides
            cursor.close(); // Fermer le curseur après utilisation
            return true;
        } else {
            // Aucun enregistrement correspondant trouvé, les identifiants ne sont pas valides
            cursor.close(); // Fermer le curseur après utilisation
            return false;
        }
    }










}
