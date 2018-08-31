package com.example.lucas.androidchallenge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.lucas.androidchallenge.DBStructure.*;

import java.util.ArrayList;
import java.util.List;

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 11;
    private static final String DB_NAME = "Users";

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_create = "CREATE TABLE " + DBStructure.TABLE_NAME + " (" +
                 DBStructure._ID + " INTEGER PRIMARY KEY, " +
                 DBStructure.COLUMN_NAME + " TEXT, " +
                 DBStructure.COLUMN_PHONE + " TEXT, " +
                 DBStructure.COLUMN_EMAIL + " TEXT" +
                " )";

        db.execSQL(query_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " +DBStructure.TABLE_NAME);
        this.onCreate(db);
    }

    /*CRUD BELOW*/

    void insertUser (User u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBStructure.COLUMN_NAME, u.name);
        values.put(DBStructure.COLUMN_PHONE, u.phone);
        values.put(DBStructure.COLUMN_EMAIL, u.email);

        db.insert(DBStructure.TABLE_NAME, null, values);
        db.close();

    }

    User selectUser (int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query_select = "SELECT * FROM " +DBStructure.TABLE_NAME + " WHERE " +DBStructure._ID + " = " + id;
//        Cursor c = db.rawQuery(query_select, new String[]{DBStructure._ID});

        Cursor c = db.query(DBStructure.TABLE_NAME, new String[]{DBStructure._ID, DBStructure.COLUMN_NAME, DBStructure.COLUMN_PHONE, DBStructure.COLUMN_EMAIL}, DBStructure._ID + " = ?", new String[]{String.valueOf(id)}, null, null, null, null);

        if (c != null){
            c.moveToFirst();
        }
        User user = new User(Integer.parseInt(c.getString(0)), c.getString(1),c.getString(2),c.getString(3));
        return user;
    }


    void updateUser (User u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(DBStructure.COLUMN_NAME, u.name);
        values.put(DBStructure.COLUMN_PHONE, u.phone);
        values.put(DBStructure.COLUMN_EMAIL, u.email);

        db.update(DBStructure.TABLE_NAME,values,DBStructure._ID + " = ?", new String [] {String.valueOf(u.getId())});
        db.close();

    }

    void deleteUser (User u) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DBStructure.TABLE_NAME, DBStructure._ID + " = ?", new String [] {String.valueOf(u.getId())});
        db.close();
    }

//*****Listing all users registered******//


    public List<User> listAllUsers () {

        List<User> listUsers = new ArrayList<User>();

        String sqlQuery = "SELECT * FROM " + DBStructure.TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(sqlQuery, null);

        if (c.moveToFirst()){
            do{
                User u = new User();
                u.setId(Integer.parseInt(c.getString(0)));
                u.setName(c.getString(1));
                u.setPhone(c.getString(2));
                u.setEmail(c.getString(3));
                listUsers.add(u);

            } while (c.moveToNext());

        }
        return listUsers;
    }
}