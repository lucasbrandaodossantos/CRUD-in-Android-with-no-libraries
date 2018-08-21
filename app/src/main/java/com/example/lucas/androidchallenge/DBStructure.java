package com.example.lucas.androidchallenge;

import android.provider.BaseColumns;

public class DBStructure implements BaseColumns {

    public DBStructure(){

    }
    public static final String TABLE_NAME = "userdata";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE= "phone";
    public static final String COLUMN_EMAIL = "email";
}
