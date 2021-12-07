package com.example.btl_qlgv_bm.BoMon_Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.btl_qlgv_bm.ActivityBoMon;
import com.example.btl_qlgv_bm.Database;
import com.example.btl_qlgv_bm.R;

public class ActivityUpdateBoMon extends AppCompatActivity {

    final String DATABASE_NAME = "DaoTaoDB.s3db";
    Button btnLuu, btnHuy;
    EditText updateTen, update_id_khoa, update_mo_ta;
    String id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bo_mon);

        addControls();
        initUI();

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancel();
            }
        });
    }

    private void initUI() {
        Intent intent = getIntent();
        id = intent.getStringExtra("maBoMon");
        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM BoMonTab Where maBoMon = ? ",new String[]{id + ""});
        cursor.moveToFirst();
        String ten = cursor.getString(1);
        String id_khoa = cursor.getString(2);
        String mo_ta = cursor.getString(3);

        updateTen.setText(ten);
        update_id_khoa.setText(id_khoa);
        update_mo_ta.setText(mo_ta);
    }

    private void addControls()
    {
        btnLuu = (Button) findViewById(R.id.btn_luu);
        btnHuy = (Button) findViewById(R.id.btn_huy);
        updateTen = (EditText) findViewById(R.id.updateTen);
        update_id_khoa = (EditText) findViewById(R.id.update_id_khoa);
        update_mo_ta = (EditText) findViewById(R.id.update_mota);
    }

    private void update()
    {
        String ten = updateTen.getText().toString();
        String id_khoa = update_id_khoa.getText().toString();
        String mo_ta = update_mo_ta.getText().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put("tenBoMon", ten);
        contentValues.put("maKhoa", id_khoa);
        contentValues.put("moTa", mo_ta);

        SQLiteDatabase database = Database.initDatabase(this, "DaoTaoDB.s3db");
        database.update("BoMonTab", contentValues, "maBoMon = ?", new String[]{id + ""});
        Intent intent = new Intent(this, ActivityBoMon.class);
        startActivity(intent);

    }

    private void cancel(){
        Intent intent = new Intent(this, ActivityBoMon.class);
        startActivity(intent);
    }
}