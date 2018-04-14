package com.t.myvoiceapp.controller.controllers;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;

import com.t.myvoiceapp.R;
import com.t.myvoiceapp.controller.asyncTask.CreateUserAsyncTask;
import com.t.myvoiceapp.controller.utils.SuperDialog;
import com.t.myvoiceapp.view.CreateAccountActivity;
import com.t.myvoiceapp.view.LogInActivity;

public class CreateAccountController {
    private EditText etInstitution;
    private EditText etSubject;
    private EditText etUserName;
    private EditText etName;
    private EditText etLast;
    private EditText etEmail;
    private EditText etPass;
    private FloatingActionButton fab;
    private CreateAccountActivity caa;

    public CreateAccountController(CreateAccountActivity caa) {
        this.caa = caa;
        this.etName = caa.findViewById(R.id.activity_create_account_et_name);
        this.etLast = caa.findViewById(R.id.activity_create_account_et_last);
        this.etEmail = caa.findViewById(R.id.activity_create_account_et_email);
        this.etPass = caa.findViewById(R.id.activity_create_account_et_password);
        this.fab = caa.findViewById(R.id.fab);
        this.etUserName = caa.findViewById(R.id.activity_create_account_et_username);
        this.etSubject = caa.findViewById(R.id.activity_create_account_et_subject);
        this.etInstitution = caa.findViewById(R.id.activity_create_account_et_institution);
    }

    public void result(String s) {
        SuperDialog.createToastMessage(caa, s);
        Intent i = new Intent(caa, LogInActivity.class);
        caa.startActivity(i);
    }

    public void errorResult(String s) {
        SuperDialog.createToastMessage(caa, s);
    }

    public void confi() {
        this.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valData()) {
                    CreateUserAsyncTask cuat = new CreateUserAsyncTask(CreateAccountController.this);
                    cuat.execute(Controller.IP + "/account",
                            etName.getText().toString(),
                            etLast.getText().toString(),
                            etPass.getText().toString(),
                            etUserName.getText().toString().trim(),
                            etSubject.getText().toString(),
                            etInstitution.getText().toString(),
                            etEmail.getText().toString().trim());
                } else {
                    SuperDialog.createToastMessage(caa,
                            caa.getString(R.string.invalid_input_data));
                }
            }
        });
    }

    private boolean valData() {
        return valPass() && valEmail() && valLast() && valName() && valUserName();
    }

    private boolean valUserName() {
        String username = this.etUserName.getText().toString();
        return !username.trim().isEmpty() && username.trim().length() >= 4;
    }

    private boolean valPass() {
        String pass = this.etPass.getText().toString();
        return !pass.trim().isEmpty();
    }

    private boolean valEmail() {
        String email = this.etEmail.getText().toString();
        return !email.trim().isEmpty();
    }

    private boolean valLast() {
        String last = this.etLast.getText().toString();
        return !last.trim().isEmpty();
    }

    private boolean valName() {
        String name = this.etName.getText().toString();
        return !name.trim().isEmpty();
    }
}
