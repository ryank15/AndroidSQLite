package com.example.kevin.chevysqlite;

/**
 * Created by Kevin on 4/29/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.HashMap;

public class VehicleTable {
    private DBHelper dbHelper;

    public VehicleTable(Context context) {
        dbHelper = new DBHelper(context);
    }

    public int insert(Vehicle vehicle) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Vehicle.TYPE, vehicle.type);
        values.put(Vehicle.PRICE, vehicle.price);
        values.put(Vehicle.YEAR, vehicle.year);
        values.put(Vehicle.MODEL, vehicle.model);

        long vehicle_Id = db.insert(Vehicle.TABLE, null, values);
        db.close();
        return (int) vehicle_Id;
    }

    public void delete(int vehicle_Id) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.delete(Vehicle.TABLE, Vehicle.ID + "= ?", new String[] { String.valueOf(vehicle_Id) });
        db.close();
    }

    public void update(Vehicle vehicle) {

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Vehicle.TYPE, vehicle.type);
        values.put(Vehicle.PRICE, vehicle.price);
        values.put(Vehicle.YEAR, vehicle.year);
        values.put(Vehicle.MODEL, vehicle.model);

        db.update(Vehicle.TABLE, values, Vehicle.ID + "= ?", new String[] { String.valueOf(vehicle.vehicle_Id) });
        db.close();
    }

    public ArrayList<HashMap<String, String>>  getVehicleList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Vehicle.ID + "," +
                Vehicle.MODEL + "," +
                Vehicle.YEAR + "," +
                Vehicle.PRICE + "," +
                Vehicle.TYPE +
                " FROM " + Vehicle.TABLE;

        ArrayList<HashMap<String, String>> vehicleList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> vehicle = new HashMap<String, String>();
                vehicle.put("id", cursor.getString(cursor.getColumnIndex(Vehicle.ID)));
                vehicle.put("model", cursor.getString(cursor.getColumnIndex(Vehicle.MODEL)));
                vehicleList.add(vehicle);

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return vehicleList;

    }

    public Vehicle getVehicleById(int Id){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String selectQuery =  "SELECT  " +
                Vehicle.ID + "," +
                Vehicle.MODEL + "," +
                Vehicle.YEAR + "," +
                Vehicle.PRICE + "," +
                Vehicle.TYPE +
                " FROM " + Vehicle.TABLE
                + " WHERE " +
                Vehicle.ID + "=?";

        int iCount =0;
        Vehicle vehicle = new Vehicle();

        Cursor cursor = db.rawQuery(selectQuery, new String[] { String.valueOf(Id) } );

        if (cursor.moveToFirst()) {
            do {
                vehicle.vehicle_Id =cursor.getInt(cursor.getColumnIndex(Vehicle.ID));
                vehicle.model =cursor.getString(cursor.getColumnIndex(Vehicle.MODEL));
                vehicle.year  =cursor.getInt(cursor.getColumnIndex(Vehicle.YEAR));
                vehicle.price =cursor.getInt(cursor.getColumnIndex(Vehicle.PRICE));
                vehicle.type  =cursor.getString(cursor.getColumnIndex(Vehicle.TYPE));

            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return vehicle;
    }

}
