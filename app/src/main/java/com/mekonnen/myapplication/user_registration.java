package com.mekonnen.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class user_registration extends AppCompatActivity{
    private EditText fulname,username,email,password,phone,gender;
    private Button registeruser;
    private String user_fulname,username2,user_email,user_password,user_phone,user_gender;
    UserInfoDatabas userInfodb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);
        fulname=findViewById(R.id.FullName);
        username=findViewById(R.id.userNameReceiver);
        email=findViewById(R.id.Email);
        password=findViewById(R.id.Password);
        phone=findViewById(R.id.PhoneNumber);
        gender=findViewById(R.id.Gender);
        registeruser=findViewById(R.id.SignUpReal);
        userInfodb=new UserInfoDatabas(this);
    }
    public void registerUser(View view) {
        userInfodb=new UserInfoDatabas(this);
        user_fulname=fulname.getText().toString().trim();
        username2=username.getText().toString().trim();
        user_email=email.getText().toString().trim();
        user_password=password.getText().toString().trim();
        user_phone=phone.getText().toString().trim();
        user_gender=gender.getText().toString().trim();
        if(user_fulname.isEmpty())
            fulname.setError("User FulName Required!");
        else if(username2.isEmpty())
            username.setError("Username Required!");
        else  if(user_email.isEmpty())
            email.setError("User email Required!");
        else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(user_email).matches())
            email.setError("please enter valid Email ending like @gmail.com or @yahoomail.com");
        else if(user_password.isEmpty())
            password.setError("User password Required!");
        else  if(user_phone.isEmpty())
            phone.setError("User phone Required!");
        else {
            userInfodb.addUser(user_fulname, username2, user_email, user_password, user_phone, user_gender);
            fulname.setText("");
            username.setText("");
            email.setText("");
            password.setText("");
            phone.setText("");
            gender.setText("");
            moveToUserPage();
        }
    }
    public void moveToUserPage(){
        Intent intent= new Intent(this,user_page.class);
        startActivity(intent);
    }

    public void clear(View view) {
        fulname.setText("");
        email.setText("");
        phone.setText("");
        username.setText("");
        password.setText("");
        gender.setText("");
    }
}
