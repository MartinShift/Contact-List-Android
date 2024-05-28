package com.example.lesson05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.lesson05.databinding.ActivityFullContactBinding;

public class FullContactActivity extends AppCompatActivity {
    private ActivityFullContactBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFullContactBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Contact contact = (Contact) getIntent().getSerializableExtra("contact");

        binding.ivAvatar.setImageResource(contact.getAvatar());
        binding.etFirstName.setText(contact.getFirstName());
        binding.etLastName.setText(contact.getLastName());
        binding.etPhone.setText(contact.getPhone());
        binding.etEmail.setText(contact.getEmail());

        binding.btnApply.setOnClickListener(v -> {
            String firstName = binding.etFirstName.getText().toString();
            String lastName = binding.etLastName.getText().toString();
            String phone = binding.etPhone.getText().toString();
            String email = binding.etEmail.getText().toString();

            contact.setFirstName(firstName);
            contact.setLastName(lastName);
            contact.setPhone(phone);
            contact.setEmail(email);

            ContactApi.updateContact(contact);

            //Intent intent = new Intent(FullContactActivity.this, MainActivity.class);
            //startActivity(intent);
            finish();
        });
    }
}