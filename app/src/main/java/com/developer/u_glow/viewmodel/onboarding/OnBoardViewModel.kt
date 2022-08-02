package com.developer.u_glow.viewmodel.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.R
import com.developer.u_glow.adapter.OnBoardingAdapter
import com.developer.u_glow.state.onboarding.OnBoardState
import com.developer.u_glow.util.Constants
import com.developer.u_glow.view.fragment.OnBoardingFragment

class OnBoardViewModel : BaseViewModel<OnBoardState>() {

    private var pagerAdapter: OnBoardingAdapter? = null

    private var onBoardState: OnBoardState = OnBoardState.Init
        set(value) {
            field = value
            publishState(onBoardState)
        }

    override fun onInitialized(bundle: Bundle?) {
    }


    /**Passing respective position in bundle for each fragment**/
    fun getOnBoardingFragment(position: Int): Fragment {
        val fragment = OnBoardingFragment()
        val bundle = Bundle()
        bundle.putInt(Constants.BundleKey.position, position)
        fragment.arguments = bundle
        return fragment
    }
}