package com.developer.u_glow.view.fragment.booking

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.base.app.utils.loadCurvedImage
import com.base.app.utils.navigateTo
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentBookingDetailsBinding
import com.developer.u_glow.state.booking.BookingDetailState
import com.developer.u_glow.viewmodel.booking.BookingDetailViewModel

class BookingDetailFragment(override val layoutId: Int = R.layout.fragment_booking_details) :
    BaseFragment<BookingDetailViewModel, FragmentBookingDetailsBinding>() {

    override val mViewModel: BookingDetailViewModel by viewModels()

    override fun subscribeObservers() {

        mViewModel.stateObserver.observe(this, Observer {
            when (it) {

                is BookingDetailState.SetOfferAdapter -> {
                    mViewBinding.rvOffers.adapter = it.adapter
                }

                else -> {
                }
            }
        })

        mViewBinding.ivDetail.loadCurvedImage("https://docs.imagga.com/static/images/docs/sample/japan-605234_1280.jpg", resources.getDimension(R.dimen._20sdp))
    }


    override fun onFragmentCreated() {
        mViewBinding.view = this
    }

    fun onClickEdit() {
        findNavController().navigateTo(R.id.nav_detail_fragment)
    }

}