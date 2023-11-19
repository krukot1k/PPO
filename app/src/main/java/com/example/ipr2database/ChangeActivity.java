package com.example.ipr2database;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.ipr2database.databinding.ActivityChangeBinding;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChangeActivity extends AppCompatActivity {
    Button buttonBackToList;
    Button buttonSave;
    Button buttonDelete;
    EditText editTextName;
    EditText editTextType;
    EditText editTextCost;
    private DatabaseHelper databaseHelper;
    String forceName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        buttonBackToList = findViewById(R.id.buttonBackToList);
        buttonSave = findViewById(R.id.buttonSave);
        buttonDelete = findViewById(R.id.buttonDelete);

        editTextName = findViewById(R.id.editTextName);
        editTextType = findViewById(R.id.editTextType);
        editTextCost = findViewById(R.id.editTextCost);

        databaseHelper = new DatabaseHelper(this);
        databaseHelper.openDatabase();

        if(ListActivity.activePosition > 0) {
            Person person = databaseHelper.takeFromDatabase(ListActivity.activePosition);
            forceName = person.getName();
            editTextName.setText(person.getName());
            editTextType.setText(person.getPost());
            editTextCost.setText(Integer.toString(person.getSalary()));
        }
    }

    public void buttonBackToListClick(View view) {
        backToList();
    }

    public void buttonSaveClick(View view) {
        String name = editTextName.getText().toString();
        String type = editTextType.getText().toString();
        String cost = editTextCost.getText().toString();
        if(!name.isEmpty() && !type.isEmpty() && !cost.isEmpty()) {
            if(!forceName.isEmpty()) {
                databaseHelper.updateDatabase(forceName, name,type, Integer.parseInt(cost));
            } else {
                databaseHelper.addToDatabase(name, type, Integer.parseInt(cost));
            }
            backToList();
        }
    }

    public void buttonDeleteClick(View view) {
        if(!forceName.isEmpty()) {
            databaseHelper.deleteFromDatabase(forceName);
            backToList();
        }
    }

    public void backToList() {
        ListActivity.activePosition = 0;
        Intent intent = new Intent(this, ListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        databaseHelper.closeDatabase();
    }
}
