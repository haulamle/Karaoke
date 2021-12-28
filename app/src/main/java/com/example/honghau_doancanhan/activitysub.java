package com.example.honghau_doancanhan;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class activitysub extends AppCompatActivity{
    TextView txtmaso,txtbaihat,txtloibaihat,txttacgia;
    ImageButton btnthich,btnkhongthich;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subactivity);
        txtmaso =(TextView)findViewById(R.id.txtMaso);
        txtbaihat =(TextView)findViewById(R.id.txtBaiHat);
        txtloibaihat =(TextView) findViewById(R.id.txtLoiBaiHat);
        txttacgia =(TextView)findViewById(R.id.txtTacGia);
        btnthich =(ImageButton)findViewById(R.id.btnThich);
        btnkhongthich = (ImageButton) findViewById(R.id.btnKhongThich);
        Intent callerIntent1 = getIntent();
        Bundle backagecaller1 = callerIntent1.getBundleExtra("package");
        String maso = backagecaller1.getString("maso");
        Cursor c = MainActivity.database.rawQuery("SELECT * FROM ArirangSongList WHERE MABH LIKE "+maso+ " ", null);
        txtmaso.setText(maso);
        c.moveToFirst();
        txtbaihat.setText(c.getString(2));
        txtloibaihat.setText(c.getString(3));
        txttacgia.setText(c.getString(4));
        if (c.getInt(6)==0) {
            btnthich.setVisibility(View.INVISIBLE);
            btnkhongthich.setVisibility(View.VISIBLE);
        } else {
            btnkhongthich.setVisibility(View.INVISIBLE);
            btnthich.setVisibility(View.VISIBLE);
        }
        c.close();
        btnthich.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH", 0);
                MainActivity.database.update("ArirangSongList", values, "MABH=?", new String[]{txtmaso.getText().toString()});
                btnthich.setVisibility(View.INVISIBLE);
                btnkhongthich.setVisibility(View.VISIBLE);
            }
        });
        btnkhongthich.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("YEUTHICH", 1);
                MainActivity.database.update("ArirangSongList", values, "MABH=?", new String[]{txtmaso.getText().toString()});
                btnkhongthich.setVisibility(View.INVISIBLE);
                btnthich.setVisibility(View.VISIBLE);
            }
        });
    }
}
