package de.slini_haeher.pointsalike

import android.os.AsyncTask
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.net.URL
import java.net.UnknownHostException


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DownloadFilesTask(this).execute()
        setContentView(R.layout.activity_main)
    }
}

private class DownloadFilesTask(private var m: MainActivity) : AsyncTask<String?, Int?, Long>() {
    var url = ""
    var session_id = ""
    // these Strings / or String are / is the parameters of the task, that can be handed over via the excecute(params) method of AsyncTask
    protected override fun doInBackground(vararg params: String?): Long {
        try {
            //val text: TextView = m.findViewById(R.id.textcount)
            //tv1.text = "Hello my New Project"
            //val text = m.findViewById(0) as TextView
            if(session_id == "" || session_id == "Offline" || session_id == "not found")
                session_id = URL("http://mc.chaos-craft.de:24000/pointsalike/login?user=noroot&pw=o7nq4L2zaK3QLYjryscFB5RvxR2HG7zN4sLwKSMjEMy4CXU3Smd4pT4BXpeCm7V2").readText()
            url = URL("http://mc.chaos-craft.de:24000/pointsalike/getCount?user=noroot&sessionId="+session_id+"").readText()
        } catch (e: java.io.FileNotFoundException){
            e.printStackTrace()
            url = "not found"
            session_id = "not available"
        } catch (e: UnknownHostException){
            url = "You are Offline"
            session_id = "Offline"
        }
        return 10L
    }
    protected override fun onProgressUpdate(vararg values: Int?) {
        //setProgressPercent(progress[0])
    }

    override fun onPostExecute(result: Long) {
        val text = m.findViewById(R.id.textcount) as TextView
        val t = "Points: " + url
        text.setText(t)
    }
}