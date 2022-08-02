package com.developer.u_glow.state.booking

import com.developer.u_glow.adapter.AddServiceAdapter

sealed class AddServiceFragmentState{
    object Init : AddServiceFragmentState()
    data class SetAddServiceAdapter(val addServiceAdapter: AddServiceAdapter) : AddServiceFragmentState()
}
