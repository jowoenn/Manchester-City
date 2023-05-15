package com.example.esport;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    EditText userName, userPassword;
    Button buttonRegister;
    TextView loginText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        // Declaring
        loginText = findViewById(R.id.loginText);
        userName = findViewById(R.id.userName);
        userPassword = findViewById(R.id.userPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

        // Button
        loginText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper = new DatabaseHelper(Register.this);
                databaseHelper.addUser(userName.getText().toString().trim(),
                        userPassword.getText().toString().trim());

                Intent intent = new Intent(Register.this, News.class);
                startActivity(intent);
            }
        });



    }
}









