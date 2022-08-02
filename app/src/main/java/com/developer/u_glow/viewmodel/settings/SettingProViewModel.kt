package com.developer.u_glow.viewmodel.settings

import android.os.Bundle
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.adapter.AvailableDayAdapter
import com.developer.u_glow.adapter.NotificationAdapter
import com.developer.u_glow.model.dto.NotificationData
import com.developer.u_glow.model.dto.SettingProData
import com.developer.u_glow.state.notification.NotificationFragmentState
import com.developer.u_glow.state.settings.SettingProFragmentState

class SettingProViewModel:BaseViewModel<SettingProFragmentState>() {

    private var daylist: MutableList<SettingProData>? = null
    private var settingProAdapter: AvailableDayAdapter? = null
    private var state: SettingProFragmentState = SettingProFragmentState.Init
        set(value) {
            field = value
            publishState(state)
        }
    override fun onInitialized(bundle: Bundle?) {
        initAvailableDate()
    }
    private fun initAvailableDate() {
        daylist = ArrayList()
        daylist?.add(SettingProData("M"))
        daylist?.add(SettingProData("T"))
        daylist?.add(SettingProData("W"))
        daylist?.add(SettingProData("T"))
        daylist?.add(SettingProData("F"))
        daylist?.add(SettingProData("S"))
        daylist?.add(SettingProData("S"))
        settingProAdapter = AvailableDayAdapter(daylist as ArrayList<SettingProData>)
        state=SettingProFragmentState.UpdateAvailableDateAdapter(settingProAdapter)
    }
}