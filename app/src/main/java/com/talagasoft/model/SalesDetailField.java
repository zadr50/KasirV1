package com.talagasoft.model;

import android.content.Intent;
import android.database.Cursor;

/**
 * Created by andri on 03/01/2017.
 */

public class SalesDetailField {
    private int _id;
    private String _invoice_no, _barcode, _item_name, _unit , _comments;
    private double _qty, _price, _cost, _discount, _amount;

    public SalesDetailField(){};
    public String getBarcode(){return this._barcode;};
    public void setBarcode(String value) {
        this._barcode = value;
    };
    public String getName(){return this._item_name;};
    public void setName(String value) {
        this._item_name = value;
    };
    public String getInvoiceNo(){return this._invoice_no;};
    public void setInvoiceNo(String value) {
        this._invoice_no = value;
    };
    public double getDiscount(){return this._discount;};
    public void setDiscount(double value) {
        this._discount = value;
    };
    public String getComments(){return this._comments;};
    public void setComments(String value) {
        this._comments = value;
    };
    public double getQty(){return this._qty;};
    public void setQty(int value) {
        this._qty = value;
    };
    public double getPrice(){return this._price;};
    public void setPrice(double value) {
        this._price = value;
    };
    public double getCost(){return this._cost;};
    public void setCost(double value) {
        this._cost = value;
    };
    public String getUnit(){return this._unit;};
    public void setUnit(String value) { this._unit = value; };

    public void getRecord(Cursor cursor){
        this._id=cursor.getInt(cursor.getColumnIndex("id"));
        this._invoice_no=cursor.getString(cursor.getColumnIndex("invoice_no"));
        this._barcode=cursor.getString(cursor.getColumnIndex("barcode"));
        this._item_name=cursor.getString(cursor.getColumnIndex("item_name"));
        this._unit=cursor.getString(cursor.getColumnIndex("unit"));
        this._qty=cursor.getDouble(cursor.getColumnIndex("qty"));
        this._price=cursor.getDouble(cursor.getColumnIndex("price"));
        this._cost=cursor.getDouble(cursor.getColumnIndex("cost"));
        this._discount=cursor.getDouble(cursor.getColumnIndex("discount"));
        this._amount=cursor.getDouble(cursor.getColumnIndex("amount"));
        this._comments=cursor.getString(cursor.getColumnIndex("comments"));
    };

    public Intent getIntent(Intent intent) {
        intent.putExtra("barcode",this._barcode);
        intent.putExtra("item_name",this._item_name);
        intent.putExtra("invoice_no",this._invoice_no);
        intent.putExtra("unit",this._unit);
        intent.putExtra("comments",this._comments);
        intent.putExtra("price",this._price);
        intent.putExtra("cost",this._cost);
        intent.putExtra("qty",this._qty);
        intent.putExtra("discount",this._discount);
        intent.putExtra("id",this._id);
        intent.putExtra("amount",this._amount);
        return intent;
    }

}

