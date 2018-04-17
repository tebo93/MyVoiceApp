package com.t.myvoiceapp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.t.myvoiceapp.R
import com.t.myvoiceapp.controller.controllers.SeeChooseAddLanguageController

class SeeChooseAddLanguageActivity : AppCompatActivity() {

    var scalc : SeeChooseAddLanguageController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_see_choose_add_language)
        scalc = SeeChooseAddLanguageController(this)
    }

    override fun onStart() {
        super.onStart()
        scalc?.setUp()
    }

}
