package com.example.kr2;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    Button buttonLogin;
    EditText editTextLogin;
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        buttonLogin = findViewById(R.id.buttonLogin);
        editTextLogin = findViewById(R.id.editTextLogin);

        databaseHelper = new DatabaseHelper(this);
        databaseHelper.openDatabase();
    }

    public void buttonLoginClick(View view) {
        if(checkText()) {
            String username = editTextLogin.getText().toString();
            User.activeUser = databaseHelper.checkUser(username);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }

    public void buttonTopClick(View view) {
        Intent intent = new Intent(this, TopActivity.class);
        startActivity(intent);
    }


    public boolean checkText() {
        String username = editTextLogin.getText().toString();
        if(username.length() == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.closeDatabase();
    }
}
