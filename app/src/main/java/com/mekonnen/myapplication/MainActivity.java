package com.mekonnen.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView welcometv, registertv;
    private EditText username, password;
    private Button login, register;
    private UserInfoDatabas userInfoDatabas;
    SQLiteDatabase userSqlDb;
    private UserSession loginSession;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.UserNameLogIn);
        password = findViewById(R.id.PasswordLogIn);
        registertv = findViewById(R.id.regyethometxtview);
        login = findViewById(R.id.logIN1);
        register = findViewById(R.id.signUpBtnhome);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        loginSession=new UserSession(this);
        if(loginSession.userLoggedIn()){
            startActivity(new Intent(getApplicationContext(),user_page.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logIN1:
                String getUsername = username.getText().toString().trim();
                String getPassword = password.getText().toString().trim();
                userInfoDatabas = new UserInfoDatabas(this);
                if (getUsername.isEmpty()){
                    username.setError("please enter username!");
                    username.requestFocus();
                } else if(getPassword.isEmpty()){
                    password.setError("please enter password!");
                }
                Boolean getResult=userInfoDatabas.checkUserLogin(getUsername, getPassword);
                if(getResult==true){
                    SharedPreferences sharedPreferences=this.getSharedPreferences("userApp",MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("username", getUsername);
                    editor.putString("password",getPassword);
                    editor.commit();
                    loginSession.setUserLoggedIn(true);
                    Intent loginIntent = new Intent(new Intent(getApplicationContext(), user_page.class));
                    startActivity(loginIntent);
                    finish();
                }
                else {
                    username.setError("Invalid  user name and password try Again");
                    username.setText("");
                    password.setText("");
                }
                break;
            case R.id.signUpBtnhome:
                startActivity(new Intent(getApplicationContext(), user_registration.class));
                finish();
                break;
        }

    }
}

