package com.t.myvoiceapp.controller.controllers

import android.content.DialogInterface
import android.content.Intent
import android.support.design.widget.FloatingActionButton
import android.text.method.KeyListener
import android.widget.EditText
import com.t.myvoiceapp.R
import com.t.myvoiceapp.controller.asyncTask.DeleteUserAsyncTask
import com.t.myvoiceapp.controller.asyncTask.ModifyUserAsyncTask
import com.t.myvoiceapp.controller.utils.FileHandler
import com.t.myvoiceapp.controller.utils.SuperDialog
import com.t.myvoiceapp.model.Account
import com.t.myvoiceapp.view.MainActivity
import com.t.myvoiceapp.view.SeeAccountActivity
import java.util.ArrayList

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
        etUserName?.isEnabled = false
        fab?.isClickable = true
        fab?.setImageDrawable(saa?.getDrawable(android.R.drawable.ic_menu_edit))
        fab?.setOnClickListener { _ ->
            setIntState(true)
            fab?.setImageDrawable(saa?.getDrawable(android.R.drawable.ic_menu_save))
            fab?.setOnClickListener { _ ->
                if (valData()) {
                    val muat = ModifyUserAsyncTask(this@SeeAccountController)
                    muat.execute(Controller.IP + "/accountinfo",
                            etName?.text.toString(),
                            etLast?.text.toString(),
                            a.password,
                            a.username,
                            etSubject?.text.toString(),
                            etInstitution?.text.toString(),
                            a.email)
                } else {
                    SuperDialog.createToastMessage(saa,
                            saa?.getString(R.string.invalid_input_data))
                }
            }
        }
        setIntState(false)
    }

    private fun valData(): Boolean {
        return etName?.text.toString().isNotEmpty() &&
                etLast?.text.toString().isNotEmpty()

    }

    private fun setIntState(b: Boolean) {
        if (!b) {
            etName?.tag = etName?.keyListener
            etName?.keyListener = null
            etLast?.tag = etLast?.keyListener
            etLast?.keyListener = null
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

    fun result(s: String?) {
        a.first = etName?.text.toString()
        a.last = etLast?.text.toString()
        a.institution = etInstitution?.text.toString()
        SuperDialog.createToastMessage(saa, s)
    }

    fun errorResult(s: String?) {
        SuperDialog.createToastMessage(saa, s)
        etName?.setText(a.first)
        etLast?.setText(a.last)
        etPass?.setText(a.password)
        etInstitution?.setText(a.institution)
        etUserName?.setText(a.username)
        saa?.finish()
    }

    fun deleteResult(s : String){
        FileHandler.saveFile(saa, "config.dat", ArrayList())
        a.setAllNull()
        val i = Intent(saa, MainActivity::class.java)
        saa?.startActivity(i)
        saa?.finish()
    }

    fun actionDeleteAccount() {
        SuperDialog.openDialogCustomYesNo(saa,
                "Delete",
                "Are you sure to delete your account?",
                DialogInterface.OnClickListener{ _,_ ->
                    val task = DeleteUserAsyncTask(this)
                    task.execute(Controller.IP + "/account",
                            a.email,
                            a.password)
                },
                DialogInterface.OnClickListener{
                   v, _->
                    v.cancel()
                })
    }
}