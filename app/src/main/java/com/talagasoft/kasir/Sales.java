package com.talagasoft.kasir;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.talagasoft.admin.WaitersList;
import com.talagasoft.model.Helper;
import com.talagasoft.model.Recordset;
import com.talagasoft.model.SqlAdapter;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Sales extends AppCompatActivity {

    ListView lstBarang;
    Button cmdItem,cmdCustomer,cmdCash,cmdCard,cmdTable,cmdWaiter,cmdExit;
    TextView txtAmount,txtQty,txtNota;
    float mTotalAmount,mTotalQty;
    String mNota;
    BaseAdapter mItemAdapter;
    private final int ITEM_SELECTED=2,CUSTOMER_SELECTED=3,TABLE_SELECTED=4,WAITER_SELECTED=5;
    private final int CASH_PAYMENT=6,CARD_PAYMENT=7;
    private final String STATUS="status", OK="OK";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        //-- init variables
        mNota="$$$";
        mTotalQty=0;
        mTotalAmount=0;

        //-- init controls
        lstBarang=(ListView) findViewById(R.id.lstBarang);
        cmdItem=(Button) findViewById(R.id.cmdItems);
        cmdItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction("com.talagasoft.model.ItemMasterSelect");
                startActivityForResult(i,ITEM_SELECTED);
            }
        });
        cmdCash = (Button) findViewById(R.id.cmdCash);
        cmdCash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction("com.talagasoft.model.SalesPaymentCash");
                startActivityForResult(i,CASH_PAYMENT);
            }
        });
        cmdCard = (Button) findViewById(R.id.cmdCard);
        cmdCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction("com.talagasoft.kasir.PayCard");
                startActivityForResult(i,CARD_PAYMENT);
            }
        });
        cmdCustomer = (Button) findViewById(R.id.cmdCustomer);
        cmdCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction("com.talagasoft.kasir.SelectCustomer");
                startActivity(i);

            }
        });
        cmdWaiter = (Button) findViewById(R.id.cmdWaiter);
        cmdWaiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction("com.talagasoft.kasir.SelectWaiter");
                startActivity(i);
            }
        });
        cmdTable = (Button) findViewById(R.id.cmdTable);
        cmdTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent();
                i.setAction("com.talagasoft.kasir.SelectTable");
                startActivity(i);
            }
        });
        cmdExit = (Button) findViewById(R.id.cmdExitSales);
        cmdExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        txtAmount = (TextView) findViewById(R.id.txtAmount);
        txtQty = (TextView) findViewById(R.id.txtQty);
        txtNota = (TextView) findViewById(R.id.txtNotaNo);
        txtNota.setText(mNota);

        //load item sales
        loadItemSale();
        calcTotal();

    }
    private void calcTotal(){
        Recordset rst=new Recordset(getBaseContext());
        rst.execute("select sum(amount) as zAmt, count(1) as zCnt " +
                "from ksr_sales_detail " +
                "where invoice_no='"+mNota+"'");
        mTotalAmount = rst.getFloat("zAmt");
        mTotalQty = rst.getInt("zCnt");
        DecimalFormat df = new DecimalFormat("###,###.##"); // or pattern "###,###.##$"
        txtAmount.setText(df.format(mTotalAmount));
        txtQty.setText("" + mTotalQty);
        Helper db=new Helper(getBaseContext());
        db.Execute("update ksr_sales_header set amount="+mTotalAmount+ " where invoice_no='"+mNota+"'");

   }
    private void loadItemSale(){
        int[] c = new int[]{R.id.nama_barang, R.id.barcode, R.id.harga};
        lstBarang.setAdapter(new SqlAdapter(getBaseContext(), R.layout.list_row, c,
                "select item_name,barcode,amount from ksr_sales_detail where invoice_no='" + mNota + "'")
        );
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==ITEM_SELECTED)
        {
            String sItemNo=data.getStringExtra("ItemNo");
            addItem(sItemNo);
        }
        if(requestCode==CASH_PAYMENT){
            String status=data.getStringExtra(STATUS);
            if(status.equals(OK)){
                finish();
            }
        }
    }

    private void addItem(String sItemNo){
        Recordset rItem=new Recordset(getBaseContext());
        rItem.OpenRecordset("select * from ksr_item_master where barcode='"+sItemNo+"'",
                "ksr_item_master","barcode");

        Recordset rSales=new Recordset(getBaseContext());
        rSales.OpenRecordset("select * from ksr_sales_detail where invoice_no='$$$'",
                "ksr_sales_detail","id");
        rSales.addNew();
        rSales.put("invoice_no",mNota);
        rSales.put("barcode", rItem.getString("barcode"));
        rSales.put("item_name",rItem.getString("item_name"));
        rSales.put("price",rItem.getString("price"));
        rSales.put("cost",rItem.getString("cost"));
        rSales.put("qty","1");
        rSales.put("amount",rItem.getString("price"));
        rSales.put("unit",rItem.getString("unit"));
        rSales.put("discount","0");
        rSales.save();
        loadItemSale();
    }

}
