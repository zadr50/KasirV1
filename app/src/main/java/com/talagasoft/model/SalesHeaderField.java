package com.talagasoft.model;

import android.database.Cursor;

/**
 * Created by andri on 03/02/2017.
 */

public class SalesHeaderField {
    private final String TABLE          = "ksr_sales_header";
    private final String INVOICE_NO     = "invoice_no";
    private final String INVOICE_DATE   = "invoice_date";
    private final String CUSTOMER       = "customer";
    private final String PAID           = "paid";
    private final String AMOUNT         = "amount";
    private final String COMMENTS       = "comments";

    private String _invoice_no,_invoice_date,_customer, _comments;
    private int _paid;
    private double _amount;

    public SalesHeaderField(){};

    public void getRecord(Cursor cursor){
        this._invoice_no=cursor.getString(cursor.getColumnIndex(INVOICE_NO));
        this._invoice_date=cursor.getString(cursor.getColumnIndex(INVOICE_DATE));
        this._customer=cursor.getString(cursor.getColumnIndex(CUSTOMER));
        this._paid=cursor.getInt(cursor.getColumnIndex(PAID));
        this._amount=cursor.getDouble(cursor.getColumnIndex(AMOUNT));
        this._comments=cursor.getString(cursor.getColumnIndex(COMMENTS));
    };

    public SalesHeaderField(String invoice_no, String invoice_date, String customer,
                           int paid, double amount, String comments){
        this._invoice_no=invoice_no;
        this._invoice_date=invoice_date;
        this._customer=customer;
        this._paid=paid;
        this._amount=amount;
        this._comments=comments;
    };

}
