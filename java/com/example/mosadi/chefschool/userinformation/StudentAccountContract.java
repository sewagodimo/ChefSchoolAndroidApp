package com.example.mosadi.chefschool.userinformation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mosadi on 2017/09/04.
 */

public class StudentAccountContract  extends SQLiteOpenHelper {
    //make the constructor private


    //public static final String COLUMN_NAME_= "";

    /* Inner class that defines the table contents */
    public static class EventsEntry implements BaseColumns {
        public static final String TABLE_NAME = "events";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_DATE = "date";
        public static final String COLUMN_NAME_TIME = "time";
        public static final String COLUMN_NAME_VENUE = "venue";
        public static final String COLUMN_NAME_RSVP = "RSVP";
    }
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

    //a table to store all the user's loginDetails
    public static class LoginDetailsEntry implements BaseColumns {
        public static final String TABLE_NAME = "loginDetails";
        public static final String COLUMN_NAME_ID = "User_ID";
        public static final String COLUMN_NAME_USERNAME = "Username";
        public static final String COLUMN_NAME_PASSWORD = "Password";
    }
        private static final String SQL_CREATE_lOGIN_ENTRIES =
                "CREATE TABLE " + LoginDetailsEntry.TABLE_NAME + " (" +
                        EventsEntry._ID + " INTEGER PRIMARY KEY," +
                        LoginDetailsEntry.COLUMN_NAME_ID + " TEXT," +
                        LoginDetailsEntry.COLUMN_NAME_USERNAME + " TEXT," +
                        LoginDetailsEntry.COLUMN_NAME_PASSWORD + " TEXT)";

        private static final String SQL_DELETE_LOGINDETAILS =
                "DROP TABLE IF EXISTS " + LoginDetailsEntry.TABLE_NAME;



    //A table to store all the profile entries
    public static class ProfileEntry implements BaseColumns {
        public static final String TABLE_NAME = "profile_entries";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_SURNAME = "surname";
        public static final String COLUMN_NAME_PICTURE = "picture";
        public static final String COLUMN_NAME_EMAIL = "email";
        public static final String COLUMN_NAME_PHONE = "Phone";
        public static final String COLUMN_NAME_CLASS = "class";
        public static final String COLUMN_NAME_WORK_STATUS = "work_status";
        public static final String COLUMN_NAME_DOB = "date_of_birth";
        public static final String COLUMN_NAME_COUNTRY = "country";
        public static final String COLUMN_NAME_CITY = "City";
        public static final String COLUMN_NAME_PROVINCE = "Province";
        public static final String COLUMN_NAME_SURBURB = "Surburb";
    }
        private static final String SQL_CREATE_PROFILE_ENTRIES =
           "CREATE TABLE " + ProfileEntry.TABLE_NAME + " (" +
                   ProfileEntry._ID + " INTEGER PRIMARY KEY," +
                   ProfileEntry.COLUMN_NAME_NAME + " TEXT," +
                   ProfileEntry.COLUMN_NAME_SURNAME + " TEXT," +
                   ProfileEntry.COLUMN_NAME_PICTURE + " TEXT," +
                   ProfileEntry.COLUMN_NAME_EMAIL + " TEXT," +
                   ProfileEntry.COLUMN_NAME_PHONE + " TEXT," +
                   ProfileEntry.COLUMN_NAME_CLASS + " TEXT," +
                   ProfileEntry.COLUMN_NAME_WORK_STATUS + " TEXT," +
                   ProfileEntry.COLUMN_NAME_DOB + " TEXT," +
                   ProfileEntry.COLUMN_NAME_COUNTRY + " TEXT," +
                   ProfileEntry.COLUMN_NAME_CITY + " TEXT," +
                   ProfileEntry.COLUMN_NAME_PROVINCE+ " TEXT," +
                   ProfileEntry.COLUMN_NAME_SURBURB+ " TEXT)" ;
        private static final String SQL_DELETE_PROFILE =
                "DROP TABLE IF EXISTS " + ProfileEntry.TABLE_NAME;



    //a table to store all the user addresses


