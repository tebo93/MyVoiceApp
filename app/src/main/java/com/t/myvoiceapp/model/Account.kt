package com.t.myvoiceapp.model

class Account private constructor() {
    var first: String? = null
    var last: String? = null
    var subject: String? = null
    var email: String? = null
    var password: String? = null
    var username: String? = null
    var institution: String? = null

    private object Container {
        val INSTANCE = Account()
    }

    companion object {
        val instance: Account by lazy { Container.INSTANCE }
    }

    fun setAllNull(){
        first = null
        last = null
        subject = null
        email = null
        password = null
        username = null
        institution = null
    }
}