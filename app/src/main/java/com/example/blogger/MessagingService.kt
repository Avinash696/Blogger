package com.example.blogger

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        message.notification?.body?.let { showNotification(it) }
    }

    private fun showNotification(message: String) {
        val pi: PendingIntent =
            PendingIntent.getActivity(this, 0, Intent(this, MainActivity::class.java), 0)

        val notification: Notification = NotificationCompat.Builder(this)
            .setSmallIcon(R.drawable.notification)
            .setContentTitle("Avinash Blogs")
            .setContentText(message)
            .setContentIntent(pi)
            .setAutoCancel(true)
            .build()

        val nm: NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        nm.notify(0, notification)
    }
}