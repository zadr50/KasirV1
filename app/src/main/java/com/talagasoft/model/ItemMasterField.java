package com.talagasoft.model;

import android.content.Intent;
import android.database.Cursor;

/**
 * Created by compaq on 03/24/2016.
 */
public class ItemMasterField {
    private String _barcode,_name,_category,_unit,_supplier, _picture;
    private int _qty;
    private double _price, _cost;

    public ItemMasterField(){};

    public void getRecord(Cursor cursor){
        this._barcode=cursor.getString(cursor.getColumnIndex("barcode"));
        this._name=cursor.getString(cursor.getColumnIndex("item_name"));
        this._category=cursor.getString(cursor.getColumnIndex("category"));
        this._unit=cursor.getString(cursor.getColumnIndex("unit"));
        this._supplier=cursor.getString(cursor.getColumnIndex("supplier"));
        this._picture=cursor.getString(cursor.getColumnIndex("picture"));
        this._qty=cursor.getInt(cursor.getColumnIndex("current_qty"));
        this._price=cursor.getDouble(cursor.getColumnIndex("price"));
        this._cost=cursor.getDouble(cursor.getColumnIndex("cost"));
    };

    public ItemMasterField(String barcode, String name, String category, String unit, String supplier,
                           String picture, int qty, double price, double cost){
        this._barcode=barcode;
        this._name=name;
        this._category=category;
        this._supplier=supplier;
        this._qty=qty;
        this._price=price;
        this._cost=cost;
        this._picture=picture;
        this._unit=unit;
    };

    public String getBarcode(){return this._barcode;};
    public void setBarcode(String value) {
        this._barcode = value;
    };
    public String getName(){return this._name;};
    public void setName(String value) {
        this._name = value;
    };
    public String getCategory(){return this._category;};
    public void setCategory(String value) {
        this._category = value;
    };
    public String getSupplier(){return this._supplier;};
    public void setSupplier(String value) {
        this._supplier = value;
    };
    public String get_picture(){return this._picture;};
    public void setPicture(String value) {
        this._picture = value;
    };
    public int getQty(){return this._qty;};
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

    public Intent getIntent(Intent intent) {
        intent.putExtra("barcode",this._barcode);
        intent.putExtra("item_name",this._name);
        intent.putExtra("category",this._category);
        intent.putExtra("unit",this._unit);
        intent.putExtra("supplier",this._supplier);
        intent.putExtra("price",this._price);
        intent.putExtra("cost",this._cost);
        intent.putExtra("current_qty",this._qty);
        intent.putExtra("picture",this._picture);
        return intent;
    }

}

