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

public class CategoryInput  extends AppCompatActivity {

    Recordset _item;
    String _mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category);

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
        EditText category= (EditText) findViewById(R.id.txtCategory);
        EditText description = (EditText) findViewById(R.id.txtDescription);
        EditText picture = (EditText) findViewById(R.id.txtPicture);

        _item= new Recordset(getBaseContext());
        _item.OpenRecordset("select * from ksr_category where category='" + category.getText().toString()+ "'",
                "ksr_category","category");
        if (_item.oef())  _item.addNew();
        _item.put("category",category.getText().toString());
        _item.put("description",description.getText().toString());
        _item.put("picture",picture.getText().toString());
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
                        EditText category = (EditText) findViewById(R.id.txtCategory);
                        _item.delete(category.getText().toString());
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
