package com.developer.u_glow.state.profile

sealed class ProfileState{
    object Init : ProfileState()
    object IsShowProgress: ProfileState()
}
