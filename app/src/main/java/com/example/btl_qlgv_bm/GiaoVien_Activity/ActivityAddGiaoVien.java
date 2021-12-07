
package com.example.btl_qlgv_bm.GiaoVien_Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import com.example.btl_qlgv_bm.ActivityGiaoVien;
import com.example.btl_qlgv_bm.Database;
import com.example.btl_qlgv_bm.R;

public class ActivityAddGiaoVien extends AppCompatActivity {

    final String DATABASE_NAME = "DaoTaoDB.s3db";
    Button btnLuu, btnHuy;
    EditText addIdGV, addTenGV, add_id_bo_mon, add_hoc_ham, add_hoc_vi, add_email, add_sdt1, add_sdt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_giao_vien);

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
        addIdGV = (EditText) findViewById(R.id.addMaGV);
        addTenGV = (EditText) findViewById(R.id.addTenGV);
        add_id_bo_mon = (EditText) findViewById(R.id.add_id_bo_mon);
        add_hoc_ham = (EditText) findViewById(R.id.add_hoc_ham);
        add_hoc_vi = (EditText) findViewById(R.id.add_hoc_vi);
        add_email = (EditText) findViewById(R.id.add_email);
        add_sdt1 = (EditText) findViewById(R.id.add_sdt1);
        add_sdt2 = (EditText) findViewById(R.id.add_sdt2);

    }

    private void insert()
    {
        String id = addIdGV.getText().toString();
        String ten = addTenGV.getText().toString();
        String id_bo_mon = add_id_bo_mon.getText().toString();
        String hoc_ham = add_hoc_ham.getText().toString();
        String hoc_vi = add_hoc_vi.getText().toString();
        String email = add_email.getText().toString();
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
        database.insert("GiaoVienTab",null, contentValues);
        Intent intent = new Intent(this, ActivityGiaoVien.class);
        startActivity(intent);

    }

    private void cancel(){
        Intent intent = new Intent(this, ActivityGiaoVien.class);
        startActivity(intent);
    }
}


