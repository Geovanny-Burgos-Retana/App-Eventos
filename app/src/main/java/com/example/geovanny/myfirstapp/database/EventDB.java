package com.example.geovanny.myfirstapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.geovanny.myfirstapp.model.Event;
import com.example.geovanny.myfirstapp.model.User;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by geovanny on 18/02/18.
 */

public class EventDB extends SQLiteOpenHelper {
    private static String dbName = "myCalendarEvents";
    private static String tableName = "Event";
    private static String idColumn = "id";
    private static String nameColumn = "name";
    private static String contactPhoneColumn = "phoneContact";
    private static String dayhourColumn = "dayhour";
    private static String priceColumn = "price";
    private static String placeColumn = "place";
    private Context context;

    public EventDB (Context context) {
        super(context,dbName,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        // Creación de la tabla de eventos en la BD
        sqLiteDatabase.execSQL("create table " + tableName + "(" +
                idColumn + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                nameColumn + " TEXT NOT NULL, " +
                contactPhoneColumn + " TEXT NOT NULL, " +
                dayhourColumn + " TEXT NOT NULL, " +
                priceColumn + " TEXT NOT NULL, " +
                placeColumn + " TEXT NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" + tableName);
        onCreate(sqLiteDatabase);
    }

    /**
     * Insertar un nuevo evento en la BD
     * @param event
     * @return
     */
    public boolean create(Event event) {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(nameColumn, event.getName());
            contentValues.put(contactPhoneColumn, event.getContactPhone());
            contentValues.put(dayhourColumn, event.getDayhour());
            contentValues.put(priceColumn, event.getPrice());
            contentValues.put(placeColumn, event.getPlace());
            long rows = sqLiteDatabase.insert(tableName,null, contentValues);
            sqLiteDatabase.close();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Lee todos los eventos de la BD
     * @return
     */
    public ArrayList<Event> readAll() {
        try {
            ArrayList<Event> events = new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select * from " + tableName, null);
            if (cursor.moveToFirst()) {
                do {
                    Event event = new Event();
                    event.setId(cursor.getInt(0));
                    event.setName(cursor.getString(1));
                    event.setContactPhone(cursor.getString(2));
                    event.setDayhour(cursor.getString(3));
                    event.setPrice(cursor.getInt(4));
                    event.setPlace(cursor.getString(5));
                    events.add(event);
                    event.toString();
                } while (cursor.moveToNext());
            }
            sqLiteDatabase.close();
            return events;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Eliminar de la BD un evento por su ID
     * @param id
     * @return
     */
    public boolean delete(int id) {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            int rows = sqLiteDatabase.delete(tableName, idColumn + " = ? ", new String[] {String.valueOf(id)});
            sqLiteDatabase.close();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
