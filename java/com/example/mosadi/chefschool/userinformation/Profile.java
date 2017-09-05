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

    private String image;
    //Create a user
    public Profile(String id, String name,String surname, String contact, int classnr, String dob){//when a user register
        this.setUserID(id);
        this.image="";//There is no image so far
        this.setName(name);
        this.setSurname(surname);
        this.setClass_number(classnr);
        this.setEmail("");
        this.setPhone("");
        this.setContact(contact);
        this.setDob(dob);
        this.setCountry("South Africa");
        this.setProvince("Western Cape");
        this.setCity("Cape Town");
        this.setSurburb("");
        if(contact.contains("@")){
            this.setEmail(contact);
        }
        else{
            this.setPhone(contact);}
    }//end of constructor
    public void edit_profile(String id, String name,String surname, String contact, int classnr, String dob){//when a user register
        this.setUserID(id);
        this.image="";//There is no image so far
        this.setName(name);
        this.setSurname(surname);
        this.setClass_number(classnr);
        this.setEmail("");
        this.setPhone("");
        this.setContact(contact);
        this.setDob(dob);
        this.setCountry("South Africa");
        this.setProvince("Western Cape");
        this.setCity("Cape Town");
        this.setSurburb("");
        if(contact.contains("@")){
            this.setEmail(contact);
        }
        else{
            this.setPhone(contact);}
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


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getClass_number() {
        return class_number;
    }

    public void setClass_number(int class_number) {
        this.class_number = class_number;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSurburb() {
        return surburb;
    }

    public void setSurburb(String surburb) {
        this.surburb = surburb;
    }
}
