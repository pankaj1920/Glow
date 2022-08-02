package com.developer.u_glow.view.activity.onboarding

import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.base.app.view.BaseActivity
import com.developer.u_glow.R
import com.developer.u_glow.adapter.OnBoardingAdapter
import com.developer.u_glow.databinding.ActivityOnboardingBinding
import com.developer.u_glow.util.Constants
import com.developer.u_glow.viewmodel.onboarding.OnBoardViewModel
import timber.log.Timber

class OnBoardActivity(override val layoutId: Int = R.layout.activity_onboarding) :
    BaseActivity<OnBoardViewModel, ActivityOnboardingBinding>() {

    override val mViewModel: OnBoardViewModel by viewModels()

    override fun onInitialize() {
        initPager()
        mViewBinding.pos = Constants.Count.zero
        mViewBinding.view = this
    }

    private fun initPager() {
        val pagerAdapter = OnBoardingAdapter(this)
        pagerAdapter.addFragment(mViewModel.getOnBoardingFragment(Constants.Count.zero))
        pagerAdapter.addFragment(mViewModel.getOnBoardingFragment(Constants.Count.one))
        pagerAdapter.addFragment(mViewModel.getOnBoardingFragment(Constants.Count.two))
        mViewBinding.viewPager.adapter = pagerAdapter

        mViewBinding.pageIndicator.setViewPager2(mViewBinding.viewPager)

        mViewBinding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                mViewBinding.pos = position
            }
        })

    }

    override fun subscribeObservers() {
       /* mViewModel.stateObserver.observe(this, Observer {
            when (it) {

            }
        })*/
    }

    fun onClickSkip(){
        navigateTo(null, Constants.Activity.authenticate)
    }
}