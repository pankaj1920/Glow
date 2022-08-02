package com.developer.u_glow.view.fragment.profile

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentYourProfileBinding
import com.developer.u_glow.state.profile.ReviewFragmentState
import com.developer.u_glow.viewmodel.profile.ReviewViewModel


class YourProfileFragment : BaseFragment<ReviewViewModel, FragmentYourProfileBinding>() {
    override val mViewModel: ReviewViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_your_profile

    override fun subscribeObservers() {

    }

    override fun onFragmentCreated() {
        mViewBinding.view = this
        mViewModel.stateObserver.observe(this, Observer {
            when (it) {
                is ReviewFragmentState.UpdateReviewAdapter -> {

                    mViewBinding.rvDashboardProfile.layoutManager =
                        LinearLayoutManager(requireContext())
                    mViewBinding.rvDashboardProfile.adapter = it.adapter
                }
                else -> {

                }
            }

        })

    }

    fun onClickNavigation() {
//        val action = YourProfileFragmentDirections.actionYourProfileFragmentToGlowAcceptedFragment()
//        findNavController().navigate(action)
    }

}