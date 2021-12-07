package com.example.btl_qlgv_bm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.btl_qlgv_bm.BoMon_Activity.ActivityAddBoMon;
import com.example.btl_qlgv_bm.Adapter.AdapterBoMon;

import java.util.ArrayList;

public class ActivityBoMon extends AppCompatActivity {
    final String DATABASE_NAME = "DaoTaoDB.s3db";
    SQLiteDatabase database;

    ListView listView;
    ArrayList<BoMon>list;
    AdapterBoMon adapter;
    Button btnThem, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bomon);

        addControls();
        back();
        readData();

    }
    private void back() {
        btnBack = (Button) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( ActivityBoMon.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        btnThem = (Button) findViewById(R.id.btnThem);
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityBoMon.this, ActivityAddBoMon.class);
                startActivity(intent);
            }
        });

        listView = (ListView) findViewById(R.id.listView);
        list = new ArrayList<>();
        adapter = new AdapterBoMon(this, list);
        listView.setAdapter(adapter);
    }


    private void readData(){
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM BoMonTab", null);
        list.clear();
        for(int i = 0; i< cursor.getCount(); i++)
        {
            cursor.moveToPosition(i);
            String id = cursor.getString(0);
            String ten = cursor.getString(1);
            String id_khoa = cursor.getString(2);
            String mota = cursor.getString(3);
            list.add(new BoMon(id,ten,id_khoa,mota));
        }
        adapter.notifyDataSetChanged();
    }

}