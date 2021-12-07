package com.example.btl_qlgv_bm.GiaoVien_Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_qlgv_bm.ActivityBoMon;
import com.example.btl_qlgv_bm.ActivityGiaoVien;
import com.example.btl_qlgv_bm.Database;
import com.example.btl_qlgv_bm.R;

public class ActivityUpdateGiaoVien extends AppCompatActivity {

    final String DATABASE_NAME = "DaoTaoDB.s3db";
    Button btnLuu, btnHuy;
    EditText editTenGV, edit_id_bo_mon, edit_hoc_ham, edit_hoc_vi, edit_email, edit_sdt1, edit_sdt2;
    String id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_giao_vien);

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
        id = intent.getStringExtra("maGiaoVien");
        SQLiteDatabase database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM GiaoVienTab Where maGiaoVien = ? ",new String[]{id + ""});
        cursor.moveToFirst();
        String ten = cursor.getString(1);
        String id_bo_mon = cursor.getString(2);
        String hoc_ham = cursor.getString(3);
        String hoc_vi = cursor.getString(4);
        String email = cursor.getString(5);
        String sdt1 = cursor.getString(6);
        String sdt2 = cursor.getString(7);


        editTenGV.setText(ten);
        edit_id_bo_mon.setText(id_bo_mon);
        edit_hoc_ham.setText(hoc_ham);
        edit_hoc_vi.setText(hoc_vi);
        edit_email.setText(email);
        edit_sdt1.setText(sdt1);
        edit_sdt2.setText(sdt2);
    }

    private void addControls()
    {
        btnLuu = (Button) findViewById(R.id.btn_luu);
        btnHuy = (Button) findViewById(R.id.btn_huy);
        editTenGV = (EditText) findViewById(R.id.editTenGV);
        edit_id_bo_mon = (EditText) findViewById(R.id.edit_id_bo_mon);
        edit_hoc_ham = (EditText) findViewById(R.id.edit_hoc_ham);
        edit_hoc_vi = (EditText) findViewById(R.id.edit_hoc_vi);
        edit_email = (EditText) findViewById(R.id.edit_email);
        edit_sdt1 = (EditText) findViewById(R.id.edit_sdt1);
        edit_sdt2 = (EditText) findViewById(R.id.edit_sdt2);
    }

    private void update()
    {
        String ten = editTenGV.getText().toString();
        String id_bo_mon = edit_id_bo_mon.getText().toString();
        String hoc_ham = edit_hoc_ham.getText().toString();
        String hoc_vi = edit_hoc_vi.getText().toString();
        String email = edit_email.getText().toString();
        String sdt1 = edit_sdt1.getText().toString();
        String sdt2 = edit_sdt2.getText().toString();

        ContentValues contentValues = new ContentValues();
        contentValues.put("tenGiaoVien", ten);
        contentValues.put("maBoMon", id_bo_mon);
        contentValues.put("hocHam", hoc_ham);
        contentValues.put("hocVi", hoc_vi);
        contentValues.put("email", email);
        contentValues.put("soDienThoai1", sdt1);
        contentValues.put("soDienThoai2", sdt2);

        SQLiteDatabase database = Database.initDatabase(this, "DaoTaoDB.s3db");
        database.update("GiaoVienTab", contentValues, "maGiaoVien = ?", new String[]{id + ""});
        Intent intent = new Intent(this, ActivityGiaoVien.class);
        startActivity(intent);

    }

    private void cancel(){
        Intent intent = new Intent(this, ActivityGiaoVien.class);
        startActivity(intent);
    }
}