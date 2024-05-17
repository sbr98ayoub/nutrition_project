package com.example.nutrition_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "register.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "food_table";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PROTEIN = "protein";
    private static final String COLUMN_CARBS = "carbs";
    private static final String COLUMN_CALORIES = "calories";

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 2);
    }


    @Override

    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "create table utilisateur(username TEXT PRIMARY KEY , email TEXT, password TEXT  )";
        db.execSQL(createTableQuery);

        String createTableQuery1 = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_PROTEIN + " REAL, " +
                COLUMN_CARBS + " REAL, " +
                COLUMN_CALORIES + " REAL)";
        db.execSQL(createTableQuery1);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS utilisateur");
        db.execSQL("DROP TABLE IF EXISTS food_table");
        onCreate(db);
    }

    public boolean insertData(String name, String phone, String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = db.insert("Users", null, contentValues);
        return result != -1;
    }

    public boolean checkUsername(String username) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from Users where name=?", new String[]{username});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkuserlogin(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Users where name=? and password=?", new String[]{username, password});

        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getNameForUser(String username) {
        if (username == null) {
            return null;
        }

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"name"};
        String selection = "name=?";
        String[] selectionArgs = {username};
        Cursor cursor = null;
        String name = null;
        try {
            cursor = db.query("Users", columns, selection, selectionArgs, null, null, null);
            if (cursor.moveToFirst()) {
                name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return name;
    }

    public String getEmailForUser(String username) {
        if (username == null) {
            return null;
        }

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"email"};
        String selection = "name=?";
        String[] selectionArgs = {username};
        Cursor cursor = null;
        String email = null;
        try {
            cursor = db.query("Users", columns, selection, selectionArgs, null, null, null);
            if (cursor.moveToFirst()) {
                email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return email;
    }

    public String getPhoneForUser(String username) {
        if (username == null) {
            return null;
        }

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"phone"};
        String selection = "name=?";
        String[] selectionArgs = {username};
        Cursor cursor = null;
        String phone = null;
        try {
            cursor = db.query("Users", columns, selection, selectionArgs, null, null, null);
            if (cursor.moveToFirst()) {
                phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return phone;
    }

    public String getPasswordForUser(String username) {
        if (username == null) {
            return null;
        }

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"password"};
        String selection = "name=?";
        String[] selectionArgs = {username};
        Cursor cursor = null;
        String phone = null;
        try {
            cursor = db.query("Users", columns, selection, selectionArgs, null, null, null);
            if (cursor.moveToFirst()) {
                phone = cursor.getString(cursor.getColumnIndexOrThrow("password"));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return phone;
    }

    public boolean updateUser(String username, String newName, String newPhone, String newEmail, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", newName);
        contentValues.put("phone", newPhone);
        contentValues.put("email", newEmail);
        contentValues.put("password", newPassword);
        int rowsAffected = db.update("Users", contentValues, "name=?", new String[]{username});
        return rowsAffected > 0;
    }

    public boolean insertData(String name, double protein, double carbs, double calories) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Check if the food item already exists
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + "=?", new String[]{name});
        if (cursor.getCount() > 0) {
            cursor.close();
            return false; // Data already exists, so return false
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_PROTEIN, protein);
        contentValues.put(COLUMN_CARBS, carbs);
        contentValues.put(COLUMN_CALORIES, calories);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public boolean insertDataUser(String username, String email, String password) {
        SQLiteDatabase myDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = myDB.insert("utilisateur", null, contentValues);
        if (result == -1) return false;
        else return true;

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

    public ArrayList<FoodItem> getAllFoodItems() {
        ArrayList<FoodItem> foodItems = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME));
                double protein = cursor.getDouble(cursor.getColumnIndex(COLUMN_PROTEIN));
                double carbs = cursor.getDouble(cursor.getColumnIndex(COLUMN_CARBS));
                double calories = cursor.getDouble(cursor.getColumnIndex(COLUMN_CALORIES));
                FoodItem foodItem = new FoodItem(name, protein, carbs, calories);
                foodItems.add(foodItem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return foodItems;
    }
}

