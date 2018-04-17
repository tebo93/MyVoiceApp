package com.t.myvoiceapp.controller.asyncTask

import android.os.AsyncTask
import com.t.myvoiceapp.controller.controllers.CreateAccountController
import com.t.myvoiceapp.controller.controllers.LogInController
import com.t.myvoiceapp.controller.controllers.SeeChooseAddLanguageController
import com.t.myvoiceapp.controller.utils.SuperDialog
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class SearchAllLanguagesAsyncTask(var c: SeeChooseAddLanguageController) : AsyncTask<String, String, String>() {

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
        val alss = ArrayList<String>()
        try {
            val json = JSONObject(result)
            val jsona = json.getJSONArray("result")
            for (i in 0..(jsona.length()-1)) {
                try {
                    val jsono = jsona.getJSONObject(i)
                    alss.add(jsono.getString("langue"))
                } catch (e: Exception) {
                    SuperDialog.createLogMessage("Error",e.message)
                }
            }
        } catch (e: Exception) {
            try {
                val json = JSONObject(result)
                c.errorResult(json.getString("error"))
                return
            } catch (err: Exception) {
                c.errorResult("error : " + err.message)
                return
            }
        }
        c.resultAllLanguages(alss)
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