package com.t.myvoiceapp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.t.myvoiceapp.R
import com.t.myvoiceapp.controller.controllers.SeeAccountController

class SeeAccountActivity : AppCompatActivity() {

    var sac = SeeAccountController()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)
        this.sac.saa = this
    }

    override fun onStart() {
        super.onStart()
        sac.config()
    }

}
