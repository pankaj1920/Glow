package com.developer.u_glow.view.fragment.booking

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.base.app.utils.loadCurvedImage
import com.base.app.utils.navigateTo
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentMakeOffersBinding
import com.developer.u_glow.state.booking.MakeOfferState
import com.developer.u_glow.viewmodel.booking.MakeOfferViewModel
import kotlinx.android.synthetic.main.alert_forgot_password.view.*
import kotlinx.android.synthetic.main.alert_offer.view.*

class MakeOfferFragment(override val layoutId: Int = R.layout.fragment_make_offers) :
    BaseFragment<MakeOfferViewModel, FragmentMakeOffersBinding>() {

    override val mViewModel: MakeOfferViewModel by viewModels()

    override fun subscribeObservers() {

        mViewModel.stateObserver.observe(this, Observer {
            when (it) {

                is MakeOfferState.SetOfferAdapter -> {
                    mViewBinding.rvOffers.adapter = it.adapter
                }

                else -> {
                }
            }
        })

        mViewBinding.ivDetail.loadCurvedImage(
            "https://homepages.cae.wisc.edu/~ece533/images/tulips.png",
            resources.getDimension(R.dimen._20sdp)
        )
    }


    override fun onFragmentCreated() {
        mViewBinding.view = this
    }

    fun onClickMakeOffer() {
        val alert = createAlert(R.layout.alert_offer)
        alert.first.btnClose.setOnClickListener {
            findNavController().navigateTo(R.id.nav_glow_accept_fragment)
            alert.second.dismiss() }
        alert.second.show()
    }
}