package com.developer.u_glow.view.fragment.page

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentFaqBinding
import com.developer.u_glow.state.page.FaqFragmentState
import com.developer.u_glow.viewmodel.page.FaqViewModel

class FaqFragment : BaseFragment<FaqViewModel,FragmentFaqBinding>() {
    override val mViewModel: FaqViewModel by viewModels()
    override val layoutId: Int
        get() =R.layout.fragment_faq

    override fun subscribeObservers() {

    }

    override fun onFragmentCreated() {

        mViewBinding.view=this
        mViewModel.stateObserver.observe(this, Observer {
            when (it) {
                is FaqFragmentState.UpdateFaqAdapter -> {
                    mViewBinding.rvFaq.adapter= it.adapter
                }

                else-> {}
            }
        })

    }
    fun onClickNavigation(){
    }

}