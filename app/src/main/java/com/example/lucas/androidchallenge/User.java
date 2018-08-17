package com.example.lucas.androidchallenge;

public class User {

    String name,dateofbirth,email;
    int phone;

    public User(){

    }

    public User(String name,int phone,String email,String dateofbirth){
    //Used to update and insert
    }

    public User(String name){
    //Used to delete
    }


    /********************************************************************************************/


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
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
}
