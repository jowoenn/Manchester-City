package com.example.esport;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {

    TextView registerText;
    EditText userName, userPassword;
    Button buttonLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Declare
        userName = findViewById(R.id.userName);
        userPassword = findViewById(R.id.userPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        registerText = findViewById(R.id.registerText);

        SQLiteDatabase db = new DatabaseHelper(this).getReadableDatabase();

        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userName.getText().toString();
                String password = userPassword.getText().toString();

                // Taking Data
                String[] projection = {
                        DatabaseHelper.UserEntry.COLUMN_ID,
                        DatabaseHelper.UserEntry.COLUMN_NAME,
                        DatabaseHelper.UserEntry.COLUMN_PASSWORD
                };

                String selection = DatabaseHelper.UserEntry.COLUMN_NAME + " = ? AND " +
                        DatabaseHelper.UserEntry.COLUMN_PASSWORD + " = ?";
                String[] selectionArgs = { name, password };

                // Validating
                Cursor cursor = db.query(
                        DatabaseHelper.UserEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        null
                );

                if (cursor.moveToFirst()) {
                    Intent intent = new Intent(Login.this, News.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
