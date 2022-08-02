package com.developer.u_glow.viewmodel.notification

import android.os.Bundle
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.adapter.NotificationAdapter
import com.developer.u_glow.model.dto.NotificationData
import com.developer.u_glow.state.notification.NotificationFragmentState

class NotificationViewModel: BaseViewModel<NotificationFragmentState>() {
    private var notificationList: MutableList<NotificationData>? = null
    private var notificationAdapter: NotificationAdapter? = null

    private var state: NotificationFragmentState = NotificationFragmentState.Init
        set(value) {
            field = value
            publishState(state)
        }

    override fun onInitialized(bundle: Bundle?) {
        initNotification()
    }

    private fun initNotification() {
        notificationList = ArrayList()
        notificationList?.add(NotificationData("",""))
        notificationList?.add(NotificationData("",""))
        notificationList?.add(NotificationData("",""))
        notificationAdapter = NotificationAdapter(notificationList as ArrayList<NotificationData>)
        state=NotificationFragmentState.UpdateNotificationAdapter(notificationAdapter)
    }
}