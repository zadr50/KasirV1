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
public class SalesDetail extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private final Context context;
    private final String TABLE          = "ksr_sales_detail";
    private final String ID             = "id";
    private final String INVOICE_NO     = "invoice_no";
    private final String BARCODE        = "barcode";
    private final String ITEM_NAME      = "item_name";
    private final String UNIT           = "unit";
    private final String QTY            = "qty";
    private final String PRICE          = "price";
    private final String COST           = "cost";
    private final String DISCOUNT       = "discount";
    private final String AMOUNT         = "amount";
    private final String COMMENTS       = "comments";

    private String[] _fields = new String[] {ID, INVOICE_NO,BARCODE,ITEM_NAME,
            UNIT,QTY,PRICE,COST,DISCOUNT,AMOUNT, COMMENTS};

    public SalesDetail(Context konteks, String nama, SQLiteDatabase.CursorFactory f, int versi) {
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
            sql="create table " + TABLE + " ( " +
                    "id int primary key autoincrement not null, invoice_no text not null, barcode text," +
                    "item_name text, unit text, qty double, price double, cost double, " +
                    "discount double, amount double, comments text)";
            db.execSQL(sql);
        }
        catch (SQLiteException e) {
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        this.db=sqLiteDatabase;
        db.execSQL("drop table if exists " + TABLE);
        onCreate(db);
    }


    public ArrayList<SalesDetailField> getAll() {
        ArrayList<SalesDetailField> records = new ArrayList<SalesDetailField>();
        Cursor cursor=db.query( TABLE, _fields, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                SalesDetailField data=new SalesDetailField();
                data.getRecord(cursor);
                records.add(data);
            } while (cursor.moveToNext());
        }
        return records;
    }
    public boolean delete(String barcode) {
        return db.delete(TABLE, BARCODE +  "='" + barcode + "'", null) > 0;
    }
    public Cursor getData(String barcode) {
        Cursor c = db.query(TABLE, _fields, BARCODE + "='" + barcode + "'",
                null, null, null, null);
        if (c != null) c.moveToFirst();
        return c;
    }
    public boolean update(int id, String invoice, String barcode, String item_name, double qty, String unit,
                          double price, double cost, float discount, double amount, String comments) {

        ContentValues data = new ContentValues();
        data.put(INVOICE_NO, invoice);
        data.put(UNIT, unit);
        data.put(PRICE, price);
        data.put(COST, cost);
        data.put(QTY, qty);
        data.put(ITEM_NAME, item_name);
        data.put(DISCOUNT, discount);
        data.put(COMMENTS, comments);
        data.put(AMOUNT, amount);
        data.put(BARCODE, barcode);

        try {
            return db.update(TABLE, data, ID + "='" + id + "'", null) > 0;
        }
        catch (SQLiteException e) {
            return false;
        }
    }
    public long insert(int id, String invoice, String barcode, String item_name, double qty, String unit,
                       double price, double cost, float discount, double amount, String comments) {
        ContentValues data = new ContentValues();
        data.put(INVOICE_NO, invoice);
        data.put(UNIT, unit);
        data.put(PRICE, price);
        data.put(COST, cost);
        data.put(QTY, qty);
        data.put(ITEM_NAME, item_name);
        data.put(DISCOUNT, discount);
        data.put(COMMENTS, comments);
        data.put(AMOUNT, amount);
        data.put(BARCODE, barcode);
        try {
            return db.insert(TABLE, null, data);
        }
        catch (SQLiteException e) {
            return -1;
        }
    }
}
