
package com.example.btl_qlgv_bm.GiaoVien_Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_qlgv_bm.ActivityGiaoVien;
import com.example.btl_qlgv_bm.BoMon;
import com.example.btl_qlgv_bm.Database;
import com.example.btl_qlgv_bm.GiaoVien;
import com.example.btl_qlgv_bm.R;

import java.util.ArrayList;
import java.util.List;

public class ActivityAddGiaoVien extends AppCompatActivity {

    final String DATABASE_NAME = "DaoTaoDB.s3db";
    Button btnLuu, btnHuy;
    EditText addIdGV, addTenGV, add_hoc_ham, add_hoc_vi, add_email, add_sdt1, add_sdt2;
    Spinner spn_id_bomon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_giao_vien);

        addControls();

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (addIdGV .length() == 0 || addIdGV .equals("") || addIdGV == null){
                    Toast.makeText(ActivityAddGiaoVien.this,"Mã giáo viên không được để trống!",Toast.LENGTH_LONG).show();
                }else if (addTenGV .length() == 0 || addTenGV .equals("") || addTenGV == null) {
                    Toast.makeText(ActivityAddGiaoVien.this, "Tên giáo viên không được để trống!", Toast.LENGTH_LONG).show();
                } else {
                    insert();
                }

            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }

    private void addControls()
    {
        btnLuu = (Button) findViewById(R.id.btn_luu);
        btnHuy = (Button) findViewById(R.id.btn_huy);
        addIdGV = (EditText) findViewById(R.id.addMaGV);
        addTenGV = (EditText) findViewById(R.id.addTenGV);
        spn_id_bomon = (Spinner) findViewById(R.id.spn_id_bomon);
        add_hoc_ham = (EditText) findViewById(R.id.add_hoc_ham);
        add_hoc_vi = (EditText) findViewById(R.id.add_hoc_vi);
        add_email = (EditText) findViewById(R.id.add_email);
        add_sdt1 = (EditText) findViewById(R.id.add_sdt1);
        add_sdt2 = (EditText) findViewById(R.id.add_sdt2);


        ArrayAdapter<BoMon> adapter = new ArrayAdapter<BoMon>(this,
                android.R.layout.simple_spinner_item,
                getDataAllBoMon());

        spn_id_bomon.setAdapter(adapter);
    }

    private List<BoMon> getDataAllBoMon() {
        List<BoMon> boMons = new ArrayList<>();
        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM BoMonTab", null);

        for(int i = 0; i< cursor.getCount(); i++)
        {
            cursor.moveToPosition(i);
            String id = cursor.getString(0);
            String ten = cursor.getString(1);
            String id_khoa = cursor.getString(2);
            String mota = cursor.getString(3);
            boMons.add(new BoMon(id, ten, id_khoa, mota));
        }
        return boMons;
    }


    private void insert()
    {
        String id = addIdGV.getText().toString();
        if (checkIdGiaoVien(id)) {
            Toast.makeText(this, "Nhập lại mã giáo viên", Toast.LENGTH_SHORT).show();
        } else {
            String ten = addTenGV.getText().toString(); // sao no do the kia

            String hoc_ham = add_hoc_ham.getText().toString();
            String hoc_vi = add_hoc_vi.getText().toString();
            String email = add_email.getText().toString();
            BoMon boMon = (BoMon) spn_id_bomon.getSelectedItem();
            String id_bo_mon = boMon.maBoMon;
            String sdt1 = add_sdt1.getText().toString();
            String sdt2 = add_sdt2.getText().toString();


            ContentValues contentValues = new ContentValues();
            contentValues.put("maGiaoVien", id);
            contentValues.put("tenGiaoVien", ten);
            contentValues.put("maBoMon", id_bo_mon);
            contentValues.put("hocHam", hoc_ham);
            contentValues.put("hocVi", hoc_vi);
            contentValues.put("email", email);
            contentValues.put("soDienThoai1", sdt1);
            contentValues.put("soDienThoai2", sdt2);

            SQLiteDatabase database = Database.initDatabase(this, "DaoTaoDB.s3db");
            database.insert("GiaoVienTab", null, contentValues);
            Intent intent = new Intent(this, ActivityGiaoVien.class);
            startActivity(intent);
        }

    }

    private boolean checkIdGiaoVien(String id) {
        if(TextUtils.isEmpty(id)) {
            return true;
        }
        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM GiaoVienTab", null);
        for(int i = 0; i< cursor.getCount(); i++)
        {
            cursor.moveToPosition(i);
            String maGiaoVien = cursor.getString(0);
            if(maGiaoVien.equals(id)) {
                return true;
            }
        }
        return false;
    }

    private void cancel(){
        Intent intent = new Intent(this, ActivityGiaoVien.class);
        startActivity(intent);
    }
}


