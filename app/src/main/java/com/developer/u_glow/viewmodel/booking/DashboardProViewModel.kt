package com.developer.u_glow.viewmodel.booking

import android.os.Bundle
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.R
import com.developer.u_glow.adapter.NotificationAdapter
import com.developer.u_glow.adapter.OptionAdapter
import com.developer.u_glow.model.dto.NotificationData
import com.developer.u_glow.model.dto.OptionData
import com.developer.u_glow.state.booking.DashboardProState
import com.developer.u_glow.util.Constants

class DashboardProViewModel : BaseViewModel<DashboardProState>() {

    private var state: DashboardProState = DashboardProState.Init
        set(value) {
            field = value
            publishState(state)
        }

    override fun onInitialized(bundle: Bundle?) {
        setupOptionAdapter()
        setupNotificationAdapter()
    }

    private fun setupOptionAdapter() {
        val optionList = ArrayList<OptionData>()
        optionList.add(OptionData(color = R.color.orange,title = R.string.your_profile))
        optionList.add(OptionData(color = R.color.pink,title = R.string.open_tasks))
        optionList.add(OptionData(color = R.color.pink,title = R.string.analytics))
        optionList.add(OptionData(color = R.color.green,title = R.string.earnings))
        optionList.add(OptionData(color = R.color.green,title = R.string.settings))
        optionList.add(OptionData(color = R.color.orange,title = R.string.your_bookings))
        state = DashboardProState.SetUpOptionAdapter(OptionAdapter(optionList, this))
    }

    private fun setupNotificationAdapter() {
        val notificationList = ArrayList<NotificationData>()
        notificationList.add(NotificationData())
        notificationList.add(NotificationData())
        notificationList.add(NotificationData())
        state = DashboardProState.SetUpNotificationAdapter(NotificationAdapter(notificationList, true))
    }

    fun onClickOption(pos: Int){
        when (pos){
            Constants.OptionType.profile -> state = DashboardProState.NavigateToProfile
            Constants.OptionType.analytics -> state = DashboardProState.NavigateToAnalytics
            Constants.OptionType.settings -> state = DashboardProState.NavigateToSettings
        }
    }

}