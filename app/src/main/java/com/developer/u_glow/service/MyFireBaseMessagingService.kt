package com.developer.u_glow.service

import com.base.app.utils.sharepreference.SharedPrefManager
import com.developer.u_glow.util.NotificationHelper
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber


class MyFireBaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        Timber.d("fcmtoken $p0 ")
        SharedPrefManager(this).fcmToken = p0
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Timber.d("notification received")
        NotificationHelper(this).createNotification(remoteMessage)
    }
}