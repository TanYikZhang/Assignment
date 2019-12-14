package com.example.assignment.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.assignment.Model.Customer;
import com.example.assignment.Model.PC;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyDatabase.db";

    private static final String TABLE_CUSTOMER = "CREATE TABLE " + Customer.TABLE_NAME
            + "(" + Customer.COLOMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Customer.COLOMN_EMAIL + " TEXT, "
            + Customer.COLOMN_FULLNAME + " TEXT, "
            + Customer.COLOMN_PHONE_NUMBER + " TEXT, "
            + Customer.COLOMN_PASSWORD + " TEXT, "
            + Customer.COLOMN_ACCESSCONTROL + " INTEGER);";

    private static final String TABLE_ORDER = "CREATE TABLE IF NOT EXISTS "
            + PC.TABLE_NAME + " (" + PC.COLOMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PC.COLOMN_CUSID + " INTEGER, "
            + PC.COLOMN_DATE + " DATE, "
            + PC.COLOMN_PROCESS + " TEXT, "
            + PC.COLOMN_CHASSIS + " TEXT, "
            + PC.COLOMN_MOTHERBOARD + " TEXT, "
            + PC.COLOMN_CPU + " TEXT, "
            + PC.COLOMN_RAM + " TEXT, "
            + PC.COLOMN_GRAPHICSCARD + " TEXT, "
            + PC.COLOMN_FIRSTSSD + " TEXT, "
            + PC.COLOMN_SECONDSSD + " TEXT, "
            + PC.COLOMN_HDD + " TEXT, "
            + PC.COLOMN_COOLINGSYSTEM + " TEXT, "
            + PC.COLOMN_CASESLIGHTING + " TEXT, "
            + PC.COLOMN_PSU + " TEXT, "
            + PC.COLOMN_WIRELESSLAN + " TEXT, "
            + PC.COLOMN_OS + " TEXT, "
            + PC.COLOMN_WARRANTY + " TEXT, "
            + PC.COLOMN_TOTALPRICE + " INTEGER, "
            + " FOREIGN KEY " + "(" + PC.COLOMN_CUSID + ")" + " REFERENCES " + Customer.TABLE_NAME + "(" + Customer.COLOMN_ID + ")" + ");";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Same as :
        //CREATE TABLE contacts (id INTEGER PRIMARY KEY, name TEXT, phoneNumber TEXT)
        db.execSQL(TABLE_CUSTOMER);
        db.execSQL(TABLE_ORDER);
      /*  db.execSQL(
                "CREATE TABLE " + Customer.TABLE_NAME
                        + "(" + Customer.COLOMN_ID + " INTEGER PRIMARY KEY, "
                        + Customer.COLOMN_EMAIL + " TEXT, "
                        + Customer.COLOMN_FULLNAME + " TEXT, "
                        + Customer.COLOMN_PHONE_NUMBER + " TEXT, "
                        + Customer.COLOMN_PASSWORD + " TEXT, "
                        + Customer.COLOMN_ACCESSCONTROL + " INTEGER);"
        );*/
        /////////////////////////////////////////////////////////////////
        ContentValues values = new ContentValues();
        Customer customer = new Customer("admin", "TYZ", "0103893988", setSHA256("admin"));
        customer.setAccessControl(1);

        values.put(Customer.COLOMN_EMAIL, customer.getEmail());
        values.put(Customer.COLOMN_FULLNAME, customer.getFullname());
        values.put(Customer.COLOMN_PHONE_NUMBER, customer.getPhoneNumber());
        values.put(Customer.COLOMN_PASSWORD, customer.getPassword());
        values.put(Customer.COLOMN_ACCESSCONTROL, customer.getAccessControl());
        db.insert(Customer.TABLE_NAME, null, values);
        //////////////////////////////////////////////////////////////////
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Customer.TABLE_NAME);
        onCreate(db);
    }

    public boolean insertCustomer(Customer customer) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Customer.COLOMN_EMAIL, customer.getEmail());
        values.put(Customer.COLOMN_FULLNAME, customer.getFullname());
        values.put(Customer.COLOMN_PHONE_NUMBER, customer.getPhoneNumber());
        values.put(Customer.COLOMN_PASSWORD, customer.getPassword());
        values.put(Customer.COLOMN_ACCESSCONTROL, customer.getAccessControl());
        db.insert(Customer.TABLE_NAME, null, values);
        return true;
    }

    public void updateCustomer(Customer customer) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Customer.COLOMN_EMAIL, customer.getEmail());
        values.put(Customer.COLOMN_FULLNAME, customer.getFullname());
        values.put(Customer.COLOMN_PHONE_NUMBER, customer.getPhoneNumber());
        values.put(Customer.COLOMN_PASSWORD, customer.getPassword());
        values.put(Customer.COLOMN_ACCESSCONTROL, customer.getAccessControl());
        db.update(Customer.TABLE_NAME, values, Customer.COLOMN_ID + " = ? ",
                new String[]{Integer.toString(customer.getId())});
    }

    public void deleteCustomer(Integer id) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(Customer.TABLE_NAME, Customer.COLOMN_ID + " = ?",
                new String[]{Integer.toString(id)});

    }

    public ArrayList<Customer> getAllCustomer() {
        ArrayList<Customer> contactArrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Customer.TABLE_NAME + " WHERE " + Customer.COLOMN_ACCESSCONTROL + " = 2", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            Customer customer = new Customer();
            customer.setId(res.getInt(res.getColumnIndex(Customer.COLOMN_ID)));
            customer.setEmail(res.getString(res.getColumnIndex(Customer.COLOMN_EMAIL)));
            customer.setFullname(res.getString(res.getColumnIndex(Customer.COLOMN_FULLNAME)));
            customer.setPhoneNumber(res.getString(res.getColumnIndex(Customer.COLOMN_PHONE_NUMBER)));
            customer.setPassword(res.getString(res.getColumnIndex(Customer.COLOMN_PASSWORD)));
            customer.setAccessControl(res.getInt(res.getColumnIndex(Customer.COLOMN_ACCESSCONTROL)));

            contactArrayList.add(customer);
            res.moveToNext();
        }
        res.close();
        return contactArrayList;
    }

    public ArrayList<Customer> getCustomer(String text) {
        ArrayList<Customer> contactArrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Customer.TABLE_NAME + " WHERE " + Customer.COLOMN_ACCESSCONTROL + " = 2 "
                + " AND " + Customer.COLOMN_FULLNAME + " LIKE " + "'%" + text + "%'", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            Customer customer = new Customer();
            customer.setId(res.getInt(res.getColumnIndex(Customer.COLOMN_ID)));
            customer.setEmail(res.getString(res.getColumnIndex(Customer.COLOMN_EMAIL)));
            customer.setFullname(res.getString(res.getColumnIndex(Customer.COLOMN_FULLNAME)));
            customer.setPhoneNumber(res.getString(res.getColumnIndex(Customer.COLOMN_PHONE_NUMBER)));
            customer.setPassword(res.getString(res.getColumnIndex(Customer.COLOMN_PASSWORD)));
            customer.setAccessControl(res.getInt(res.getColumnIndex(Customer.COLOMN_ACCESSCONTROL)));

            contactArrayList.add(customer);
            res.moveToNext();
        }
        res.close();
        return contactArrayList;
    }

    public ArrayList<Customer> getAllPerson() {
        ArrayList<Customer> contactArrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + Customer.TABLE_NAME, null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            Customer customer = new Customer();
            customer.setId(res.getInt(res.getColumnIndex(Customer.COLOMN_ID)));
            customer.setEmail(res.getString(res.getColumnIndex(Customer.COLOMN_EMAIL)));
            customer.setFullname(res.getString(res.getColumnIndex(Customer.COLOMN_FULLNAME)));
            customer.setPhoneNumber(res.getString(res.getColumnIndex(Customer.COLOMN_PHONE_NUMBER)));
            customer.setPassword(res.getString(res.getColumnIndex(Customer.COLOMN_PASSWORD)));
            customer.setAccessControl(res.getInt(res.getColumnIndex(Customer.COLOMN_ACCESSCONTROL)));

            contactArrayList.add(customer);
            res.moveToNext();
        }
        res.close();
        return contactArrayList;
    }


    public boolean insertOrderPC(PC PC) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new Date());

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PC.COLOMN_CUSID, PC.getCusid());
        values.put(PC.COLOMN_PROCESS, PC.getProcess());
        values.put(PC.COLOMN_DATE, strDate);
        values.put(PC.COLOMN_CHASSIS, PC.getTypeChassis());
        values.put(PC.COLOMN_MOTHERBOARD, PC.getTypeMotherBoard());
        values.put(PC.COLOMN_CPU, PC.getTypeCPU());
        values.put(PC.COLOMN_RAM, PC.getTypeRAM());
        values.put(PC.COLOMN_GRAPHICSCARD, PC.getTypeGraphicscard());
        values.put(PC.COLOMN_FIRSTSSD, PC.getTypeFirstSlotSSD());
        values.put(PC.COLOMN_SECONDSSD, PC.getTypeSecondSlotSSD());
        values.put(PC.COLOMN_HDD, PC.getTypeHardDrive());
        values.put(PC.COLOMN_COOLINGSYSTEM, PC.getTypeCoolingSystem());
        values.put(PC.COLOMN_CASESLIGHTING, PC.getTypeCasesLighting());
        values.put(PC.COLOMN_PSU, PC.getTypePowerSupply());
        values.put(PC.COLOMN_WIRELESSLAN, PC.getTypeWirelessLan());
        values.put(PC.COLOMN_OS, PC.getTypeOS());
        values.put(PC.COLOMN_WARRANTY, PC.getTypeWarrantyPackage());
        values.put(PC.COLOMN_TOTALPRICE, PC.getTotalPrice());
        db.insert(PC.TABLE_NAME, null, values);
        return true;
    }

    public void updateOrderPC(PC PC) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PC.COLOMN_CUSID, PC.getCusid());
        values.put(PC.COLOMN_PROCESS, PC.getProcess());
        values.put(PC.COLOMN_CHASSIS, PC.getTypeChassis());
        values.put(PC.COLOMN_MOTHERBOARD, PC.getTypeMotherBoard());
        values.put(PC.COLOMN_CPU, PC.getTypeCPU());
        values.put(PC.COLOMN_RAM, PC.getTypeRAM());
        values.put(PC.COLOMN_GRAPHICSCARD, PC.getTypeGraphicscard());
        values.put(PC.COLOMN_FIRSTSSD, PC.getTypeFirstSlotSSD());
        values.put(PC.COLOMN_SECONDSSD, PC.getTypeSecondSlotSSD());
        values.put(PC.COLOMN_HDD, PC.getTypeHardDrive());
        values.put(PC.COLOMN_COOLINGSYSTEM, PC.getTypeCoolingSystem());
        values.put(PC.COLOMN_CASESLIGHTING, PC.getTypeCasesLighting());
        values.put(PC.COLOMN_PSU, PC.getTypePowerSupply());
        values.put(PC.COLOMN_WIRELESSLAN, PC.getTypeWirelessLan());
        values.put(PC.COLOMN_OS, PC.getTypeOS());
        values.put(PC.COLOMN_WARRANTY, PC.getTypeWarrantyPackage());
        values.put(PC.COLOMN_TOTALPRICE, PC.getTotalPrice());

        db.update(PC.TABLE_NAME, values, PC.COLOMN_ID + " = ? ",
                new String[]{Integer.toString(PC.getId())});
    }

    public void deleteOrderPC(Integer id) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(PC.TABLE_NAME, PC.COLOMN_ID + " = ?",
                new String[]{Integer.toString(id)});

    }

    public ArrayList<PC> getAllOrder() {
        ArrayList<PC> OrderPCArrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + PC.TABLE_NAME + " ORDER BY " + PC.COLOMN_ID + " DESC", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            PC PC = new PC();
            PC.setId(res.getInt(res.getColumnIndex(PC.COLOMN_ID)));
            PC.setCusid(res.getInt(res.getColumnIndex(PC.COLOMN_CUSID)));
            PC.setDateBuild(res.getString(res.getColumnIndex(PC.COLOMN_DATE)));
            PC.setProcess(res.getString(res.getColumnIndex(PC.COLOMN_PROCESS)));
            PC.setTypeChassis(res.getString(res.getColumnIndex(PC.COLOMN_CHASSIS)));
            PC.setTypeMotherBoard(res.getString(res.getColumnIndex(PC.COLOMN_MOTHERBOARD)));
            PC.setTypeCPU(res.getString(res.getColumnIndex(PC.COLOMN_CPU)));
            PC.setTypeRAM(res.getString(res.getColumnIndex(PC.COLOMN_RAM)));
            PC.setTypeGraphicscard(res.getString(res.getColumnIndex(PC.COLOMN_GRAPHICSCARD)));
            PC.setTypeFirstSlotSSD(res.getString(res.getColumnIndex(PC.COLOMN_FIRSTSSD)));
            PC.setTypeSecondSlotSSD(res.getString(res.getColumnIndex(PC.COLOMN_SECONDSSD)));
            PC.setTypeHardDrive(res.getString(res.getColumnIndex(PC.COLOMN_HDD)));
            PC.setTypeCoolingSystem(res.getString(res.getColumnIndex(PC.COLOMN_COOLINGSYSTEM)));
            PC.setTypeCasesLighting(res.getString(res.getColumnIndex(PC.COLOMN_CASESLIGHTING)));
            PC.setTypePowerSupply(res.getString(res.getColumnIndex(PC.COLOMN_PSU)));
            PC.setTypeWirelessLan(res.getString(res.getColumnIndex(PC.COLOMN_WIRELESSLAN)));
            PC.setTypeOS(res.getString(res.getColumnIndex(PC.COLOMN_OS)));
            PC.setTypeWarrantyPackage(res.getString(res.getColumnIndex(PC.COLOMN_WARRANTY)));
            PC.setTotalPrice(res.getInt(res.getColumnIndex(PC.COLOMN_TOTALPRICE)));

            OrderPCArrayList.add(PC);
            res.moveToNext();
        }
        res.close();
        return OrderPCArrayList;
    }

    public ArrayList<PC> getOrder(int id) {
        ArrayList<PC> OrderPCArrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + PC.TABLE_NAME + " WHERE " + PC.COLOMN_ID + " = " + id + " ORDER BY " + PC.COLOMN_ID + " DESC", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            PC PC = new PC();
            PC.setId(res.getInt(res.getColumnIndex(PC.COLOMN_ID)));
            PC.setCusid(res.getInt(res.getColumnIndex(PC.COLOMN_CUSID)));
            PC.setDateBuild(res.getString(res.getColumnIndex(PC.COLOMN_DATE)));
            PC.setProcess(res.getString(res.getColumnIndex(PC.COLOMN_PROCESS)));
            PC.setTypeChassis(res.getString(res.getColumnIndex(PC.COLOMN_CHASSIS)));
            PC.setTypeMotherBoard(res.getString(res.getColumnIndex(PC.COLOMN_MOTHERBOARD)));
            PC.setTypeCPU(res.getString(res.getColumnIndex(PC.COLOMN_CPU)));
            PC.setTypeRAM(res.getString(res.getColumnIndex(PC.COLOMN_RAM)));
            PC.setTypeGraphicscard(res.getString(res.getColumnIndex(PC.COLOMN_GRAPHICSCARD)));
            PC.setTypeFirstSlotSSD(res.getString(res.getColumnIndex(PC.COLOMN_FIRSTSSD)));
            PC.setTypeSecondSlotSSD(res.getString(res.getColumnIndex(PC.COLOMN_SECONDSSD)));
            PC.setTypeHardDrive(res.getString(res.getColumnIndex(PC.COLOMN_HDD)));
            PC.setTypeCoolingSystem(res.getString(res.getColumnIndex(PC.COLOMN_COOLINGSYSTEM)));
            PC.setTypeCasesLighting(res.getString(res.getColumnIndex(PC.COLOMN_CASESLIGHTING)));
            PC.setTypePowerSupply(res.getString(res.getColumnIndex(PC.COLOMN_PSU)));
            PC.setTypeWirelessLan(res.getString(res.getColumnIndex(PC.COLOMN_WIRELESSLAN)));
            PC.setTypeOS(res.getString(res.getColumnIndex(PC.COLOMN_OS)));
            PC.setTypeWarrantyPackage(res.getString(res.getColumnIndex(PC.COLOMN_WARRANTY)));
            PC.setTotalPrice(res.getInt(res.getColumnIndex(PC.COLOMN_TOTALPRICE)));

            OrderPCArrayList.add(PC);
            res.moveToNext();
        }
        res.close();
        return OrderPCArrayList;
    }

    public ArrayList<PC> getAllOrderHistory(int id) {
        ArrayList<PC> OrderPCArrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + PC.TABLE_NAME + " WHERE " + PC.COLOMN_CUSID + " = " + id + " ORDER BY " + PC.COLOMN_ID + " DESC", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            PC PC = new PC();
            PC.setId(res.getInt(res.getColumnIndex(PC.COLOMN_ID)));
            PC.setCusid(res.getInt(res.getColumnIndex(PC.COLOMN_CUSID)));
            PC.setDateBuild(res.getString(res.getColumnIndex(PC.COLOMN_DATE)));
            PC.setProcess(res.getString(res.getColumnIndex(PC.COLOMN_PROCESS)));
            PC.setTypeChassis(res.getString(res.getColumnIndex(PC.COLOMN_CHASSIS)));
            PC.setTypeMotherBoard(res.getString(res.getColumnIndex(PC.COLOMN_MOTHERBOARD)));
            PC.setTypeCPU(res.getString(res.getColumnIndex(PC.COLOMN_CPU)));
            PC.setTypeRAM(res.getString(res.getColumnIndex(PC.COLOMN_RAM)));
            PC.setTypeGraphicscard(res.getString(res.getColumnIndex(PC.COLOMN_GRAPHICSCARD)));
            PC.setTypeFirstSlotSSD(res.getString(res.getColumnIndex(PC.COLOMN_FIRSTSSD)));
            PC.setTypeSecondSlotSSD(res.getString(res.getColumnIndex(PC.COLOMN_SECONDSSD)));
            PC.setTypeHardDrive(res.getString(res.getColumnIndex(PC.COLOMN_HDD)));
            PC.setTypeCoolingSystem(res.getString(res.getColumnIndex(PC.COLOMN_COOLINGSYSTEM)));
            PC.setTypeCasesLighting(res.getString(res.getColumnIndex(PC.COLOMN_CASESLIGHTING)));
            PC.setTypePowerSupply(res.getString(res.getColumnIndex(PC.COLOMN_PSU)));
            PC.setTypeWirelessLan(res.getString(res.getColumnIndex(PC.COLOMN_WIRELESSLAN)));
            PC.setTypeOS(res.getString(res.getColumnIndex(PC.COLOMN_OS)));
            PC.setTypeWarrantyPackage(res.getString(res.getColumnIndex(PC.COLOMN_WARRANTY)));
            PC.setTotalPrice(res.getInt(res.getColumnIndex(PC.COLOMN_TOTALPRICE)));

            OrderPCArrayList.add(PC);
            res.moveToNext();
        }
        res.close();
        return OrderPCArrayList;
    }

    public ArrayList<PC> getOrderHistory(int cusid, int id) {
        ArrayList<PC> OrderPCArrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + PC.TABLE_NAME + " WHERE " + PC.COLOMN_CUSID + " = " + cusid + " AND " + PC.COLOMN_ID + " = " + id + " ORDER BY " + PC.COLOMN_ID + " DESC", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            PC PC = new PC();
            PC.setId(res.getInt(res.getColumnIndex(PC.COLOMN_ID)));
            PC.setCusid(res.getInt(res.getColumnIndex(PC.COLOMN_CUSID)));
            PC.setDateBuild(res.getString(res.getColumnIndex(PC.COLOMN_DATE)));
            PC.setProcess(res.getString(res.getColumnIndex(PC.COLOMN_PROCESS)));
            PC.setTypeChassis(res.getString(res.getColumnIndex(PC.COLOMN_CHASSIS)));
            PC.setTypeMotherBoard(res.getString(res.getColumnIndex(PC.COLOMN_MOTHERBOARD)));
            PC.setTypeCPU(res.getString(res.getColumnIndex(PC.COLOMN_CPU)));
            PC.setTypeRAM(res.getString(res.getColumnIndex(PC.COLOMN_RAM)));
            PC.setTypeGraphicscard(res.getString(res.getColumnIndex(PC.COLOMN_GRAPHICSCARD)));
            PC.setTypeFirstSlotSSD(res.getString(res.getColumnIndex(PC.COLOMN_FIRSTSSD)));
            PC.setTypeSecondSlotSSD(res.getString(res.getColumnIndex(PC.COLOMN_SECONDSSD)));
            PC.setTypeHardDrive(res.getString(res.getColumnIndex(PC.COLOMN_HDD)));
            PC.setTypeCoolingSystem(res.getString(res.getColumnIndex(PC.COLOMN_COOLINGSYSTEM)));
            PC.setTypeCasesLighting(res.getString(res.getColumnIndex(PC.COLOMN_CASESLIGHTING)));
            PC.setTypePowerSupply(res.getString(res.getColumnIndex(PC.COLOMN_PSU)));
            PC.setTypeWirelessLan(res.getString(res.getColumnIndex(PC.COLOMN_WIRELESSLAN)));
            PC.setTypeOS(res.getString(res.getColumnIndex(PC.COLOMN_OS)));
            PC.setTypeWarrantyPackage(res.getString(res.getColumnIndex(PC.COLOMN_WARRANTY)));
            PC.setTotalPrice(res.getInt(res.getColumnIndex(PC.COLOMN_TOTALPRICE)));

            OrderPCArrayList.add(PC);
            res.moveToNext();
        }
        res.close();
        return OrderPCArrayList;
    }

    public ArrayList<PC> getDatebetween(int x, int y) {
        ArrayList<PC> OrderPCArrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        //Cursor res = db.rawQuery("SELECT * FROM " + PC.TABLE_NAME + " WHERE "+ PC.COLOMN_DATE +" BETWEEN " + "date('2019-12-12')" + " AND "+ "date('2019-12-18')" + " ORDER BY " + PC.COLOMN_ID + " DESC" , null);
        Cursor res = db.rawQuery("SELECT * FROM " + PC.TABLE_NAME + " WHERE strftime('%Y %m'," + PC.COLOMN_DATE + ") = '" + x + " " + y + "'", null);
        res.moveToFirst();
        while (!res.isAfterLast()) {
            PC PC = new PC();
            PC.setId(res.getInt(res.getColumnIndex(PC.COLOMN_ID)));
            PC.setCusid(res.getInt(res.getColumnIndex(PC.COLOMN_CUSID)));
            PC.setDateBuild(res.getString(res.getColumnIndex(PC.COLOMN_DATE)));
            PC.setProcess(res.getString(res.getColumnIndex(PC.COLOMN_PROCESS)));
            PC.setTypeChassis(res.getString(res.getColumnIndex(PC.COLOMN_CHASSIS)));
            PC.setTypeMotherBoard(res.getString(res.getColumnIndex(PC.COLOMN_MOTHERBOARD)));
            PC.setTypeCPU(res.getString(res.getColumnIndex(PC.COLOMN_CPU)));
            PC.setTypeRAM(res.getString(res.getColumnIndex(PC.COLOMN_RAM)));
            PC.setTypeGraphicscard(res.getString(res.getColumnIndex(PC.COLOMN_GRAPHICSCARD)));
            PC.setTypeFirstSlotSSD(res.getString(res.getColumnIndex(PC.COLOMN_FIRSTSSD)));
            PC.setTypeSecondSlotSSD(res.getString(res.getColumnIndex(PC.COLOMN_SECONDSSD)));
            PC.setTypeHardDrive(res.getString(res.getColumnIndex(PC.COLOMN_HDD)));
            PC.setTypeCoolingSystem(res.getString(res.getColumnIndex(PC.COLOMN_COOLINGSYSTEM)));
            PC.setTypeCasesLighting(res.getString(res.getColumnIndex(PC.COLOMN_CASESLIGHTING)));
            PC.setTypePowerSupply(res.getString(res.getColumnIndex(PC.COLOMN_PSU)));
            PC.setTypeWirelessLan(res.getString(res.getColumnIndex(PC.COLOMN_WIRELESSLAN)));
            PC.setTypeOS(res.getString(res.getColumnIndex(PC.COLOMN_OS)));
            PC.setTypeWarrantyPackage(res.getString(res.getColumnIndex(PC.COLOMN_WARRANTY)));
            PC.setTotalPrice(res.getInt(res.getColumnIndex(PC.COLOMN_TOTALPRICE)));

            OrderPCArrayList.add(PC);
            res.moveToNext();
        }
        res.close();
        return OrderPCArrayList;
    }

    private String setSHA256(String x) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(x.getBytes());

            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
