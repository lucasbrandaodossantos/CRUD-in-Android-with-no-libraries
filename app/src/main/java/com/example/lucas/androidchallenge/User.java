package com.example.lucas.androidchallenge;

public class User {

    String name, phone, email;
    int id;

    public User(){

    }

    public User(int _id, String _name, String _phone, String _email){
            //Used to update
            this.id = _id;
            this.name = _name;
            this.phone = _phone;
            this.email = _email;
    }

    public User(String name, String phone, String email){
    //Used to insert
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
