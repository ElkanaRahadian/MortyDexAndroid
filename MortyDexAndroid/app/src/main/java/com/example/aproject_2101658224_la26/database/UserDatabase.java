package com.example.aproject_2101658224_la26.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import androidx.annotation.NonNull;

import com.example.aproject_2101658224_la26.model.User;

public class UserDatabase {
    private Context context;

    public UserDatabase(Context context) {
        this.context = context;
    }

    public long insertUser(@NonNull User user) {
        SQLiteDatabase database = DatabaseHelper.getInstance(context).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbConfig.UserDb.USERNAME, user.getUsername());
        contentValues.put(DbConfig.UserDb.PASSWORD, user.getPassword());
        contentValues.put(DbConfig.UserDb.EMAIL, user.getEmail());
        contentValues.put(DbConfig.UserDb.PHONE_NUMBER, user.getPhoneNumber());
        contentValues.put(DbConfig.UserDb.OTP, "123");
        contentValues.put(DbConfig.UserDb.VERIFIED, "true");

        long id = -1;
        try {
            id = database.insertOrThrow(DbConfig.UserDb.TABLE_NAME, null, contentValues);
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            database.close();
        }
        return id;
    }

    public boolean isUserAvailable(String username, String password) {
        SQLiteDatabase database = DatabaseHelper.getInstance(context).getReadableDatabase();
        String findUserQuery = "SELECT * FROM " + DbConfig.UserDb.TABLE_NAME
                + " WHERE "
                + DbConfig.UserDb.USERNAME + " = '" + username + "'"
                + " AND "
                + DbConfig.UserDb.PASSWORD + " = '" + password + "'";

        try (Cursor cursor = database.rawQuery(findUserQuery, null)) {
            return cursor.getCount() > 0;
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return false;
    }
}
