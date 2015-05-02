package com.example.kevin.chevysqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kevin on 4/29/2015.
 */

 public class DBHelper  extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 4;
        private static final String DATABASE_NAME = "chevy.db";

        public DBHelper(Context context ) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_TABLE_VEHICLE = "CREATE TABLE " + Vehicle.TABLE  + "("
                    + Vehicle.ID  + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                    + Vehicle.MODEL + " TEXT, "
                    + Vehicle.YEAR + " INTEGER, "
                    + Vehicle.PRICE + " INTEGER, "
                    + Vehicle.TYPE + " TEXT )";

            db.execSQL(CREATE_TABLE_VEHICLE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed, all data will be gone!!!
            db.execSQL("DROP TABLE IF EXISTS " + Vehicle.TABLE);

            // Create tables again
            onCreate(db);

        }

 }
