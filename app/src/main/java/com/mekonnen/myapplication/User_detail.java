package com.mekonnen.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
public class User_detail extends AppCompatActivity {
    TextView user_detail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail);
        user_detail=findViewById(R.id.id_userdetail);
        Intent user_detailIntent=getIntent();
        String fulname=user_detailIntent.getStringExtra("fulname");
        String username=user_detailIntent.getStringExtra("username");
        String email=user_detailIntent.getStringExtra("email");
        String password=user_detailIntent.getStringExtra("password");
        String phone=user_detailIntent.getStringExtra("phone");
        String gender=user_detailIntent.getStringExtra("gender");
        ActionBar actionBar=getSupportActionBar();
        actionBar.setTitle(fulname+"'s  Detail");
        user_detail.setText("FulName:"+fulname+"\n"+
                "UserName:"+username+"\n"+"Email:"+email+"\n"+
                "Password:"+password+"\n"+"Phone:"+phone+"\n"+
                "Gender:"+gender);
    }
}
