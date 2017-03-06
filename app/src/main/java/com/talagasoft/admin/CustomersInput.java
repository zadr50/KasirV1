package com.talagasoft.admin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.talagasoft.model.Recordset;
import com.talagasoft.kasir.R;

/**
 * Created by andri on 03/02/2017.
 */

public class CustomersInput extends AppCompatActivity {

    Recordset _item;
    String _mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer);

        Button cmdDel= (Button) findViewById(R.id.cmdDelete);
        cmdDel.setVisibility(View.INVISIBLE);
        cmdDel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                DeleteRecord();
            }
        });

        Button cmdSave=(Button) findViewById(R.id.cmdSave);
        cmdSave.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if(SaveRecord()) {
                    finish();
                }
            }
        });
        Button cmdClose=(Button) findViewById(R.id.cmdClose);
        cmdClose.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                finish();
            }
        });
        _mode="add";
    }

    private boolean SaveRecord() {
        EditText txtCustomerNo= (EditText) findViewById(R.id.txtCustomerNo);
        EditText txtCompany = (EditText) findViewById(R.id.txtCompany);
        EditText txtAddress = (EditText) findViewById(R.id.txtAddress);
        EditText txtPhone = (EditText) findViewById(R.id.txtPhone);
        EditText txtEmail = (EditText) findViewById(R.id.txtEmail);

        _item= new Recordset(getBaseContext());
        _item.OpenRecordset("select * from ksr_customers where customer_no='" + txtCustomerNo.getText().toString()+ "'",
                "ksr_customers","customer_no");
        if (_item.oef())  _item.addNew();
        _item.put("customer_no",txtCustomerNo.getText().toString());
        _item.put("company",txtCompany.getText().toString());
        _item.put("address",txtAddress.getText().toString());
        _item.put("phone",txtPhone.getText().toString());
        _item.put("email",txtEmail.getText().toString());
        int id= _item.save();
        return id>-1;
    }
    private void DeleteRecord(){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete this item?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText txtId = (EditText) findViewById(R.id.txtCustomerNo);
                        _item.delete(txtId.getText().toString());
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
