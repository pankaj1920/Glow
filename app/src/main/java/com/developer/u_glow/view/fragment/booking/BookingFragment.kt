package com.developer.u_glow.view.fragment.booking

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.base.app.utils.navigateTo
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentBookingBinding
import com.developer.u_glow.state.booking.BookingState
import com.developer.u_glow.viewmodel.booking.BookingViewModel

class BookingFragment(override val layoutId: Int = R.layout.fragment_booking) :
    BaseFragment<BookingViewModel, FragmentBookingBinding>() {

    override val mViewModel: BookingViewModel by viewModels()

    override fun subscribeObservers() {

        mViewModel.stateObserver.observe(this, Observer {
            when (it) {

                is BookingState.UpdateBookingAdapter -> {
                    mViewBinding.rvBooking.adapter = it.adapter
                }

                is BookingState.NavigateToDetail -> {
                    findNavController().navigateTo(R.id.nav_booking_detail_fragment)
                }

                else -> {
                }
            }
        })
    }


    override fun onFragmentCreated() {
        mViewBinding.view = this
    }

    fun onClickOption(position: Int){
        mViewBinding.position = position
    }
}