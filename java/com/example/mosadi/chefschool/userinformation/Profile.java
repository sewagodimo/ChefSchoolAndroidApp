package com.example.mosadi.chefschool.userinformation;

/**
 * Created by Mosadi on 2017/09/02.
 */

public class Profile {
    private String userID; //from the user
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String class_number;
    private String dob;
    private String work_status;

    private String country;
    private String province;
    private String city;
    private String surburb;
    private String image;
    //Create a user

    public Profile(String id, String name,String surname,String image,String email,String phone, String classnr, String work_status, String dob,String country, String province,String city,String surburb){//when a user register
        this.setUserID(id);
        this.setImage(image);//There is no image so far
        this.setWork_status(work_status);
        this.setName(name);
        this.setSurname(surname);
        this.setClass_number(classnr);
        this.setEmail(email);
        this.setPhone(phone);
       // this.setContact(contact);
        this.setDob(dob);
        this.setCountry(country);
        this.setProvince(province);
        this.setCity(city);
        this.setSurburb(surburb);
    }//end of constructor
    public Profile(String id,String name, String surname,String classnr,String contact){
        this.setUserID(id);
        this.setImage("");//There is no image so far
        this.setWork_status("");
        this.setName(name);
        this.setSurname(surname);
        this.setClass_number(classnr);
        this.setEmail("anemail");
        this.setPhone("");
        if(contact.contains("@")){
            this.setEmail(contact);
        }
        else{
            this.setPhone(contact);
        }

        // this.setContact(contact);
        this.setDob("");
        this.setCountry("");
        this.setProvince("");
        this.setCity("");
        this.setSurburb("");

    }

    public Profile(){
        this.setUserID("");
        this.setImage("");//There is no image so far
        this.setWork_status("");
        this.setName("");
        this.setSurname("");
        this.setClass_number("");
        this.setEmail("");
        this.setPhone("");
        // this.setContact(contact);
        this.setDob("");
        this.setCountry("");
        this.setProvince("");
        this.setCity("");
        this.setSurburb("");
    }

    public void edit_profile( String name,String surname,String image,String email, String phone,StudentAccountContract db){//when a user register
        //this.setUserID(id);
        this.setImage(image);//There is no image so far
        this.setName(name);
        this.setSurname(surname);
       // this.setClass_number(classnr);
        this.setEmail(email);
        this.setPhone(phone);
       // this.setContact(contact);
       // this.setDob(dob);
        //then change sqlite
        db.updateProfile(this);


    }//end of constructor
public void edit_address(String country, String prov, String city, String surburb,StudentAccountContract db){
this.setCountry(country);
    this.setProvince(prov);
    this.setCity(city);
    this.setSurburb(surburb);
    //update sqlite
    db.updateAddress(this);
}
public Profile userProfile(){
    return this;
}

    public String profileString(){
        String log = "Id: "+this.getUserID()+" ,Name: " + this.getName() +" ,Surame: " + this.getSurname()
                +" ,Class: " + this.getClass_number() + " ,Phone: " + this.getPhone() + " ,Email: "
                + this.getEmail()+"dob: "+this.getDob();
        // Writing Contacts to log
        return log;
    }
    public String addressString(){
        String log = "Id: "+this.getUserID()+" ,Country: " + this.getCountry() +" ,Province: " + this.getProvince()
                +" ,City: " + this.getCity() + " ,Surburb: "+this.getSurburb();
        // Writing Contacts to log
        return log;
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



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClass_number() {
        return class_number;
    }

    public void setClass_number(String class_number) {
        this.class_number = class_number;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dobstr) {
        //so you get year month and date
            this.dob=dobstr;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getWork_status() {
        return work_status;
    }

    public void setWork_status(String work_status) {
        this.work_status = work_status;
    }
}
