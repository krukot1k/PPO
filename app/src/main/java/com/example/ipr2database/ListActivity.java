package com.example.ipr2database;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import android.annotation.SuppressLint;

import com.example.ipr2database.databinding.ActivityList2Binding;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<Person> adapter;
    private ArrayList<Person> Persons = new ArrayList<>();
    private DatabaseHelper databaseHelper;
    static int activePosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list2);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);

        listView = findViewById(R.id.listView);

        databaseHelper = new DatabaseHelper(this);
        databaseHelper.openDatabase();

        adapter = new PersonsAdapter(this);
        listView.setAdapter(adapter);
        Persons.addAll(databaseHelper.readDatabase());

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                activePosition = position + 1;
                toChange();
            }
        });

        //databaseHelper.clearDb();
    }

    public void buttonAddClick(View view) {
        toChange();
    }

    public void toChange() {
        Intent intent = new Intent(this, ChangeActivity.class);
        startActivity(intent);
    }

    private class PersonsAdapter extends ArrayAdapter<Person> {

        public PersonsAdapter(Context context) {
            super(context, android.R.layout.simple_list_item_1, Persons);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Person person = getItem(position);
            LayoutInflater inflater = getLayoutInflater();
            @SuppressLint({"ViewHolder", "InflateParams"}) View view = inflater.inflate(R.layout.row, null);
            TextView textViewName = view.findViewById(R.id.textViewName);
            TextView textViewPost = view.findViewById(R.id.textViewPost);
            TextView textViewSalary = view.findViewById(R.id.textViewSalary);
            textViewName.setText(person.getName());
            textViewPost.setText(person.getPost());
            textViewSalary.setText(Integer.toString(person.getSalary()));
            return view;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.closeDatabase();
    }
}
