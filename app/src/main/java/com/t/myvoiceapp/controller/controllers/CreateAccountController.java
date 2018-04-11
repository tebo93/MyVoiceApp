package com.t.myvoiceapp.controller.controllers;

import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.EditText;

import com.t.myvoiceapp.R;
import com.t.myvoiceapp.controller.utils.SuperDialog;
import com.t.myvoiceapp.view.CreateAccountActivity;

public class CreateAccountController {
    private EditText etName;
    private EditText etLast;
    private EditText etEmail;
    private EditText etPass;
    private FloatingActionButton fab;
    private CreateAccountActivity caa;

    public CreateAccountController(CreateAccountActivity caa){
        this.caa = caa;
        this.etName = caa.findViewById(R.id.activity_create_account_et_name);
        this.etLast = caa.findViewById(R.id.activity_create_account_et_last);
        this.etEmail = caa.findViewById(R.id.activity_create_account_et_email);
        this.etPass = caa.findViewById(R.id.activity_create_account_et_password);
        this.fab = caa.findViewById(R.id.fab);
    }

    public void confi(){
        this.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valData()){
                    //llamada a synctask
                }else{
                    SuperDialog.createToastMessage(caa,
                            caa.getString(R.string.invalid_input_data));
                }
            }
        });
    }

    private boolean valData() {
        return valPass() && valEmail() && valLast() && valName();
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
