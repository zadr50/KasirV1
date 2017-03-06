package com.talagasoft.admin;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.talagasoft.model.SqlAdapter;
import com.talagasoft.kasir.R;

/**
 * Created by compaq on 03/20/2016.
 */
public class TablesList extends AppCompatActivity {
    ListView lv1;
    private BaseAdapter mBaseAdapter=null;
    private int[] controls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list1);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent4 = new Intent();
                myIntent4.setAction("com.talagasoft.admin.TablesInput");
                startActivity(myIntent4);
            }
        });
        FloatingActionButton fabSearch = (FloatingActionButton) findViewById(R.id.fabSearch);
        fabSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
            }
        });
        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText("Tables Number");

        lv1 = (ListView) findViewById(R.id.list1);
        loadData();
    }
    private void loadData(){
        controls= new int[]{R.id.nama_barang, R.id.barcode, R.id.harga};
        mBaseAdapter=new SqlAdapter(getBaseContext(), R.layout.list_row,
                controls, "select * from ksr_tables");
        lv1.setAdapter(mBaseAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}