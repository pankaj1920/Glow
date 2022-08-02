package com.developer.u_glow.view.fragment.booking

import android.os.Bundle
import android.os.Parcelable
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.base.app.utils.navigateTo
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.UGrowApp
import com.developer.u_glow.databinding.FragmentDashboardBinding
import com.developer.u_glow.model.dto.CategoryData
import com.developer.u_glow.model.dto.PostGlowData
import com.developer.u_glow.model.dto.SelectGlowData
import com.developer.u_glow.state.booking.DashboardState
import com.developer.u_glow.util.Constants
import com.developer.u_glow.viewmodel.booking.DashboardViewModel
import org.parceler.Parcels
import timber.log.Timber

class DashboardFragment(override val layoutId: Int = R.layout.fragment_dashboard) :
    BaseFragment<DashboardViewModel, FragmentDashboardBinding>() {
    var list = ArrayList<CategoryData>()
    override val mViewModel: DashboardViewModel by viewModels()

    override fun subscribeObservers() {

        mViewModel.stateObserver.observe(this, Observer {
            when (it) {
                is DashboardState.UpdateCategoryAdapter -> {
                    mViewBinding.rvCategory.adapter = it.adapter
                }

                is DashboardState.UpdateBookingAdapter -> {
                    mViewBinding.rvBooking.adapter = it.adapter
                }

                is DashboardState.NavigateToSubCategory -> {
                    findNavController().navigateTo(R.id.nav_pick_category_fragment, it.bundle)
                }
                is DashboardState.ShowMore -> {
                    mViewBinding.tvAllCategory.isVisible = it.showMore
                }

                else -> {

                }
            }
        })
    }

    fun onClickAllCategory() {
        mViewModel.showMore()
    }

    override fun onFragmentCreated() {
        mViewBinding.view = this
    }

    fun onClickHistory() {
        findNavController().navigateTo(R.id.nav_booking_fragment)
    }


}