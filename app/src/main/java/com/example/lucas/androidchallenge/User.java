package com.example.lucas.androidchallenge;

public class User {

    String name,email;
    int phone,id;

    public User(){

    }

    public User(int id, String name,int phone,String email){
    //Used to update and insert
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public User(String name,int phone,String email){
    //Used to delete
        this.name = name;
        this.phone = phone;
        this.email = email;
    }


    /********************************************************************************************/


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
