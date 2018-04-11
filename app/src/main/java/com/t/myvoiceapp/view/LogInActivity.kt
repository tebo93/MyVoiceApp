package com.t.myvoiceapp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.t.myvoiceapp.R
import com.t.myvoiceapp.controller.controllers.LogInController

class LogInActivity : AppCompatActivity() {

    var lic = LogInController()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        lic.setActivity(this)
    }

    override fun onStart() {
        super.onStart()
        lic.config()
    }
}
