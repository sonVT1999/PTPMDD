package com.example.btl_qlgv_bm.BoMon_Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_qlgv_bm.ActivityBoMon;
import com.example.btl_qlgv_bm.Database;
import com.example.btl_qlgv_bm.R;

public class ActivityAddBoMon extends AppCompatActivity {

    final String DATABASE_NAME = "DaoTaoDB.s3db";
    Button btnLuu, btnHuy;
    EditText addId, addTen, add_id_khoa, add_mo_ta;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bo_mon);

        addControls();

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert();
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
        addId = (EditText) findViewById(R.id.addMa);
        addTen = (EditText) findViewById(R.id.addTen);
        add_id_khoa = (EditText) findViewById(R.id.add_id_bo_mon);
        add_mo_ta = (EditText) findViewById(R.id.add_mo_ta);
    }

    private void insert()
    {
        String id = addId.getText().toString();
        String ten = addTen.getText().toString();
        String id_khoa = add_id_khoa.getText().toString();
        String mo_ta = add_mo_ta.getText().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put("maBoMon", id);
        contentValues.put("tenBoMon", ten);
        contentValues.put("maKhoa", id_khoa);
        contentValues.put("moTa", mo_ta);

        SQLiteDatabase database = Database.initDatabase(this, "DaoTaoDB.s3db");
        database.insert("BoMonTab",null, contentValues);
        Intent intent = new Intent(this, ActivityBoMon.class);
        startActivity(intent);

    }

    private void cancel(){
        Intent intent = new Intent(this, ActivityBoMon.class);
        startActivity(intent);
    }
}