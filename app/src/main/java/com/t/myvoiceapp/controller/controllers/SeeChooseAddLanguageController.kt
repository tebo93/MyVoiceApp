package com.t.myvoiceapp.controller.controllers

import android.app.AlertDialog
import android.content.DialogInterface
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.TextView
import com.t.myvoiceapp.R
import com.t.myvoiceapp.controller.adapters.seechooseaddlanguage.RecyclerViewLanguageAdapter
import com.t.myvoiceapp.controller.asyncTask.AddLanguageAsyncTask
import com.t.myvoiceapp.controller.asyncTask.DeleteUserAsyncTask
import com.t.myvoiceapp.controller.asyncTask.SearchAllLanguagesAsyncTask
import com.t.myvoiceapp.controller.utils.SuperDialog
import com.t.myvoiceapp.model.Account
import com.t.myvoiceapp.view.SeeChooseAddLanguageActivity

class SeeChooseAddLanguageController(var scala: SeeChooseAddLanguageActivity) {
    var rv = scala.findViewById(R.id.activity_see_choose_add_language_rv) as RecyclerView
    var fab = scala.findViewById(R.id.activity_see_choose_add_language_fab) as FloatingActionButton
    var rvla: RecyclerViewLanguageAdapter? = null
    var li = LayoutInflater.from(scala)
    var a = Account.instance
    fun setUp() {
        rv.layoutManager = LinearLayoutManager(scala)
        rvla = RecyclerViewLanguageAdapter(scala, ArrayList())
        rv.adapter = rvla
        fab.setOnClickListener({
            val v = li.inflate(R.layout.see_choose_add_language_card, null, false)
            val tx = v.findViewById(R.id.see_choose_add_language_card_tv) as EditText
            tx.hint = "New Language Name"
            SuperDialog.openDialogCustomDialog(scala,
                    "New Language",
                    v,
                    DialogInterface.OnClickListener { v, _ ->
                        if (tx.text.toString().trim().length > 2) {
                            val task = AddLanguageAsyncTask(this)
                            task.execute(Controller.IP + "/accountlan",
                                    a.email,
                                    a.password,
                                    tx.text.toString())
                        } else {
                            SuperDialog.createToastMessage(scala, "The language should have at least 3 characters")
                            v.cancel()
                        }
                    },
                    DialogInterface.OnClickListener { v, _ ->
                        v.cancel()
                    })
        })
        val task = SearchAllLanguagesAsyncTask(this)
        task.execute(Controller.IP + "/accountinfolangues",
                a.email,
                a.password)
    }

    fun resultAddedCorrectly(s: String) {
        val task = SearchAllLanguagesAsyncTask(this)
        task.execute(Controller.IP + "/accountinfolangues",
                a.email,
                a.password)
    }

    fun errorResult(s: String) {

    }

    fun resultAllLanguages(alss: ArrayList<String>) {
        rvla?.setElements(alss)
        rvla?.notifyDataSetChanged()
    }
}