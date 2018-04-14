package com.t.myvoiceapp.controller.controllers

import android.support.design.widget.FloatingActionButton
import android.text.method.KeyListener
import android.widget.EditText
import com.t.myvoiceapp.R
import com.t.myvoiceapp.controller.asyncTask.CreateUserAsyncTask
import com.t.myvoiceapp.controller.utils.SuperDialog
import com.t.myvoiceapp.model.Account
import com.t.myvoiceapp.view.SeeAccountActivity

class SeeAccountController {
    var etName: EditText? = null
    var etLast: EditText? = null
    var etEmail: EditText? = null
    var etPass: EditText? = null
    var fab: FloatingActionButton? = null
    var etUserName: EditText? = null
    var etSubject: EditText? = null
    var etInstitution: EditText? = null
    var a = Account.instance
    var saa: SeeAccountActivity? = null

    fun config() {
        etName = saa?.findViewById(R.id.activity_create_account_et_name)
        etLast = saa?.findViewById(R.id.activity_create_account_et_last)
        etEmail = saa?.findViewById(R.id.activity_create_account_et_email)
        etPass = saa?.findViewById(R.id.activity_create_account_et_password)
        fab = saa?.findViewById(R.id.fab)
        etUserName = saa?.findViewById(R.id.activity_create_account_et_username)
        etSubject = saa?.findViewById(R.id.activity_create_account_et_subject)
        etInstitution = saa?.findViewById(R.id.activity_create_account_et_institution)
        etName?.setText(a.first)
        etLast?.setText(a.last)
        etEmail?.setText(a.email)
        etPass?.setText(a.password)
        etUserName?.setText(a.username)
        etSubject?.setText(a.subject)
        etInstitution?.setText(a.institution)
        etEmail?.isEnabled = false
        etPass?.isEnabled = false
        fab?.isClickable = true
        //set fab icon to edit
        fab?.setOnClickListener { _ ->
            //set fab icon to save
            setIntState(true)
            fab?.setOnClickListener { _ ->
                if (valData()) {
                    val cuat = CreateUserAsyncTask(this@SeeAccountController)
                    cuat.execute(Controller.IP + "/account",
                            etName?.text.toString(),
                            etLast?.text.toString(),
                            a.password,
                            a.username,
                            etSubject?.getText().toString(),
                            etInstitution?.getText().toString(),
                            a.email)
                } else {
                    SuperDialog.createToastMessage(saa,
                            saa?.getString(R.string.invalid_input_data))
                }
                //save modification
                //config()
            }
        }
        setIntState(false)
    }

    private fun valData(): Boolean {
        return etName?.getText().toString().isNotEmpty() &&
                etLast?.getText().toString().isNotEmpty() &&

    }

    private fun setIntState(b: Boolean) {
        if (!b) {
            etName?.tag = etName?.keyListener
            etName?.keyListener = null
            etLast?.tag = etLast?.keyListener
            etLast?.keyListener = null
            etUserName?.tag = etUserName?.keyListener
            etUserName?.keyListener = null
            etSubject?.tag = etSubject?.keyListener
            etSubject?.keyListener = null
            etInstitution?.tag = etInstitution?.keyListener
            etInstitution?.keyListener = null
        } else {
            etName?.keyListener = etName?.tag as KeyListener?
            etLast?.keyListener = etLast?.tag as KeyListener?
            etUserName?.keyListener = etUserName?.tag as KeyListener?
            etSubject?.keyListener = etSubject?.tag as KeyListener?
            etInstitution?.keyListener = etInstitution?.tag as KeyListener?
        }
    }

    fun result() {
        etName?.setText(a.first)
        etLast?.setText(a.last)
        etPass?.setText(a.password)
        etInstitution?.setText(a.institution)
        etUserName?.setText(a.username)
    }

    fun errorResult() {
        saa?.finish()
    }
}