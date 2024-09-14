package com.saif.jetpacknavigationexample

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

const val CHANNEL_ID = "demoChannel"

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val channel =
            NotificationChannel(CHANNEL_ID, "Example", NotificationManager.IMPORTANCE_DEFAULT)
        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).createNotificationChannel(
            channel
        )

    }
}