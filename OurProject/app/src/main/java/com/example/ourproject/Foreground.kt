
package com.example.ourproject

import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content. Context
import android.net.Uri

import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat

class ForegroundService : Service() {


    private val CHANNEL_ID = "ForegroundService Kotlin"

    companion object {
        fun startService(context: Context, message: String) {
            val startIntent = Intent(context, ForegroundService::class.java)
            startIntent.putExtra("inputExtra", message)
            ContextCompat.startForegroundService(context, startIntent)
        }


    }
var id=1
    override fun onStartCommand(intent: Intent?, flags: Int, startld: Int): Int {

        val input = intent?.getStringExtra(" inputExtra")
        createNotificationChannel()

        //map
        val mapIntent: Intent = Uri.parse("Indoor Cafe, Barakat Commercial Complex," +
                " Queen Rania Al Abdullah St 275, Amman"
        ).let { location ->
            // Or map point based on latitude/longitude
            val location: Uri = Uri.parse("geo:37.422219,-122.08364?z=14")
            // z param is zoom level
            Intent(Intent.ACTION_VIEW, location)
        }




//TO OPEN NOTIFICATION / FEATURES OF NOTIFICATION

val notificationIntent=Intent(mapIntent)
        val pendingIntent= PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val notification = NotificationCompat.Builder (this, CHANNEL_ID)

            .setContentTitle("You Have Successfully Booked in The Ordered Place ")
            .setContentText(input)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): Binder? {
        return null
    }


    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val serviceChannel = NotificationChannel(CHANNEL_ID, "Foreground Service Channe!",
                NotificationManager.IMPORTANCE_DEFAULT)

            val manager = getSystemService(NotificationManager::class.java)
            manager!!.createNotificationChannel(serviceChannel)
        }
    }

}


