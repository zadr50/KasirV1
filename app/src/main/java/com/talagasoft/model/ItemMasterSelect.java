package com.talagasoft.model;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.talagasoft.kasir.R;

/**
 * Created by andri on 03/02/2017.
 */

public class ItemMasterSelect extends AppCompatActivity {
    ListView lv1;
    private BaseAdapter mBaseAdapter = null;
    private int[] controls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list1);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setVisibility(View.GONE);
        FloatingActionButton fabSearch = (FloatingActionButton) findViewById(R.id.fabSearch);
        fabSearch.setVisibility(View.GONE);
        TextView txtTitle = (TextView) findViewById(R.id.txtTitle);
        txtTitle.setText("Select Item Master");
        lv1 = (ListView) findViewById(R.id.list1);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cursor cursor = (Cursor) mBaseAdapter.getItem(i);
                String sItemNo= String.valueOf(cursor.getString(cursor.getColumnIndex("barcode")));
                Intent intent=new Intent();
                intent.putExtra("ItemNo",sItemNo);
                setResult(2,intent);
                finish();
            }
        });
        loadData();
    }

    private void loadData() {
        controls = new int[]{R.id.nama_barang, R.id.barcode, R.id.harga};
        mBaseAdapter = new SqlAdapter(getBaseContext(), R.layout.list_row,
                controls, "select item_name,barcode,category from ksr_item_master");
        lv1.setAdapter(mBaseAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }



}