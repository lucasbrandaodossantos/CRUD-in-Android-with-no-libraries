package com.example.lucas.androidchallenge.Database;

public class SQLcommands {

    public static String getCreateTable(){

        StringBuilder createtable = new StringBuilder();

        createtable.append ("CREATE TABLE IF NOT EXISTS PERSON(");
        createtable.append ("        NAME VARCHAR (250) NOT NULL,");
        createtable.append ("        AGE  INTEGER (2) NOT NULL);");

        return createtable.toString();
    }

    public static String getInsertInto(){

        StringBuilder insertinto = new StringBuilder();

        insertinto.append ("INSERT INTO PERSON(NAME, AGE)");
        insertinto.append ("VALUES");

        return insertinto.toString();
    }

    public static String getUpdate(){
        StringBuilder update = new StringBuilder();

        update.append ("UPDATE PERSON(");
        update.append ("SET NAME");
        update.append ("        AGE  INTEGER (2) NOT NULL);");

        return update.toString();
    }

    public static String getDelete(){
        StringBuilder delete = new StringBuilder();

        delete.append ("CREATE TABLE IF NOT EXISTS PERSON(");
        delete.append ("        NAME VARCHAR (250) NOT NULL,");
        delete.append ("        AGE  INTEGER (2) NOT NULL);");

        return delete.toString();
    }

    public static String getSelect(){
        StringBuilder select = new StringBuilder();

        select.append ("CREATE TABLE IF NOT EXISTS PERSON(");
        select.append ("        NAME VARCHAR (250) NOT NULL,");
        select.append ("        AGE  INTEGER (2) NOT NULL);");

        return select.toString();
    }
}
