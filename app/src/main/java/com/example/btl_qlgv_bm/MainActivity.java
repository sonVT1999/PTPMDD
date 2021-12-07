package com.example.btl_qlgv_bm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    final String DATABASE_NAME = "DaoTaoDB.s3db";
    SQLiteDatabase database;

    Button btnQLyBoMon, btnQLyGiaoVien, btnExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
    }

    private void addControls() {
        btnQLyBoMon = (Button) findViewById(R.id.btnQLyBoMon);
        btnQLyBoMon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityBoMon.class);
                startActivity(intent);
            }
        });
        btnQLyGiaoVien = (Button) findViewById(R.id.btnQLyGiaoVien);
        btnQLyGiaoVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityGiaoVien.class);
                startActivity(intent);
            }
        });
        btnExit = (Button) findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
                System.exit(0);
            }
        });
    }
}