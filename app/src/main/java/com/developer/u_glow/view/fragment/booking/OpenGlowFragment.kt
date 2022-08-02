package com.developer.u_glow.view.fragment.booking

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.base.app.utils.navigateTo
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentOpenGlowBinding
import com.developer.u_glow.state.booking.OpenGlowState
import com.developer.u_glow.viewmodel.booking.OpenGlowViewModel

class OpenGlowFragment(override val layoutId: Int = R.layout.fragment_open_glow) :
    BaseFragment<OpenGlowViewModel, FragmentOpenGlowBinding>() {

    override val mViewModel: OpenGlowViewModel by viewModels()

    override fun subscribeObservers() {

        mViewModel.stateObserver.observe(this, Observer {
            when (it) {

                is OpenGlowState.UpdateBookingAdapter -> {
                    mViewBinding.rvBooking.adapter = it.adapter
                }

                is OpenGlowState.UpdateFilterAdapter -> {
                    mViewBinding.includeFilter.rvDay.adapter = it.adapter
                    mViewBinding.includeFilter.rvService.adapter = it.adapter
                    mViewBinding.includeFilter.rvTime.adapter = it.adapter
                }

                is OpenGlowState.NavigateToDetails -> {
                    findNavController().navigateTo(R.id.nav_make_offer_fragment)
                }

                else -> {
                }
            }
        })
    }


    override fun onFragmentCreated() {
        mViewBinding.includeFilter.view = this
    }

    fun onClickLocation(){
        mViewBinding.includeFilter.isShowLocation = !mViewBinding.includeFilter.isShowLocation
    }

    fun onClickTime(){
        mViewBinding.includeFilter.isShowTime = !mViewBinding.includeFilter.isShowTime
    }

    fun onClickBudget(){
        mViewBinding.includeFilter.isShowBudget = !mViewBinding.includeFilter.isShowBudget
    }

    fun onClickDay(){
        mViewBinding.includeFilter.isShowDay = !mViewBinding.includeFilter.isShowDay
    }

    fun onClickService(){
        mViewBinding.includeFilter.isShowService = !mViewBinding.includeFilter.isShowService
    }

}