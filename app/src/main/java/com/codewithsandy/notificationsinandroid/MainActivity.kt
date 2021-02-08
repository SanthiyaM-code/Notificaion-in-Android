
package com.codewithsandy.notificationsinandroid

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {

    val ChannelName="Notification channe no 1"
    val ChannelId="ID1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        createNotificationChannel()

        //make pop up responsible
        val intent= Intent(this,MainActivity::class.java)
        val pendingIntent=TaskStackBuilder.create(this).run{
            addNextIntentWithParentStack(intent)
            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
        }

        val notification = NotificationCompat.Builder(this,ChannelId).setContentTitle("Sample notification title").setContentTitle("This is notificaion context!")
            .setSmallIcon(R.drawable.ic_baseline_notifications_active_24).setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent).build()


        val notificationManager=NotificationManagerCompat.from(this)

     var button :Button
     button=findViewById(R.id.button)
        button.setOnClickListener{
            notificationManager.notify(0,notification)
        }
    }
    fun createNotificationChannel()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O)
        {
            val channel=NotificationChannel(ChannelId,ChannelName,NotificationManager.IMPORTANCE_HIGH).apply {
                lightColor=Color.GREEN
                enableLights(true)
            }
            val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}