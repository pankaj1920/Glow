package com.developer.u_glow.state.booking

import com.developer.u_glow.adapter.AddServiceAdapter

sealed class GlowPostedFragmentState{
    object Init : GlowPostedFragmentState()
    data class SetAddServiceAdapter(val addServiceAdapter: AddServiceAdapter) : GlowPostedFragmentState()
}
