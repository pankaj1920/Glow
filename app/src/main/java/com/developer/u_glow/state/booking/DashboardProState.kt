package com.developer.u_glow.state.booking

import com.developer.u_glow.adapter.NotificationAdapter
import com.developer.u_glow.adapter.OptionAdapter

sealed class DashboardProState {
    object Init : DashboardProState()
    object NavigateToProfile : DashboardProState()
    object NavigateToAnalytics : DashboardProState()
    object NavigateToSettings : DashboardProState()
    data class SetUpOptionAdapter(val optionAdapter: OptionAdapter) : DashboardProState()
    data class SetUpNotificationAdapter(val notificationAdapter: NotificationAdapter) :
        DashboardProState()

}