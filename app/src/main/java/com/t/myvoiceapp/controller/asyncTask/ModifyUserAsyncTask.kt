package com.t.myvoiceapp.controller.asyncTask

import android.os.AsyncTask
import com.t.myvoiceapp.controller.controllers.CreateAccountController
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ModifyUserAsyncTask(var c: CreateAccountController) : AsyncTask<String, String, String>() {

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
            c.result(json.getString("result"))
        } catch (e: Exception) {
            try {
                val json = JSONObject(result)
                c.errorResult(json.getString("error"))
            } catch (err: Exception) {
                c.errorResult("error " + err.message)
            }
        }
    }

    private fun connectionSetProperties(conn: HttpURLConnection, params: Array<out String?>) {
        conn.requestMethod = "PUT"
        conn.setRequestProperty("User-Agent", java.lang.System.getProperty("http.agent"))
        conn.setRequestProperty("Accept-Language", "en-US,en;q=0.5")
        conn.setRequestProperty("x_first_name", params[1])
        conn.setRequestProperty("x_last_name", params[2])
        conn.setRequestProperty("x_password", params[3])
        conn.setRequestProperty("x_username", params[4])
        conn.setRequestProperty("x_subject", params[5])
        conn.setRequestProperty("x_institution", params[6])
        conn.setRequestProperty("x_email", params[7])
        conn.doInput = true
    }
}