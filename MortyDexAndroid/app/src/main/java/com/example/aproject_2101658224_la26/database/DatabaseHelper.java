package com.example.aproject_2101658224_la26.database;

import static com.example.aproject_2101658224_la26.database.DbConfig.CURRENT_DB_VERSION;
import static com.example.aproject_2101658224_la26.database.DbConfig.DATABASE_NAME;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static volatile DatabaseHelper databaseHelper;
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, CURRENT_DB_VERSION);
    }

    public static DatabaseHelper getInstance(Context context) {
        if (databaseHelper == null) {
            synchronized (DatabaseHelper.class) {
                if (databaseHelper == null)
                    databaseHelper = new DatabaseHelper(context);
            }
        }
        return databaseHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createUserTable = "CREATE TABLE " + DbConfig.UserDb.TABLE_NAME + "("
                + DbConfig.UserDb.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + DbConfig.UserDb.USERNAME + " TEXT NOT NULL UNIQUE, "
                + DbConfig.UserDb.PASSWORD + " TEXT NOT NULL, "
                + DbConfig.UserDb.PHONE_NUMBER + " TEXT NOT NULL UNIQUE, "
                + DbConfig.UserDb.EMAIL + " TEXT NOT NULL UNIQUE, "
                + DbConfig.UserDb.OTP + " TEXT NOT NULL, "
                + DbConfig.UserDb.VERIFIED + " TEXT NOT NULL"
                + ")";
        sqLiteDatabase.execSQL(createUserTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON;");
    }
}
