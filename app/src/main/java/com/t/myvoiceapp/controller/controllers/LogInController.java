package com.t.myvoiceapp.controller.controllers;

import android.widget.Button;
import android.widget.EditText;

import com.t.myvoiceapp.R;
import com.t.myvoiceapp.view.LogInActivity;

public class LogInController {
    private LogInActivity lia;
    private EditText etEmail;
    private EditText etPass;
    private Button b;

    public LogInController(){

    }

    public void setActivity(LogInActivity lia) {
        this.lia = lia;
        this.etEmail = lia.findViewById(R.id.activity_login_et_email);
        this.etPass = lia.findViewById(R.id.activity_login_et_password);
        this.b = lia.findViewById(R.id.activity_login_b);
    }

    public void config(){

    }
}
