package com.example.aproject_2101658224_la26.database;

public class DbConfig {
    public static final String DATABASE_NAME = "morty.db";
    public static int CURRENT_DB_VERSION = 1;

    public static class UserDb {
        public static String TABLE_NAME = "users";
        public static String USER_ID = "userId";
        public static String USERNAME = "username";
        public static String PASSWORD = "password";
        public static String PHONE_NUMBER = "phoneNumber";
        public static String EMAIL = "email";
        public static String OTP = "otp";
        public static String VERIFIED = "verified";
    }
}
