package com.developer.u_glow.state.profile

import com.developer.u_glow.adapter.SelectServiceAdapter
import com.developer.u_glow.adapter.SelectSubServiceAdapter

sealed class SelectServiceFragmentState{

    object Init : SelectServiceFragmentState()
    object IsShowProgress : SelectServiceFragmentState()
    data class UpdateSelectServiceAdapter(var adapter: SelectServiceAdapter?) : SelectServiceFragmentState()
    data class UpdateSelectSubServiceAdapter(var adapter: SelectSubServiceAdapter?) : SelectServiceFragmentState()

}
