package com.developer.u_glow.state.notification

import com.developer.u_glow.adapter.NotificationAdapter

sealed class NotificationFragmentState{

    object Init : NotificationFragmentState()
    data class UpdateNotificationAdapter(var adapter: NotificationAdapter?) : NotificationFragmentState()
}
