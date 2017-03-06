package com.talagasoft.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import static com.talagasoft.model.Global.NAMA_DB;
import static com.talagasoft.model.Global.VERSI_DB;

/**
 * Created by compaq on 03/23/2016.
 */
public class Helper extends SQLiteOpenHelper {

    private SQLiteDatabase db;

    public Helper(Context konteks, String nama,  SQLiteDatabase.CursorFactory f, int versi) {
        super(konteks, nama, f, versi);
    }
    public Helper(Context konteks){
        super(konteks, NAMA_DB, null, VERSI_DB);
        try {
            this.db = getWritableDatabase();
        }
        catch (SQLiteException e) {
            this.db = getReadableDatabase();
        }
    }
    public void Execute(String sql){
        this.db.execSQL(sql);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db=db;
        create_table_item_master();
        create_table_sales();
        create_table_item_category();
        create_table_supplier();
        create_table_customer();
        create_table_tables();
        create_table_waiter();
    }
    private void create_table_waiter() {
        String sql="";
        try {
            sql="create table ksr_waiters ( " +
                    "waiter_no text primary key, description text not null,  status int)";
            this.db.execSQL(sql);
        }
        catch (SQLiteException e) {
        }
    }

    private void create_table_tables() {
        String sql="";
        try {
            sql="create table ksr_tables ( " +
                    "table_no text primary key, description text not null,  status int)";
            this.db.execSQL(sql);
        }
        catch (SQLiteException e) {
        }
    }
    private void create_table_customer() {
        String sql="";
        try {
            sql="create table ksr_customers ( " +
                    "customer_no text primary key, company text not null,  address text, " +
                    "phone txt, email text)";
            this.db.execSQL(sql);
        }
        catch (SQLiteException e) {
        }
    }

    private void create_table_supplier() {
        String sql="";
        try {
            sql="create table ksr_suppliers ( " +
                    "supplier_no text primary key, supplier_name text not null, address text, " +
                    "phone text, email text)";
            this.db.execSQL(sql);
        }
        catch (SQLiteException e) {
        }
    }
    private void create_table_item_category() {
        String sql="";
        try {
            sql="create table ksr_category ( " +
                    "category text primary key, description text not null,  picture text)";
            this.db.execSQL(sql);
        }
        catch (SQLiteException e) {
        }
    }

    private void create_table_item_master() {
        String sql="";
        try {
            sql="create table ksr_item_master ( " +
                    "barcode text primary key, item_name text not null, unit text," +
                    "current_qty double, price double, cost double, picture text, " +
                    "supplier text,category text)";
            this.db.execSQL(sql);
        }
        catch (SQLiteException e) {
        }
    }
    private void create_table_sales() {
        String sql="";
        try {
            sql="create table ksr_sales_header ( " +
                    "invoice_no text primary key, invoice_date datetime not null, customer text," +
                    "amount double, comments text, paid int)";
            this.db.execSQL(sql);
            sql="create table ksr_sales_detail ( " +
                    "id integer primary key, invoice_no text not null, barcode text," +
                    "item_name text, unit text, qty double, price double, cost double, " +
                    "discount double, amount double, comments text)";
            this.db.execSQL(sql);
            sql="create table ksr_sales_payment ( " +
                    "id integer primary key, invoice_no text not null, date_paid datetime, " +
                    "how_paid text, amount double, comments text)";
            this.db.execSQL(sql);
        }
        catch (SQLiteException e) {
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versiLama,
                          int versiBaru) {
        db.execSQL("drop tabel if exists ksr_item_master");
        onCreate(db);
        db.execSQL("drop tabel if exists ksr_category");
        onCreate(db);
        db.execSQL("drop tabel if exists ksr_customers");
        onCreate(db);
        db.execSQL("drop tabel if exists ksr_suppliers");
        onCreate(db);
        db.execSQL("drop tabel if exists ksr_sales_header");
        onCreate(db);
        db.execSQL("drop tabel if exists ksr_sales_detail");
        onCreate(db);
        db.execSQL("drop tabel if exists ksr_tables");
        onCreate(db);
        db.execSQL("drop tabel if exists ksr_waiters");
        onCreate(db);
        db.execSQL("drop tabel if exists ksr_sales_payment");
        onCreate(db);

    }
}