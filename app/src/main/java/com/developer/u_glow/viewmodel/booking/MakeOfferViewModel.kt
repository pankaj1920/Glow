package com.developer.u_glow.viewmodel.booking

import android.os.Bundle
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.adapter.OfferAdapter
import com.developer.u_glow.model.dto.OfferData
import com.developer.u_glow.state.booking.MakeOfferState

class MakeOfferViewModel : BaseViewModel<MakeOfferState>() {

    private var offerList: MutableList<OfferData>? = null
    private var offerAdapter: OfferAdapter? = null

    private var state: MakeOfferState = MakeOfferState.Init
        set(value) {
            field = value
            publishState(state)
        }

    override fun onInitialized(bundle: Bundle?) {
        setOfferAdapter()
    }

    private fun setOfferAdapter() {
        offerList = ArrayList()
        offerList?.add(OfferData())
        offerList?.add(OfferData())
        offerList?.add(OfferData())
        offerAdapter = OfferAdapter(offerList as ArrayList<OfferData>)
        state = MakeOfferState.SetOfferAdapter(offerAdapter)
    }
}