    //the sdqlite helper class

        public static final int DATABASE_VERSION = 2;
        public static final String DATABASE_NAME = "ictstudents.db";

        public StudentAccountContract(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_EVENTS_ENTRIES);
            db.execSQL(SQL_CREATE_lOGIN_ENTRIES);
           db.execSQL(SQL_CREATE_PROFILE_ENTRIES);
           // db.execSQL(SQL_CREATE_ADDRESS_ENTRIES);
        }
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // Drop older table if existed
            db.execSQL("DROP TABLE IF EXISTS " + ProfileEntry.TABLE_NAME);
           // db.execSQL("DROP TABLE IF EXISTS " + AddressEntry.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + EventsEntry.TABLE_NAME);
            db.execSQL("DROP TABLE IF EXISTS " + LoginDetailsEntry.TABLE_NAME);

            // Create tables again
            onCreate(db);
        }
        @Override
        public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            onUpgrade(db, oldVersion, newVersion);
        }
        // Adding new Profile
        public void addProfile(Profile profile) {
            SQLiteDatabase db = this.getWritableDatabase();
            //Get a new profile
            ContentValues values = new ContentValues();
            values.put(ProfileEntry.COLUMN_NAME_NAME, profile.getName()); // Contact Name
            values.put(ProfileEntry.COLUMN_NAME_SURNAME, profile.getSurname()); // Contact Phone Number
            values.put(ProfileEntry.COLUMN_NAME_EMAIL, profile.getEmail()); // Contact EMAIL
            values.put(ProfileEntry.COLUMN_NAME_PHONE, profile.getPhone()); // Contact EMAIL
            values.put(ProfileEntry.COLUMN_NAME_PICTURE, profile.getImage());// Contact the profile picture
            values.put(ProfileEntry.COLUMN_NAME_CLASS, profile.getClass_number()); // get the user's class
            values.put(ProfileEntry.COLUMN_NAME_EMAIL, profile.getEmail()); // Contact EMAIL
            values.put(ProfileEntry.COLUMN_NAME_DOB, profile.getDob()); // Contact DATE OF BIRTH
            values.put(ProfileEntry.COLUMN_NAME_WORK_STATUS, profile.getWork_status()); // Contact EMAIL
            values.put(ProfileEntry.COLUMN_NAME_COUNTRY, profile.getCountry()); //The user's country
            values.put(ProfileEntry.COLUMN_NAME_PROVINCE, profile.getProvince()); //The user's country
            values.put(ProfileEntry.COLUMN_NAME_CITY, profile.getCity()); //The user's country0
            values.put(ProfileEntry.COLUMN_NAME_SURBURB, profile.getSurburb()); //The user's country0
          //  db.insert(AddressEntry.TABLE_NAME,null,address);
            db.insert(ProfileEntry.TABLE_NAME, null, values);
            db.close(); // Closing database connection
        }

        // Getting single contact
        public Profile getContact(String name) {
            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(
                    ProfileEntry.TABLE_NAME, new String[] {
                            ProfileEntry._ID,
                            ProfileEntry.COLUMN_NAME_NAME,
                            ProfileEntry.COLUMN_NAME_SURNAME,
                            ProfileEntry.COLUMN_NAME_PICTURE,
                            ProfileEntry.COLUMN_NAME_EMAIL,
                            ProfileEntry.COLUMN_NAME_PHONE,
                            ProfileEntry.COLUMN_NAME_CLASS,
                            ProfileEntry.COLUMN_NAME_WORK_STATUS,
                            ProfileEntry.COLUMN_NAME_DOB,
                            ProfileEntry.COLUMN_NAME_COUNTRY,
                            ProfileEntry.COLUMN_NAME_PROVINCE,
                            ProfileEntry.COLUMN_NAME_CITY,
                            ProfileEntry.COLUMN_NAME_SURBURB

                    }, ProfileEntry.COLUMN_NAME_NAME + "=?", new String[] { String.valueOf(name) }
                    , null, null, null, null);
            if (cursor != null)
                cursor.moveToFirst();

            Profile profile = new Profile(cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getString(6),
                    cursor.getString(7),
                    cursor.getString(8),
                    cursor.getString(9),
                    cursor.getString(10),
                    cursor.getString(11),
                    cursor.getString(12) );
            // return contact
            return profile;
        }

        // Getting All Contacts
        public List<Profile> getAllProfile() {
            List<Profile> contactList = new ArrayList<>();
            String selectQuery = "SELECT  * FROM " + ProfileEntry.TABLE_NAME;
            //String selectQueryAddress = "SELECT  * FROM " + AddressEntry.TABLE_NAME;

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);
            //Cursor addresCursor = db.rawQuery(selectQueryAddress,null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    //String id,String name, String surname,String classnr,String contact
                    Profile profile = new Profile();
                    profile.setUserID(cursor.getString(0));
                    profile.setName(cursor.getString(1));
                    profile.setSurname(cursor.getString(2));
                    profile.setImage(cursor.getString(3));
                    profile.setEmail(cursor.getString(4));
                    profile.setPhone(cursor.getString(5));
                    profile.setClass_number(cursor.getString(6));
                    profile.setWork_status(cursor.getString(7));
                    profile.setDob(cursor.getString(8));
                   // profile.setUserID(cursor.getString(9));
                    profile.setCountry(cursor.getString(9));
                    profile.setProvince(cursor.getString(10));
                    profile.setCity(cursor.getString(11));
                    profile.setSurburb(cursor.getString(12));

                    // Adding contact to list
                    contactList.add(profile);
                }
                while (cursor.moveToNext());
            }

            // return contact list
            return contactList;
        }

        // Getting contacts Count
       // public int getContactsCount() {}
        // Updating single contact
        public int updateProfile(Profile profile) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(ProfileEntry.COLUMN_NAME_NAME, profile.getName());
            values.put(ProfileEntry.COLUMN_NAME_SURNAME, profile.getSurname());
            values.put(ProfileEntry.COLUMN_NAME_PICTURE, profile.getImage());
            values.put(ProfileEntry.COLUMN_NAME_EMAIL, profile.getEmail());
            values.put(ProfileEntry.COLUMN_NAME_PHONE, profile.getPhone());
           // values.put(ProfileEntry.COLUMN_NAME_WORK_STATUS, profile.getWork_status());
            values.put(ProfileEntry.COLUMN_NAME_DOB, profile.getDob());

            // updating row
            return db.update(ProfileEntry.TABLE_NAME, values, ProfileEntry._ID + " = ?",
                    new String[] { String.valueOf(profile.getUserID()) });
        }

        public int updateAddress(Profile profile) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(ProfileEntry.COLUMN_NAME_COUNTRY, profile.getCountry());
            values.put(ProfileEntry.COLUMN_NAME_CITY, profile.getCity());
            values.put(ProfileEntry.COLUMN_NAME_PROVINCE, profile.getProvince());
            values.put(ProfileEntry.COLUMN_NAME_SURBURB, profile.getSurburb());

            // updating row
            return db.update(ProfileEntry.TABLE_NAME, values, ProfileEntry._ID + " = ?",
                    new String[] { String.valueOf(profile.getUserID()) });
        }
    public int updateWork_status(Profile profile) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProfileEntry.COLUMN_NAME_WORK_STATUS, profile.getCountry());
        // updating row
        return db.update(ProfileEntry.TABLE_NAME, values, ProfileEntry._ID + " = ?",
                new String[] { String.valueOf(profile.getUserID()) });
    }

        // Deleting single contact
        public void deleteProfile(Profile profile) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(ProfileEntry.TABLE_NAME,ProfileEntry._ID + " = ?",
                    new String[] { String.valueOf(profile.getUserID()) });
            db.close();
        }
    }
    //ACCESS THE DB
   // StudentsAccountDbHelper mDbHelper = new StudentsAccountDbHelper(getContext());
// Gets the data repository in write mode
   // SQLiteDatabase db = mDbHelper.getWritableDatabase();

    // Create a new map of values, where column names are the keys


