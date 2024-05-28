package com.example.lesson05;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class ContactDialog extends DialogFragment {
    private EditText etFirstName;
    private EditText etLastName;
    private EditText etPhone;
    private EditText etEmail;
    private Button btnSave;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.dialog_new_contact, null);

        etFirstName = view.findViewById(R.id.etFirstName);
        etLastName = view.findViewById(R.id.etLastName);
        etPhone = view.findViewById(R.id.etPhone);
        etEmail = view.findViewById(R.id.etEmail);
        btnSave = view.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this::saveContact);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(view);

        return builder.create();
    }

    private void saveContact(View view) {
//        Toast.makeText(getContext(), "TEST SAVE", Toast.LENGTH_SHORT).show();
        ContactApi.addContact(new Contact(
                ContactApi.getMaxId() + 1,
                R.drawable.android,
                etFirstName.getText().toString(),
                etLastName.getText().toString(),
                etPhone.getText().toString(),
                etEmail.getText().toString()
        ));
        dismiss();

    }
}
