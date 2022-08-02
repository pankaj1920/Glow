package com.developer.u_glow.viewmodel.page

import android.os.Bundle
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.adapter.FaqAdapter
import com.developer.u_glow.model.dto.FaqData
import com.developer.u_glow.state.page.FaqFragmentState

class FaqViewModel:BaseViewModel<FaqFragmentState>() {
    private var faqList: MutableList<FaqData>? = null
    private var faqAdapter: FaqAdapter? = null

    private var state: FaqFragmentState = FaqFragmentState.Init
        set(value) {
            field = value
            publishState(state)
        }
    override fun onInitialized(bundle: Bundle?) {
        initFaq()
    }

    private fun initFaq() {
        faqList = ArrayList()
        faqList?.add(FaqData("",""))
        faqList?.add(FaqData("",""))
        faqList?.add(FaqData("",""))
        faqAdapter = FaqAdapter(faqList as ArrayList<FaqData>)
        state= FaqFragmentState.UpdateFaqAdapter(faqAdapter)
    }

}