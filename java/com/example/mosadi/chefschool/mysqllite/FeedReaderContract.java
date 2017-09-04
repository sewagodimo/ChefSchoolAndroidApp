package com.example.mosadi.chefschool.mysqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by Mosadi on 2017/09/04.
 */

public class FeedReaderContract r{
    //make the constructor private
 
        //public static final String COLUMN_NAME_= "";

    /* Inner class that defines the table contents */
    public static class EventsEntry implements BaseColumns {
        public static final String TABLE_NAME = "events";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TIME = "date";
        public static final String COLUMN_NAME_VENUE = "venue";
        public static final String COLUMN_NAME_RSVP = "RSVP";
        private static final String SQL_CREATE_EVENTS_ENTRIES =
                "CREATE TABLE " + EventsEntry.TABLE_NAME + " (" +
                        EventsEntry._ID + " INTEGER PRIMARY KEY," +
                        EventsEntry.COLUMN_NAME_TITLE + " TEXT," +
                        EventsEntry.COLUMN_NAME_DATE + " TEXT," +
                        EventsEntry.COLUMN_NAME_TIME + " TEXT," +
                        EventsEntry.COLUMN_NAME_VENUE +" TEXT," +
                        EventsEntry.COLUMN_NAME_RSVP + " TEXT)";

        private static final String SQL_DELETE_EVENTSENTRY =
                "DROP TABLE IF EXISTS " + EventsEntry.TABLE_NAME;
    }
    //a table to store all the user's loginDetails
    public static class LoginDetailsEntry implements BaseColumns{
        public static final String TABLE_NAME = "loginDetails";
        public static final String COLUMN_NAME_ID= "User_ID";
        public static final String COLUMN_NAME_USERNAME= "Username";
        public static final String COLUMN_NAME_PASSWORD= "Password";
        private static final String SQL_CREATE_lOGIN_ENTRIES =
                "CREATE TABLE " + LoginDetailsEntry.TABLE_NAME + " (" +
                        EventsEntry._ID + " INTEGER PRIMARY KEY," +
                        LoginDetailsEntry.COLUMN_NAME_ID + " TEXT," +
                        LoginDetailsEntry.COLUMN_NAME_USERNAME + " TEXT," +
                        LoginDetailsEntry.COLUMN_NAME_PASSWORD + " TEXT)";

        private static final String SQL_DELETE_LOGINDETAILS =
                "DROP TABLE IF EXISTS " + LoginDetailsEntry.TABLE_NAME;
    }


    //A table to store all the profile entries
    public static class ProfileEntry implements BaseColumns {
        public static final String TABLE_NAME = "profiles";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_SURNAME = "surname";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PHONE = "Phone";
        public static final String COLUMN_NAME_CLASS = "class";
        public static final String COLUMN_NAME_WORK_STATUS = "work_status";
        public static final String COLUMN_NAME_DOB= "date-of-birth";
        //CREATE THE TABLE
        private static final String SQL_CREATE_PROFILE_ENTRIES =
           "CREATE TABLE " + ProfileEntry.TABLE_NAME + " (" +
                   ProfileEntry._ID + " INTEGER PRIMARY KEY," +
                   ProfileEntry.COLUMN_NAME_NAME + " TEXT," +
                   ProfileEntry.COLUMN_NAME_SURNAME + " TEXT," +
                   ProfileEntry.COLUMN_NAME_EMAIL + " TEXT," +
                   ProfileEntry.COLUMN_NAME_PHONE + " TEXT," +
                   ProfileEntry.COLUMN_NAME_CLASS + " TEXT," +
                   ProfileEntry.COLUMN_NAME_WORK_STATUS + " TEXT," +
                   ProfileEntry.COLUMN_NAME_DOB + " TEXT)" ;
        private static final String SQL_DELETE_PROFILE =
                "DROP TABLE IF EXISTS " + ProfileEntry.TABLE_NAME;


    }
    //a table to store all the user addresses
    public static class AddressEntry implements BaseColumns{
        public static final String TABLE_NAME = "address";
        public static final String COLUMN_NAME_COUNTRY = "country";
        public static final String COLUMN_NAME_CITY= "City";
        public static final String COLUMN_NAME_PROVINCE= "Province";
        public static final String COLUMN_NAME_SURBURB= "Surburb";
        private static final String SQL_CREATE_ADDRESS_ENTRIES =
                "CREATE TABLE " + AddressEntry.TABLE_NAME + " (" +
                        AddressEntry._ID + " INTEGER PRIMARY KEY," +
                        AddressEntry.COLUMN_NAME_COUNTRY + " TEXT," +
                        AddressEntry.COLUMN_NAME_CITY + " TEXT," +
                        AddressEntry.COLUMN_NAME_PROVINCE+ " TEXT," +
                        AddressEntry.COLUMN_NAME_SURBURB+ " TEXT)" ;
        private static final String SQL_DELETE_ADDRESS =
                "DROP TABLE IF EXISTS " + AddressEntry.TABLE_NAME;
    }

}
