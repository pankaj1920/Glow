package com.developer.u_glow.state.settings

import com.developer.u_glow.adapter.AvailableDayAdapter
import com.developer.u_glow.adapter.NotificationAdapter
import com.developer.u_glow.state.notification.NotificationFragmentState

sealed class SettingProFragmentState{
    object Init : SettingProFragmentState()
    data class UpdateAvailableDateAdapter(var adapter: AvailableDayAdapter?) : SettingProFragmentState()
}
