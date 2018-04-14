package com.t.myvoiceapp.controller.asyncTask

import android.os.AsyncTask
import com.t.myvoiceapp.controller.controllers.LogInController
import com.t.myvoiceapp.model.Account
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class LogInUserAsyncTask(var c: LogInController) : AsyncTask<String, String, String>() {

    override fun doInBackground(vararg params: String?): String {
        var result = ""
        val url: URL
        var conn: HttpURLConnection? = null
        try {
            url = URL(params[0].toString())
            conn = url.openConnection() as HttpURLConnection
            if (params.size > 1) {
                connectionSetProperties(conn, params)
            }
            val `is` = conn.getInputStream()
            val reader = BufferedReader(InputStreamReader(`is`))
            val sb = StringBuilder()
            for (line in reader.readLines()) {
                sb.append(line).append("\n")
            }
            `is`.close()
            result = sb.toString()
            println(result)
        } catch (e: Exception) {
            e.printStackTrace()
            result = "{\"error\":\"error in the connection\"}"
        } finally {
            if (conn != null) {
                conn.disconnect()
            }
        }
        return result
    }

    override fun onPostExecute(result: String?) {
        try {
            val json = JSONObject(result)
            val r = json.getJSONObject("result");
            val a = Account.instance
            a.email = r.getString("email")
            a.password = r.getString("password")
            a.username = r.getString("username")
            a.first = r.getString("first_name")
            a.last = r.getString("last_name")
            a.institution = r.getString("institution")
            a.subject = r.getString("subject")
            c.result(true)
        } catch (e: Exception) {
            try {
                val json = JSONObject(result)
                c.errorResult(json.getString("error"))
            } catch (err: Exception) {
                c.errorResult("error : " + err.message)
            }
        }
    }

    private fun connectionSetProperties(conn: HttpURLConnection, params: Array<out String?>) {
        conn.requestMethod = "POST"
        conn.setRequestProperty("User-Agent", java.lang.System.getProperty("http.agent"))
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5")
        conn.setRequestProperty("x_email", params[1])
        conn.setRequestProperty("x_password", params[2])
        conn.doInput = true
    }
}