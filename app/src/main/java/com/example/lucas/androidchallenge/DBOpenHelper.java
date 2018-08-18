package com.example.lucas.androidchallenge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBOpenHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "Users";
    private static final String TABLE_NAME = "userdata";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PHONE= "phone";
    private static final String COLUMN_EMAIL = "email";

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query_create = "CREATE TABLE " + TABLE_NAME + " (" +
                 COLUMN_ID + " INTEGER PRIMARY KEY, " +
                 COLUMN_NAME + " TEXT, " +
                 COLUMN_PHONE + " TEXT, " +
                 COLUMN_EMAIL + " TEXT)";

        db.execSQL(query_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /*CRUD BELOW*/

    void insertUser (User u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, u.name);
        values.put(COLUMN_PHONE, u.phone);
        values.put(COLUMN_EMAIL, u.email);

        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    void deleteUser (User u) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String [] {String.valueOf(u.getId())});
        db.close();
    }

    void updateUser (User u) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME, u.name);
        values.put(COLUMN_PHONE, u.phone);
        values.put(COLUMN_EMAIL, u.email);

        db.update(TABLE_NAME,values,COLUMN_ID + " = ?", new String [] {String.valueOf(u.getId())});
        db.close();

    }


    User showUser (int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(TABLE_NAME, new String[]{COLUMN_ID, COLUMN_NAME, COLUMN_PHONE, COLUMN_EMAIL}, COLUMN_ID + " = ?",
                new String [] {String.valueOf(id)}, null,null,null,null);
        if (c != null){
            c.moveToFirst();
        }
        User user = new User(c.getString(0), Integer.parseInt(c.getString(1)), c.getString(2));
        return user;
    }

    public List<User> listAllUsers (){

        List<User> listUsers = new ArrayList<User>();

        String sqlQuery = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.rawQuery(sqlQuery, null);

        if (c.moveToFirst()){
            do{
                User u = new User();
                u.setName(c.getString(0));
                u.setPhone(Integer.parseInt(c.getString(1)));
                u.setEmail(c.getString(2));
                u.setId(Integer.parseInt(c.getString(3)));
                listUsers.add(u);

            } while (c.moveToNext());

        }
        return listAllUsers();
    }


}
