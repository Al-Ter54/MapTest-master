package com.example.shifthackz.maptest.utils

import android.os.AsyncTask
import android.util.Log
import com.google.android.gms.maps.GoogleMap
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class DownloadTask : AsyncTask<Any, Any, Any>() {
    private lateinit var mMap: GoogleMap

    override fun doInBackground(params: Array<Any>): String {
        var data = ""

        try {
            data = downloadUrl(params[0].toString())
        } catch (e: Exception) {
            Log.d("Background Task", e.toString())
        }

        return data
    }

    override fun onPostExecute(result: Any) {
        super.onPostExecute(result)

        val parserTask = ParserTask(mMap)
        val result2 = result.toString()

        parserTask.execute(result2)

    }

    @Throws(IOException::class)
    private fun downloadUrl(strUrl: String): String {
        var data = ""
        var iStream: InputStream? = null
        var urlConnection: HttpURLConnection? = null
        try {
            val url = URL(strUrl)

            urlConnection = url.openConnection() as HttpURLConnection

            urlConnection!!.connect()

            iStream = urlConnection!!.getInputStream()

            val br = BufferedReader(InputStreamReader(iStream))

            val sb = StringBuffer()

            var line = ""
            while ((line == br.readLine()) != null) {
                sb.append(line)
            }

            data = sb.toString()

            br.close()

        } catch (e: Exception) {
            Log.d("Exception", e.toString())
        } finally {
            iStream!!.close()
            urlConnection!!.disconnect()
        }
        return data
    }
}