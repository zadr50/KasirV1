package com.talagasoft.kasir;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.talagasoft.admin.CategoryList;
import com.talagasoft.admin.CustomersList;
import com.talagasoft.admin.ItemMasterList;
import com.talagasoft.admin.SuppliersList;
import com.talagasoft.admin.TablesList;
import com.talagasoft.admin.WaitersList;


/**
 * Created by compaq on 02/29/2016.
 */
public class MenuUtama extends AppCompatActivity {
    Button btnExit,btnHelp,btnNewOrder,btnItemMaster,btnCategory;
    Button btnCustomer,btnSupplier,btnPending;
    Button btnReport,btnSetting,btnWaiter,btnTable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_utama);

        btnExit=(Button) findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                cmdExit();
            }
        });

        btnHelp=(Button) findViewById(R.id.btnHelp);
        btnHelp.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent1 = new Intent(getBaseContext(), Help.class );
                startActivity(myIntent1);
            }
        });
        btnItemMaster=(Button) findViewById(R.id.btnItemMaster);
        btnItemMaster.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent3 = new Intent(getBaseContext(), ItemMasterList.class );
                startActivity(myIntent3);
            }
        });

        btnNewOrder=(Button) findViewById(R.id.btnNewOrder);
        btnNewOrder.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent2 = new Intent(getBaseContext(), Sales.class );
                startActivity(myIntent2);
            }
        });


        btnCategory=(Button) findViewById(R.id.btnCategory);
        btnCategory.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent4 = new Intent(getBaseContext(), CategoryList.class );
                startActivity(myIntent4);
            }
        });
        btnCustomer=(Button) findViewById(R.id.btnCustomer);
        btnCustomer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent5 = new Intent(getBaseContext(), CustomersList.class);
                startActivity(myIntent5);
            }
        });

        btnPending=(Button) findViewById(R.id.btnPendingOrder);
        btnPending.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent6 = new Intent(getBaseContext(), PendingOrder.class );
                startActivity(myIntent6);
            }
        });
        btnReport=(Button) findViewById(R.id.btnReports);
        btnReport.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent7 = new Intent(getBaseContext(), Reports.class );
                startActivity(myIntent7);
            }
        });
        btnSetting=(Button) findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent8 = new Intent(getBaseContext(), Setting.class );
                startActivity(myIntent8);
            }
        });
        btnSupplier=(Button) findViewById(R.id.btnSupplier);
        btnSupplier.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent9 = new Intent(getBaseContext(), SuppliersList.class );
                startActivity(myIntent9);
            }
        });
        btnWaiter=(Button) findViewById(R.id.btnWaiters);
        btnWaiter.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent10 = new Intent(getBaseContext(), WaitersList.class );
                startActivity(myIntent10);
            }
        });
        btnTable=(Button) findViewById(R.id.btnTable);
        btnTable.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent myIntent11 = new Intent(getBaseContext(), TablesList.class );
                startActivity(myIntent11);
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void cmdExit(){
        finish();
    }
}
