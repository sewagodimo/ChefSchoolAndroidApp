package com.example.mosadi.chefschool.userinformation;

/**
 * Created by Mosadi on 2017/09/02.
 */

public class User {
   private  int userID;
    private String username;
    private String password;
    private boolean loggedIn;
    public User(int id, String name, String word){
        this.userID=id;
        this.username=name;
        this.password=word;

    }
    public String getUsername(){return this.username;}
    public String getPassword(){return this.password;}
    public int getUserID(){return this.userID;}
    public void changepassword(String newpassword){
        //put this into the database and make some changes to it
        this.password=newpassword;}
    public boolean userLogin(String username,String password){
        //get the user data from the table
        loggedIn=true;
        return true;}
    public boolean LogOut(){
        //get the user data from the table
        loggedIn=false;
        return true;}
}
