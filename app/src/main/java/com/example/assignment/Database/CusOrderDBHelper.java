package com.example.assignment.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.assignment.Model.Customer;
import com.example.assignment.Model.PC;

import java.security.MessageDigest;
import java.util.ArrayList;

public class CusOrderDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyDataba.db";

    private static final
            String TABLE_ORDER = "CREATE TABLE IF NOT EXISTS "
            + PC.TABLE_NAME + " (" + PC.COLOMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PC.COLOMN_CUSID + " INTEGER, "
            + PC.COLOMN_DATE + " TEXT, "
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
            + " FOREIGN KEY " + "("+PC.COLOMN_CUSID+")" +" REFERENCES "+ Customer.TABLE_NAME+"("+Customer.COLOMN_ID+")" + ");"
            ;

    public CusOrderDBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Same as :
        //CREATE TABLE contacts (id INTEGER PRIMARY KEY, name TEXT, phoneNumber TEXT)
        db.execSQL(TABLE_ORDER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + PC.TABLE_NAME);
        onCreate(db);
    }

    public boolean insertOrderPC(PC PC){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PC.COLOMN_CUSID,PC.getCusid());
        values.put(PC.COLOMN_CHASSIS,PC.getTypeChassis());
        values.put(PC.COLOMN_MOTHERBOARD,PC.getTypeMotherBoard());
        values.put(PC.COLOMN_CPU,PC.getTypeCPU());
        values.put(PC.COLOMN_RAM,PC.getTypeRAM());
        values.put(PC.COLOMN_GRAPHICSCARD,PC.getTypeGraphicscard());
        values.put(PC.COLOMN_FIRSTSSD,PC.getTypeFirstSlotSSD());
        values.put(PC.COLOMN_SECONDSSD,PC.getTypeSecondSlotSSD());
        values.put(PC.COLOMN_HDD,PC.getTypeHardDrive());
        values.put(PC.COLOMN_COOLINGSYSTEM,PC.getTypeCoolingSystem());
        values.put(PC.COLOMN_CASESLIGHTING,PC.getTypeCasesLighting());
        values.put(PC.COLOMN_PSU,PC.getTypePowerSupply());
        values.put(PC.COLOMN_WIRELESSLAN,PC.getTypeWirelessLan());
        values.put(PC.COLOMN_OS,PC.getTypeOS());
        values.put(PC.COLOMN_WARRANTY,PC.getTypeWarrantyPackage());
        values.put(PC.COLOMN_TOTALPRICE,PC.getTotalPrice());
        db.insert(PC.TABLE_NAME, null,values);
        return true;
    }

    public void updateOrderPC(PC PC){
        SQLiteDatabase db= getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PC.COLOMN_CUSID,PC.getCusid());
        values.put(PC.COLOMN_CHASSIS,PC.getTypeChassis());
        values.put(PC.COLOMN_MOTHERBOARD,PC.getTypeMotherBoard());
        values.put(PC.COLOMN_CPU,PC.getTypeCPU());
        values.put(PC.COLOMN_RAM,PC.getTypeRAM());
        values.put(PC.COLOMN_GRAPHICSCARD,PC.getTypeGraphicscard());
        values.put(PC.COLOMN_FIRSTSSD,PC.getTypeFirstSlotSSD());
        values.put(PC.COLOMN_SECONDSSD,PC.getTypeSecondSlotSSD());
        values.put(PC.COLOMN_HDD,PC.getTypeHardDrive());
        values.put(PC.COLOMN_COOLINGSYSTEM,PC.getTypeCoolingSystem());
        values.put(PC.COLOMN_CASESLIGHTING,PC.getTypeCasesLighting());
        values.put(PC.COLOMN_PSU,PC.getTypePowerSupply());
        values.put(PC.COLOMN_WIRELESSLAN,PC.getTypeWirelessLan());
        values.put(PC.COLOMN_OS,PC.getTypeOS());
        values.put(PC.COLOMN_WARRANTY,PC.getTypeWarrantyPackage());
        values.put(PC.COLOMN_TOTALPRICE,PC.getTotalPrice());

        db.update(PC.TABLE_NAME, values, PC.COLOMN_ID + " = ? ",
                new String[]{Integer.toString(PC.getId())});
    }

    public void deleteOrderPC(Integer id){
        SQLiteDatabase db= getWritableDatabase();

        db.delete(PC.TABLE_NAME, PC.COLOMN_ID + " = ?",
                new String[]{Integer.toString(id)});

    }

    public ArrayList<PC> getAllOrder(){
        ArrayList<PC> OrderPCArrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + PC.TABLE_NAME , null);
        res.moveToFirst();

        while (!res.isAfterLast()){
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

    public ArrayList<PC> getOrderHistory(int id){
        ArrayList<PC> OrderPCArrayList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + PC.TABLE_NAME + " WHERE id = " + id, null);
        res.moveToFirst();

        while (!res.isAfterLast()){
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

}
