package com.t.myvoiceapp.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.account_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_delete_account) {
            this.sac.actionDeleteAccount()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

}
