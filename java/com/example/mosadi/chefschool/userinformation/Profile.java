package com.example.mosadi.chefschool.userinformation;

/**
 * Created by Mosadi on 2017/09/02.
 */

public class Profile {
    private String userID; //from the user
    private String name;
    private String surname;
    private String phone;
    private String contact;
    private String email;
    private int class_number;
    private String dob;
    private String country;
    private String province;
    private String city;
    private String surburb;
    private boolean activated;
    private String image;
    //Create a user
    public Profile(String id, String name,String surname, String contact, int classnr, String dob){//when a user register
        this.userID=id;
        this.image="";//There is no image so far
        this.name=name;
        this.surname=surname;
        this.class_number=classnr;
        this.email="";
        this.phone="";
        this.contact=contact;
        this.dob=dob;
        this.country="South Africa";
        this.province="Western Cape";
        this.city="Cape Town";
        this.surburb="";
        if(contact.contains("@")){
            this.email=contact;
        }
        else{this.phone=contact;}
    }//end of constructor
    public void edit_profile(){
        //get values from different parts and edit them
    }
    public void cancelProfileChanges(){
        //when the user does not want the changes they have made
    }
    public void changeAddress(){

    }
    public void cancelAddressChanges(){

    }
}
