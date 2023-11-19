package com.example.kr2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class TopActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<User> adapter;
    private ArrayList<User> users = new ArrayList<>();
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        listView = findViewById(R.id.listView);

        databaseHelper = new DatabaseHelper(this);
        databaseHelper.openDatabase();

        adapter = new UsersAdapter(this);
        listView.setAdapter(adapter);
        users.addAll(databaseHelper.readDatabase());
    }

    public void buttonBackToMainClick(View view) {
        Intent intent = new Intent(this, StartActivity.class);
        startActivity(intent);
    }

    private class UsersAdapter extends ArrayAdapter<User> {

        public UsersAdapter(Context context) {
            super(context, android.R.layout.simple_list_item_1, users);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            User user = getItem(position);
            LayoutInflater inflater = getLayoutInflater();
            @SuppressLint({"ViewHolder", "InflateParams"}) View view = inflater.inflate(R.layout.row, null);
            TextView textViewUsername = view.findViewById(R.id.textViewUsername);
            TextView textViewScore = view.findViewById(R.id.textViewScore);
            textViewUsername.setText(user.getUsername());
            textViewScore.setText(Integer.toString(user.getScore()));
            return view;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.closeDatabase();
    }
}
