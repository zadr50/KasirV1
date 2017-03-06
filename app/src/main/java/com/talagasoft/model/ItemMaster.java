package com.talagasoft.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by compaq on 03/23/2016.
 */
public class ItemMaster extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private final Context context;
    private String mTable = "ksr_item_master";
    private final String BARCODE = "barcode";
    private final String CATEGORY="category";
    private final String ITEM_NAME = "item_name";
    private final String UNIT = "unit";
    private final String PRICE = "price";
    private final String COST = "cost";
    private final String QTY = "current_qty";
    private final String PICTURE = "picture";
    private final String SUPPLIER = "supplier";

    public ItemMaster(Context konteks, String nama, SQLiteDatabase.CursorFactory f, int versi) {
        super(konteks,nama,f,versi);
        this.context = konteks;
    }

    public void open()  {
        try {
            db = getWritableDatabase();
        }
        catch (SQLiteException e) {
            db = getReadableDatabase();
        }
    }
    public void close() {
        db.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="";
        this.db=sqLiteDatabase;

        try {
            sql="create table ksr_item_master ( " +
                    "barcode primary key, item_name text not null, unit text," +
                    "current_qty int, price double, cost double, picture text, " +
                    "supplier text,category text)";
            db.execSQL(sql);
        }
        catch (SQLiteException e) {
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        this.db=sqLiteDatabase;
        db.execSQL("drop table if exists ksr_item_master");
        onCreate(db);
    }


    public ArrayList<ItemMasterField> getAll() {
        ArrayList<ItemMasterField> records = new ArrayList<ItemMasterField>();
        Cursor cursor=db.query(mTable,
                new String[] {BARCODE,ITEM_NAME,CATEGORY,UNIT,PRICE,COST,QTY,PICTURE,SUPPLIER},
                null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                ItemMasterField data=new ItemMasterField();
                data.getRecord(cursor);
                records.add(data);
            }while (cursor.moveToNext());
        }
        return records;
    }
    public boolean delete(String barcode) {
        return db.delete(mTable,BARCODE +  "='" + barcode + "'", null) > 0;
    }
    public Cursor getData(String barcode) {
        Cursor c = db.query(mTable,
                new String[] {BARCODE,ITEM_NAME,CATEGORY,UNIT,PRICE,COST,QTY,PICTURE,SUPPLIER},
                BARCODE + "='" + barcode + "'",
                null, null, null, null);
        if (c != null)
            c.moveToFirst();
        return c;
    }
    public boolean update(String barcode, String item_name, String category, String unit, double price,
                          double cost,int qty,String supplier,String picture) {
        ContentValues data = new ContentValues();
        //data.put(BARCODE, barcode);
        data.put(ITEM_NAME, item_name);
        data.put(UNIT, unit);
        data.put(PRICE, price);
        data.put(COST, cost);
        data.put(QTY, qty);
        data.put(PICTURE, picture);
        data.put(SUPPLIER,supplier);
        data.put(CATEGORY,category);
        data.put(PICTURE,picture);

        try {
        return db.update(mTable, data,
                BARCODE + "='" + barcode + "'", null) > 0;
        }
        catch (SQLiteException e) {
            return false;
        }
    }
    public long insert(String barcode, String item_name, String category, String unit, double price,
                       double cost,int qty,String supplier,String picture) {
        ContentValues data = new ContentValues();
        data.put(BARCODE, barcode);
        data.put(ITEM_NAME, item_name);
        data.put(UNIT, unit);
        data.put(PRICE, price);
        data.put(COST, cost);
        data.put(QTY, qty);
        data.put(PICTURE, picture);
        data.put(SUPPLIER,supplier);
        data.put(CATEGORY,category);

        try {
            return db.insert(mTable, null, data);
        }
        catch (SQLiteException e) {
            return -1;
        }
    }
}
