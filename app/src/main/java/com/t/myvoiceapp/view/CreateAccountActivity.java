package com.t.myvoiceapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.t.myvoiceapp.R;
import com.t.myvoiceapp.controller.controllers.CreateAccountController;

public class CreateAccountActivity extends AppCompatActivity {

    private CreateAccountController cac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        this.cac = new CreateAccountController(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.cac.confi();
    }
}
