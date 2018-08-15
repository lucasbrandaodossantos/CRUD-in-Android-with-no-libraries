package com.example.lucas.androidchallenge.Database;

public class SQLcommands {

    public static String getCreateTable(){

        StringBuilder createtable = new StringBuilder();

        createtable.append ("CREATE TABLE IF NOT EXISTS PERSON(");
        createtable.append ("        NAME VARCHAR (250) NOT NULL,");
        createtable.append ("        AGE  INTEGER (2) NOT NULL);");

        return createtable.toString();
    }

}
