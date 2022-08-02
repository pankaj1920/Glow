package com.developer.u_glow.view.fragment.booking

import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.base.app.utils.navigateTo
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentDashboardProBinding
import com.developer.u_glow.state.booking.DashboardProState
import com.developer.u_glow.util.Constants
import com.developer.u_glow.view.widget.DayDecorator
import com.developer.u_glow.view.widget.SelectedDayDecorator
import com.developer.u_glow.view.widget.SelectorDecorator
import com.developer.u_glow.view.widget.setEvent
import com.developer.u_glow.viewmodel.booking.DashboardProViewModel

class DashboardProFragment(override val layoutId: Int = R.layout.fragment_dashboard_pro) :
    BaseFragment<DashboardProViewModel, FragmentDashboardProBinding>() {

    override val mViewModel: DashboardProViewModel by viewModels()
    private var dateDecorator: SelectedDayDecorator? = null

    override fun subscribeObservers() {

        mViewModel.stateObserver.observe(this, Observer {
            when (it) {

                is DashboardProState.SetUpOptionAdapter -> {
                    mViewBinding.rvOptions.adapter = it.optionAdapter
                }

                is DashboardProState.SetUpNotificationAdapter -> {
                    mViewBinding.rvNotification.adapter = it.notificationAdapter
                }

                is DashboardProState.NavigateToProfile ->  findNavController().navigateTo(R.id.nav_edit_profile_pro_fragment)

                is DashboardProState.NavigateToAnalytics ->  findNavController().navigateTo(R.id.nav_analytics_fragment)

                is DashboardProState.NavigateToSettings ->  findNavController().navigateTo(R.id.nav_setting_pro_fragment)

                else -> {
                }
            }
        })
    }

    override fun onFragmentCreated() {
        mViewBinding.view = this
        setUpCalender()
    }

    private fun setUpCalender() {
        dateDecorator = SelectedDayDecorator()

        mViewBinding.calendar.addDecorators(
            SelectorDecorator(requireActivity()),
            ContextCompat.getDrawable(requireContext(), R.drawable.bg_bottom_line_white)?.let { DayDecorator(it) },
            dateDecorator
        )

        mViewBinding.calendar.setOnDateChangedListener { _, date, _ ->
            dateDecorator?.setDate(date.date)
            mViewBinding.calendar.invalidateDecorators()
        }

        setSelectedDays()
    }

    private fun setSelectedDays() {
        val dateList: ArrayList<String> = arrayListOf()
        dateList.add("2021-07-21")
        dateList.add("2021-07-25")
        mViewBinding.calendar.setEvent(dateList, requireContext())
    }

    fun onClickProfile() {
        val bundle = Bundle()
        bundle.putBoolean(Constants.BundleKey.isShowProgress, true)
        findNavController().navigateTo(R.id.nav_profile_fragment, bundle)
    }
}