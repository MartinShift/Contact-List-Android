package com.example.lesson05;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvContacts;
    private List<Contact> contacts;
    private ContactAdapter adapter;
    private Button btnAdd;

    private SharedPreferences sharedPreferences;
    private boolean isFirstNameFirst = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContacts = findViewById(R.id.lvContacts);
        btnAdd = findViewById(R.id.btnAdd);

        ContactApi.init();
        contacts = ContactApi.getContacts();

        adapter = new ContactAdapter(
                this,
                R.layout.contact_item,
                contacts
        );
        lvContacts.setAdapter(adapter);
        lvContacts.setOnItemClickListener((parent, view, position, id) -> {
            Contact contact = contacts.get(position);
            //Toast.makeText(MainActivity.this, contact.getLastName(), Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, FullContactActivity.class);
            intent.putExtra("contact", contact);
            startActivity(intent);
        });
        lvContacts.setOnItemLongClickListener((parent, view, position, id) -> {
            contacts.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "removed", Toast.LENGTH_SHORT).show();
            return true;
        });

        btnAdd.setOnClickListener(v -> {
            ContactDialog contactDialog = new ContactDialog();
            contactDialog.show(getSupportFragmentManager(), "contact");
        });

        sharedPreferences = getSharedPreferences("com.example.lesson05", MODE_PRIVATE);
        isFirstNameFirst = sharedPreferences.getBoolean("isFirstNameFirst", true);
        updateNameOrder();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.setttings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.firstSecond) {
            isFirstNameFirst = true;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstNameFirst", isFirstNameFirst);
            editor.apply();
            updateNameOrder();
            return true;
        } else if (id == R.id.secondFirst) {
            isFirstNameFirst = false;
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isFirstNameFirst", isFirstNameFirst);
            editor.apply();
            updateNameOrder();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateNameOrder() {
        adapter.setFirstNameFirst(isFirstNameFirst);
        adapter.notifyDataSetChanged();
    }

}