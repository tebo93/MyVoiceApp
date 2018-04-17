package com.t.myvoiceapp.controller.adapters.seechooseaddlanguage

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.t.myvoiceapp.R

class RecyclerViewLanguageAdapter(c: Context, var alss: ArrayList<String>) : RecyclerView.Adapter<LanguageHolder>() {
    override fun onBindViewHolder(holder: LanguageHolder, position: Int) {
        holder.tvText.isEnabled = false
        holder.tvText.text = alss.get(position)
    }

    val li = LayoutInflater.from(c)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanguageHolder {
        return LanguageHolder(li.inflate(R.layout.see_choose_add_language_card, parent, false))
    }

    override fun getItemCount(): Int {
        return alss.size
    }

    fun setElements(alss: ArrayList<String>) {
        this.alss.clear()
        this.alss.addAll(alss)
    }

}