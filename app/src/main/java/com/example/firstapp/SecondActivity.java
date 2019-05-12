package com.example.firstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    public TextView tv1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tv1 = findViewById(R.id.tv1);

        Intent intent = getIntent();
        ArrayList<String> ls = intent.getStringArrayListExtra("ls");

        String sres = "";
        for(String s: ls) {
            sres += s;
        }
        tv1.setText(sres);
    }
}
