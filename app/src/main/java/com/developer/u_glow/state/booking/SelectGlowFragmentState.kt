package com.developer.u_glow.state.booking

import com.developer.u_glow.adapter.AddServiceAdapter
import com.developer.u_glow.adapter.SelectGlowAdapter
import com.developer.u_glow.model.dto.PositionData
import com.developer.u_glow.model.dto.PostGlowData
import com.developer.u_glow.model.dto.ServiceData

sealed class SelectGlowFragmentState {

    object Init : SelectGlowFragmentState()
    data class SetSelectGlowAdapter(var selectGlowAdapter: SelectGlowAdapter) :
        SelectGlowFragmentState()

    data class SetAddServiceAdapter(var addServiceAdapter: AddServiceAdapter) :
        SelectGlowFragmentState()

    data class SaveList(
        var subId:List<Int>?=null,
        var data: ServiceData? = null,
        var list: PostGlowData? = null,
        var selectedPositionList: MutableList<PositionData>? = null
    ) : SelectGlowFragmentState()

    data class DeleteList(
        var position: Int? = null
    ) : SelectGlowFragmentState()

}

