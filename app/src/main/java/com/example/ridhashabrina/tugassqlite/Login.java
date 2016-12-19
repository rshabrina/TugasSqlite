package com.example.ridhashabrina.tugassqlite;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    Button btnLogin;
    EditText etUsername, etPassword;
    String Username = "admin", Password = "admin";
    DataHelper MyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        MyDB = new DataHelper(this);
        Cursor res = MyDB.LihatData();
        if(res.moveToNext()){
            finish();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        etUsername = (EditText) findViewById(R.id.editTextUsername);
        etPassword = (EditText) findViewById(R.id.editTextPassword);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
    }
    private void Login(){
        String User = etUsername.getText().toString();
        String Pass = etPassword.getText().toString();
        if(User.equals("") || Pass.equals("")){
            Toast.makeText(this, "Form Masih Kosong!", Toast.LENGTH_SHORT).show();
        }else{
            if(!User.equals(Username) || !Pass.equals(Password)){
                Toast.makeText(this, "Username atau Password Salah!", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Berhasil Login!", Toast.LENGTH_SHORT).show();
                MyDB.SimpanData(User, Pass);
                finish();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        }
    }
}