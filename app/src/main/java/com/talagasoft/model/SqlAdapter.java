package com.talagasoft.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.talagasoft.kasir.R;

import java.util.ArrayList;

import static com.talagasoft.model.Global.NAMA_DB;
import static com.talagasoft.model.Global.VERSI_DB;

/**
 * Created by andri on 03/02/2017.
 */

public class SqlAdapter extends BaseAdapter {
    private SQLiteDatabase _db;
    private LayoutInflater _layoutInflater;
    private String[] _fields;
    private Cursor _cursor;
    private Context _context;
    private ArrayList<TextView> _arTextView=null;
    private int _row_layout;
    private ArrayList<Object> _data=null;
    private int[] _controls;

    public SqlAdapter(Context aContext, int layout, int[] controls, String sql) {
        _context=aContext;
        _layoutInflater = LayoutInflater.from(_context);
        _row_layout=layout;
        _controls=controls;
        Helper dbHelper = new Helper(_context, NAMA_DB, null, VERSI_DB);
        try {
            _db = dbHelper.getWritableDatabase();
        }
        catch (SQLiteException e) {
            _db = dbHelper.getReadableDatabase();
        }
        OpenRecordset(sql);
    }
    private boolean OpenRecordset(String sql){
        _cursor = _db.rawQuery(sql,null);
        _fields=_cursor.getColumnNames();
        if(_fields == null) return false;

        return true;
    }


    @Override
    public int getCount() {
        return _cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        _cursor.moveToPosition(position);
        return _cursor;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder=new ViewHolder();
        if (convertView == null) {
            convertView = _layoutInflater.inflate(_row_layout, null);
            for(int i=0;i<_controls.length;i++){
                TextView id=(TextView) convertView.findViewById(_controls[i]);
                holder.add(id);
            }
            holder.img = (ImageView) convertView.findViewById(R.id.gambar);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        _cursor.moveToPosition(position);
        String fieldName="";
        for(int i=0;i<_fields.length;i++){
            fieldName=_fields[i];
            holder.setText(fieldName);
        }
        holder.img.setImageResource(R.drawable.nopic);
        return convertView;
    }

    class ViewHolder {
        private ArrayList<TextView> txt=new ArrayList<>();
        private void add(TextView id){
            txt.add( id);
        }
        private void setText(String fieldName){
            int idx=_cursor.getColumnIndex(fieldName);
            String value=_cursor.getString(idx);
            txt.get(idx).setText(value);
        }
        ImageView img;
    }
}