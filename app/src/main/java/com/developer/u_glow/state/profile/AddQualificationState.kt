package com.developer.u_glow.state.profile

import com.developer.u_glow.adapter.AddQualificationAdapter

sealed class AddQualificationState{
    object Init: AddQualificationState()
    object IsShowProgress : AddQualificationState()

    data class UpdateAddQualificationAdapter(var adapter: AddQualificationAdapter?) : AddQualificationState()

}
