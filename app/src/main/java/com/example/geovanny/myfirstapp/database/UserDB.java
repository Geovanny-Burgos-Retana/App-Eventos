package com.example.geovanny.myfirstapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.geovanny.myfirstapp.model.User;

import java.util.ArrayList;

/**
 * Created by geovanny on 18/02/18.
 */

public class UserDB extends SQLiteOpenHelper {
    private static String dbName = "myCalendarEvents";
    private static String tableName = "User";
    private static String idColumn = "id";
    private static String nameColumn = "name";
    private static String usernameColumn = "username";
    private static String passwordColumn = "password";
    private Context context;

    public UserDB (Context context) {
        super(context,dbName,null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Creacion de la tabla para eventos en la BD
        sqLiteDatabase.execSQL("create table " + tableName + "(" +
                idColumn + " INTEGER NOT NULL PRIMARY KEY, " +
                nameColumn + " TEXT NOT NULL, " +
                usernameColumn + " TEXT NOT NULL, " +
                passwordColumn + " TEXT NOT NULL )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + tableName);
        onCreate(sqLiteDatabase);
    }

    /**
     * Insertar un usuario a la base de datos
     * @param user
     * @return
     */
    public boolean create(User user) {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(idColumn,1);
            contentValues.put(nameColumn, user.getName());
            contentValues.put(usernameColumn, user.getUsername());
            contentValues.put(passwordColumn, user.getPassword());
            long rows = sqLiteDatabase.insert(tableName,null, contentValues);
            sqLiteDatabase.close();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Consultar los datos del único usuario
     * @return
     */
    public User findOne() {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("select id, name, username, password from " + tableName + " where " + idColumn + " = ?",new String[] {"1"});
            if (cursor.moveToFirst() && cursor.getCount() == 1) {
                User user = new User();
                user.setId(cursor.getInt(0));
                user.setName(cursor.getString(1));
                user.setUsername(cursor.getString(2));
                user.setPassword(cursor.getString(3));
                sqLiteDatabase.close();
                return user;
            }
            sqLiteDatabase.close();
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Actualizar en la BD las credenciales del usuario
     * @param user
     * @return
     */
    public boolean update(User user) {
        try {
            SQLiteDatabase sqLiteDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(usernameColumn, user.getUsername());
            contentValues.put(passwordColumn, user.getPassword());
            int rows = sqLiteDatabase.update(tableName, contentValues, idColumn + " = ?", new String[] {String.valueOf(user.getId())});
            sqLiteDatabase.close();
            return rows > 0;
        } catch (Exception e) {
            return false;
        }
    }
}
