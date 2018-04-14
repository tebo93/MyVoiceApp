package com.t.myvoiceapp.controller.controllers;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.t.myvoiceapp.R;
import com.t.myvoiceapp.controller.asyncTask.LogInUserAsyncTask;
import com.t.myvoiceapp.controller.utils.FileHandler;
import com.t.myvoiceapp.controller.utils.SuperDialog;
import com.t.myvoiceapp.model.Account;
import com.t.myvoiceapp.view.AccueilActivity;
import com.t.myvoiceapp.view.LogInActivity;

import java.io.File;
import java.util.ArrayList;

public class LogInController {
    private LogInActivity lia;
    private EditText etEmail;
    private EditText etPass;
    private Button b;
    private String email;
    private String pass;

    public LogInController() {

    }

    public void setActivity(LogInActivity lia) {
        this.lia = lia;
        this.etEmail = lia.findViewById(R.id.activity_login_et_email);
        this.etPass = lia.findViewById(R.id.activity_login_et_password);
        this.b = lia.findViewById(R.id.activity_login_b);
    }

    public void result(boolean b) {
        if (b) {
            ArrayList<String> als = new ArrayList<>();
            als.add(email);
            als.add(pass);
            FileHandler.saveFile(lia, "config.dat", als);
            Intent i = new Intent(lia, AccueilActivity.class);
            lia.startActivity(i);
        } else {
            SuperDialog.createToastMessage(lia, lia.getString(R.string.email_or_password_wrong));
        }
        this.b.setClickable(true);
        this.etEmail.setClickable(true);
        this.etPass.setClickable(true);
    }

    public void errorResult(String s) {
        SuperDialog.createToastMessage(lia, s);
        this.b.setClickable(true);
        this.etEmail.setClickable(true);
        this.etPass.setClickable(true);
    }

    public void config() {
        ArrayList<String> als = FileHandler.readFromFile(lia, "config.dat");
        if (als.size() == 2){
            LogInUserAsyncTask liuat = new LogInUserAsyncTask(LogInController.this);
            liuat.execute(Controller.IP + "/accountinfo", als.get(0), als.get(1));
        }
        this.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valData()) {
                    email = etEmail.getText().toString().trim();
                    pass = etPass.getText().toString().trim();
                    b.setClickable(false);
                    etEmail.setClickable(false);
                    etPass.setClickable(false);
                    LogInUserAsyncTask liuat = new LogInUserAsyncTask(LogInController.this);
                    liuat.execute(Controller.IP + "/accountinfo", email, pass);
                } else {
                    SuperDialog.createToastMessage(lia, lia.getString(R.string.data_entered_error));
                }
            }
        });
    }

    private boolean valData() {
        return valEmail() && valPassword();
    }

    private boolean valPassword() {
        return !etPass.getText().toString().trim().isEmpty() &&
                etPass.getText().toString().trim().length() >= 4;
    }

    private boolean valEmail() {
        return !etEmail.getText().toString().trim().isEmpty() &&
                etEmail.getText().toString().trim().length() >= 4;
    }
}
