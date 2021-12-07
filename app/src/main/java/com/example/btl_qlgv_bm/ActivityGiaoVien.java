package com.example.btl_qlgv_bm;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_qlgv_bm.Adapter.AdapterBoMon;
import com.example.btl_qlgv_bm.Adapter.AdapterGiaoVien;
import com.example.btl_qlgv_bm.BoMon_Activity.ActivityAddBoMon;
import com.example.btl_qlgv_bm.GiaoVien_Activity.ActivityAddGiaoVien;

import java.util.ArrayList;

public class ActivityGiaoVien extends AppCompatActivity {
    final String DATABASE_NAME = "DaoTaoDB.s3db";
    SQLiteDatabase database;

    ListView listViewGV;
    ArrayList<GiaoVien>listGV;
    AdapterGiaoVien adapter;
    Button btnThem, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giaovien);

        addControls();
        back();
        readData();
    }

    private void back() {
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ActivityGiaoVien.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        btnThem = (Button) findViewById(R.id.btnThem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityGiaoVien.this, ActivityAddGiaoVien.class);
                startActivity(intent);
            }
        });
        listViewGV = (ListView) findViewById(R.id.listViewGV);
        listGV = new ArrayList<>();
        adapter = new AdapterGiaoVien(this, listGV);
        listViewGV.setAdapter(adapter);
    }


    private void readData(){
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM GiaoVienTab", null);
        listGV.clear();
        for(int i = 0; i< cursor.getCount(); i++)
        {
            cursor.moveToPosition(i);
            String id = cursor.getString(0);
            String ten = cursor.getString(1);
            String id_bo_mon = cursor.getString(2);
            String hoc_ham = cursor.getString(3);
            String hoc_vi = cursor.getString(4);
            String email = cursor.getString(5);
            String sdt1 = cursor.getString(6);
            String sdt2 = cursor.getString(7);
            listGV.add(new GiaoVien(id, ten, id_bo_mon, hoc_ham, hoc_vi, email, sdt1, sdt2));
        }
        adapter.notifyDataSetChanged();
    }
}