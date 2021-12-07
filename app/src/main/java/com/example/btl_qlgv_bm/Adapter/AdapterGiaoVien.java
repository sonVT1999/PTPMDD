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

import com.example.btl_qlgv_bm.GiaoVien;
import com.example.btl_qlgv_bm.Database;
import com.example.btl_qlgv_bm.GiaoVien_Activity.ActivityUpdateGiaoVien;
import com.example.btl_qlgv_bm.R;

import java.util.ArrayList;

public class AdapterGiaoVien extends BaseAdapter {

    Activity context;
    ArrayList<GiaoVien>list;

    public AdapterGiaoVien(Activity context, ArrayList<GiaoVien>list)
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
        View row = inflater.inflate(R.layout.listview_giaovien, null);

        TextView txt_id = (TextView) row.findViewById(R.id.txt_id);
        TextView txt_ten = (TextView) row.findViewById(R.id.txt_ten);
        TextView txt_id_bo_mon = (TextView) row.findViewById(R.id.txt_idBoMon);
        TextView txt_hoc_ham = (TextView) row.findViewById(R.id.txt_hoc_ham);
        TextView txt_hoc_vi = (TextView) row.findViewById(R.id.txt_hoc_vi);
        TextView txt_email = (TextView) row.findViewById(R.id.txt_email);
        TextView txt_sdt1 = (TextView) row.findViewById(R.id.txt_sdt1);
        TextView txt_sdt2 = (TextView) row.findViewById(R.id.txt_sdt2);
        Button btn_Sua = (Button) row.findViewById(R.id.btnSua);
        Button btn_Xoa = (Button) row.findViewById(R.id.btnXoa);

        GiaoVien giaoVien = list.get(position);
        txt_id.setText(giaoVien.maGiaoVien + "");
        txt_ten.setText(giaoVien.tenGiaoVien);
        txt_id_bo_mon.setText(giaoVien.maBoMon);
        txt_hoc_ham.setText(giaoVien.hocHam);
        txt_hoc_vi.setText(giaoVien.hocVi);
        txt_email.setText(giaoVien.email);
        txt_sdt1.setText(giaoVien.soDienThoai1);
        txt_sdt2.setText(giaoVien.soDienThoai2);

        btn_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ActivityUpdateGiaoVien.class);
                intent.putExtra("maGiaoVien", giaoVien.maGiaoVien);
                context.startActivity(intent);
            }
        });

        btn_Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa giáo viên này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        delete(giaoVien.maGiaoVien);
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

    private void delete(String maGiaoVien){
        SQLiteDatabase database = Database.initDatabase(context, "DaoTaoDB.s3db");
        database.delete("GiaoVienTab", "maGiaoVien = ?", new String[]{maGiaoVien + ""});
        list.clear();
        Cursor cursor = database.rawQuery("Select * from GiaoVienTab", null);
        while (cursor.moveToNext())
        {
            String id = cursor.getString(0);
            String ten = cursor.getString(1);
            String id_bo_mon = cursor.getString(2);
            String hoc_ham = cursor.getString(3);
            String hoc_vi = cursor.getString(4);
            String email = cursor.getString(5);
            String sdt1 = cursor.getString(6);
            String sdt2 = cursor.getString(7);

            list.add(new GiaoVien(id, ten, id_bo_mon, hoc_ham, hoc_vi, email, sdt1, sdt2));
        }
        notifyDataSetChanged();
    }
}

