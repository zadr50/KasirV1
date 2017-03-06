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
 * Created by compaq on 03/25/2016.
 */
public class ItemMasterInput extends AppCompatActivity {

    Recordset _item;
    String _mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_master);

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
        EditText txtBarcode= (EditText) findViewById(R.id.txtBarcode);
        EditText txtItemName = (EditText) findViewById(R.id.txtItemName);
        EditText txtUnit = (EditText) findViewById(R.id.txtUnit);
        EditText txtPicture = (EditText) findViewById(R.id.txtIcon);
        EditText txtSupplier = (EditText) findViewById(R.id.txtSupplier);
        EditText txtCategory = (EditText) findViewById(R.id.txtCategory);
        EditText txtPrice = (EditText) findViewById(R.id.txtSales);
        EditText txtCost = (EditText) findViewById(R.id.txtPurchase);

        _item= new Recordset(getBaseContext());
        _item.OpenRecordset("select * from ksr_item_master where barcode='" + txtBarcode.getText().toString()+ "'",
                "ksr_item_master","barcode");
        if (_item.oef())  _item.addNew();
        _item.put("barcode",txtBarcode.getText().toString());
        _item.put("item_name",txtItemName.getText().toString());
        _item.put("unit",txtUnit.getText().toString());
        _item.put("price",txtPrice.getText().toString());
        _item.put("cost",txtCost.getText().toString());
        _item.put("picture",txtPicture.getText().toString());
        _item.put("supplier",txtSupplier.getText().toString());
        _item.put("category",txtCategory.getText().toString());
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
                        EditText txtId = (EditText) findViewById(R.id.txtBarcode);
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
