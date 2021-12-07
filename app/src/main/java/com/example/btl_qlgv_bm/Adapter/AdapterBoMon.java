package com.example.btl_qlgv_bm.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.btl_qlgv_bm.BoMon_Activity.ActivityUpdateBoMon;
import com.example.btl_qlgv_bm.BoMon;
import com.example.btl_qlgv_bm.Database;
import com.example.btl_qlgv_bm.R;

import java.util.ArrayList;

public class AdapterBoMon extends BaseAdapter {

    Activity context;
    ArrayList<BoMon>list;

    public AdapterBoMon(Activity context ,ArrayList<BoMon>list )
    {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.listview_bomon, null);

        TextView txt_id = (TextView) row.findViewById(R.id.txt_id);
        TextView txt_ten = (TextView) row.findViewById(R.id.txt_ten);
        TextView txt_id_khoa = (TextView) row.findViewById(R.id.txt_idKhoa);
        TextView txt_mota = (TextView) row.findViewById(R.id.txt_mota);
        Button btn_Sua = (Button) row.findViewById(R.id.btn_sua);
        Button btn_Xoa = (Button) row.findViewById(R.id.btn_xoa);

        BoMon boMon = list.get(position);
        txt_id.setText(boMon.maBoMon + "");
        txt_ten.setText(boMon.tenBoMon);
        txt_id_khoa.setText(boMon.makhoa);
        txt_mota.setText(boMon.moTa);

        btn_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityUpdateBoMon.class);
                intent.putExtra("maBoMon", boMon.maBoMon);
                context.startActivity(intent);
            }
        });

        btn_Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa bộ môn này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        delete(boMon.maBoMon);
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        return  row;
    }

    private void delete(String maBoMon){
        SQLiteDatabase database = Database.initDatabase(context, "DaoTaoDB.s3db");
        database.delete("BoMonTab", "maBoMon = ?", new String[]{maBoMon + ""});
        list.clear();
        Cursor cursor = database.rawQuery("Select * from BoMonTab", null);
        while (cursor.moveToNext())
        {
            String id = cursor.getString(0);
            String ten = cursor.getString(1);
            String id_khoa = cursor.getString(2);
            String mo_ta = cursor.getString(3);

            list.add(new BoMon(id, ten, id_khoa, mo_ta));
        }
        notifyDataSetChanged();
    }
}
