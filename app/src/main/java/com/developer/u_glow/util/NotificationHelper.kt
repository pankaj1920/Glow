package com.developer.u_glow.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.base.app.utils.sharepreference.SharedPrefManager
import com.developer.u_glow.R
import com.developer.u_glow.view.activity.splash.SplashActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber
import kotlin.random.Random


class NotificationHelper(private val mContext: Context) {

    private var mNotificationManager: NotificationManager? = null
    private var mBuilder: NotificationCompat.Builder? = null

    /**
     * Create and push the notification
     */
    fun createNotification(remoteMessage: RemoteMessage) {

        val msg = remoteMessage.data["message"] ?: remoteMessage.notification?.body
        val title = remoteMessage.data["title"] ?: remoteMessage.notification?.title
        val notificationId = remoteMessage.data["id"]?.toInt() ?: Random.nextInt()

        val message = remoteMessage.notification?.body ?: ""


        /**Creates an explicit intent for an Activity in your app */

        val resultIntent = Intent(mContext, SplashActivity::class.java)
        resultIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        val resultPendingIntent = PendingIntent.getActivity(
            mContext,
            0 /* Request code */, resultIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        mBuilder = NotificationCompat.Builder(mContext, NOTIFICATION_CHANNEL_ID)
        mBuilder!!.setSmallIcon(R.mipmap.ic_launcher_round)
        mBuilder!!.setContentTitle(title)
            .setContentText(msg)
            .setAutoCancel(true)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(message)
            )
            .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
            .setContentIntent(resultPendingIntent)

        mNotificationManager =
            mContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        @RequiresApi(Build.VERSION_CODES.O)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val notificationChannel =
                NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    "NOTIFICATION_CHANNEL_NAME",
                    importance
                )
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.vibrationPattern =
                longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)
            assert(mNotificationManager != null)
            mBuilder!!.setChannelId(NOTIFICATION_CHANNEL_ID)
//            mBuilder?.setSmallIcon(R.drawable.ic_notification)
            mNotificationManager!!.createNotificationChannel(notificationChannel)
        }

//        assert(mNotificationManager != null)
        mNotificationManager?.notify(
            notificationId /* Request Code */,
            mBuilder!!.build()
        )  /*multiple notification*/

    }

    fun getRefreshToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Timber.d("Fetching FCM registration token failed ${task.exception}")
                return@OnCompleteListener
            }

            // Get new FCM registration token
            SharedPrefManager(mContext).fcmToken = task.result
        })
    }

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "10001"
    }
}