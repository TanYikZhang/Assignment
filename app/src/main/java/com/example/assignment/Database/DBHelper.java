package com.example.assignment.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.assignment.Model.Customer;

import java.security.MessageDigest;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyDatabase.db";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Same as :
        //CREATE TABLE contacts (id INTEGER PRIMARY KEY, name TEXT, phoneNumber TEXT)
        db.execSQL(
                "CREATE TABLE " + Customer.TABLE_NAME
                        + "(" + Customer.COLOMN_ID + " INTEGER PRIMARY KEY, "
                        + Customer.COLOMN_EMAIL + " TEXT, "
                        + Customer.COLOMN_FULLNAME + " TEXT, "
                        + Customer.COLOMN_PHONE_NUMBER + " TEXT, "
                        + Customer.COLOMN_PASSWORD + " TEXT, "
                        + Customer.COLOMN_ACCESSCONTROL + " INTEGER);"
        );
        /////////////////////////////////////////////////////////////////
        ContentValues values = new ContentValues();
        Customer customer =new Customer("admin","TYZ","0103893988",setSHA256("admin"));
        customer.setAccessControl(1);

        values.put(Customer.COLOMN_EMAIL,customer.getEmail());
        values.put(Customer.COLOMN_FULLNAME,customer.getFullname());
        values.put(Customer.COLOMN_PHONE_NUMBER,customer.getPhoneNumber());
        values.put(Customer.COLOMN_PASSWORD,customer.getPassword());
        values.put(Customer.COLOMN_ACCESSCONTROL,customer.getAccessControl());
        db.insert(Customer.TABLE_NAME, null,values);
        //////////////////////////////////////////////////////////////////
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Customer.TABLE_NAME);
        onCreate(db);
    }

    public boolean insertCustomer(Customer customer){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Customer.COLOMN_EMAIL,customer.getEmail());
        values.put(Customer.COLOMN_FULLNAME,customer.getFullname());
        values.put(Customer.COLOMN_PHONE_NUMBER,customer.getPhoneNumber());
        values.put(Customer.COLOMN_PASSWORD,customer.getPassword());
        values.put(Customer.COLOMN_ACCESSCONTROL,customer.getAccessControl());
        db.insert(Customer.TABLE_NAME, null,values);
        return true;
    }

    public void updateCustomer(Customer customer){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Customer.COLOMN_EMAIL,customer.getEmail());
        values.put(Customer.COLOMN_FULLNAME,customer.getFullname());
        values.put(Customer.COLOMN_PHONE_NUMBER,customer.getPhoneNumber());
        values.put(Customer.COLOMN_PASSWORD,customer.getPassword());
        values.put(Customer.COLOMN_ACCESSCONTROL,customer.getAccessControl());
        db.update(Customer.TABLE_NAME, values, Customer.COLOMN_ID + " = ? ",
                new String[]{Integer.toString(customer.getId())});
    }

    public void deleteCustomer(Integer id){
        SQLiteDatabase db= getWritableDatabase();

        db.delete(Customer.TABLE_NAME, Customer.COLOMN_ID + " = ?",
                new String[]{Integer.toString(id)});

    }

    public ArrayList<Customer> getAllCustomer(){
        ArrayList<Customer> contactArrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Customer.TABLE_NAME +" WHERE "+Customer.COLOMN_ACCESSCONTROL +" = 2", null);
        res.moveToFirst();

        while (!res.isAfterLast()){
            Customer contact = new Customer();
            contact.setId(res.getInt(res.getColumnIndex(Customer.COLOMN_ID)));
            contact.setEmail(res.getString(res.getColumnIndex(Customer.COLOMN_EMAIL)));
            contact.setFullname(res.getString(res.getColumnIndex(Customer.COLOMN_FULLNAME)));
            contact.setPhoneNumber(res.getString(res.getColumnIndex(Customer.COLOMN_PHONE_NUMBER)));
            contact.setPassword(res.getString(res.getColumnIndex(Customer.COLOMN_PASSWORD)));
            contact.setAccessControl(res.getInt(res.getColumnIndex(Customer.COLOMN_ACCESSCONTROL)));

            contactArrayList.add(contact);
            res.moveToNext();
        }
        res.close();
        return contactArrayList;
    }

    public ArrayList<Customer> getAllPerson(){
        ArrayList<Customer> contactArrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Customer.TABLE_NAME, null);
        res.moveToFirst();

        while (!res.isAfterLast()){
            Customer contact = new Customer();
            contact.setId(res.getInt(res.getColumnIndex(Customer.COLOMN_ID)));
            contact.setEmail(res.getString(res.getColumnIndex(Customer.COLOMN_EMAIL)));
            contact.setFullname(res.getString(res.getColumnIndex(Customer.COLOMN_FULLNAME)));
            contact.setPhoneNumber(res.getString(res.getColumnIndex(Customer.COLOMN_PHONE_NUMBER)));
            contact.setPassword(res.getString(res.getColumnIndex(Customer.COLOMN_PASSWORD)));
            contact.setAccessControl(res.getInt(res.getColumnIndex(Customer.COLOMN_ACCESSCONTROL)));

            contactArrayList.add(contact);
            res.moveToNext();
        }
        res.close();
        return contactArrayList;
    }
    private String setSHA256(String x){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(x.getBytes());

            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        }catch(Exception ex){
            throw new RuntimeException(ex);
        }

    }
}
