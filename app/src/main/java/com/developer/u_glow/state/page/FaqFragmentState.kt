package com.developer.u_glow.state.page

import com.developer.u_glow.adapter.FaqAdapter

sealed class FaqFragmentState {

    object Init : FaqFragmentState()
    data class UpdateFaqAdapter(var adapter: FaqAdapter?) : FaqFragmentState()

}
