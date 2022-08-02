package com.developer.u_glow.state.onboarding

sealed class OnBoardFragmentState {
    object Init : OnBoardFragmentState()
    data class UpdateOnBoardData(val pos: Int): OnBoardFragmentState()
}