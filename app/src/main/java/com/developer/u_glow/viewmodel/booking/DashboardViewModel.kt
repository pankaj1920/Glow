package com.developer.u_glow.viewmodel.booking

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.base.app.model.State
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.webservices.ModelRepository
import com.developer.u_glow.adapter.BookingAdapter
import com.developer.u_glow.adapter.CategoryAdapter
import com.developer.u_glow.model.dto.CategoryData
import com.developer.u_glow.model.dto.OpenBookingData
import com.developer.u_glow.model.dto.PostGlowData
import com.developer.u_glow.model.request.SampleRequest
import com.developer.u_glow.state.booking.DashboardState
import com.developer.u_glow.util.Constants
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class DashboardViewModel : BaseViewModel<DashboardState>() {

    private var categoryList: MutableList<CategoryData>? = null
    private var categoryAdapter: CategoryAdapter? = null
    private var bookingList: MutableList<OpenBookingData>? = null
    private var bookingAdapter: BookingAdapter? = null
    val bundle = Bundle()
    var postGlow:PostGlowData?=null
    private var state: DashboardState = DashboardState.Init
        set(value) {
            field = value
            publishState(state)
        }

    override fun onInitialized(bundle: Bundle?) {

        getAllCategory()
        getOpenBooking()
    }

    private fun initBooking() {
        bookingList = ArrayList()
        for (i in 1..5) {
            bookingList?.add(OpenBookingData())
        }
        bookingAdapter = BookingAdapter(
            bookingList as ArrayList<OpenBookingData>,
            true,
            Constants.GlowType.booking,
            this
        )
        state = DashboardState.UpdateBookingAdapter(bookingAdapter)
    }

    fun onClickCategory(position: Int, data: CategoryData) {

          postGlow = PostGlowData(
            position,
            data.forMe!!,
            data.forGroup!!,
            data.id!!
        )
        bundle.putParcelable(Constants.Key.post, postGlow)
        state = DashboardState.NavigateToSubCategory(data, position,bundle)
        data.select != data.select
    }

    /**Sample api integration**/
    fun performSampleApi() {
        val sampleRequest = SampleRequest()
        viewModelScope.launch {
            ModelRepository(iRepositoryListener)
                .updateSampleInfo(sampleRequest).collect {
                    when (it) {
                        is State.Success -> {
                            /** it.data will have the response object **/
                        }

                        else -> {
                        }
                    }
                }
        }
    }

    private fun getAllCategory() {

        viewModelScope.launch {
            ModelRepository(iRepositoryListener).getAllCategory().collect {

                when (it) {
                    is State.Success -> {
                        it.data.data?.let {
                            state = if (it.size > 9) {
                                DashboardState.ShowMore(true)
                            } else {
                                DashboardState.ShowMore(false)
                            }
                            categoryList = it
                        }

                        categoryAdapter = CategoryAdapter(
                            it.data.data as MutableList<CategoryData>,
                            this@DashboardViewModel
                        )
                        state = DashboardState.UpdateCategoryAdapter(categoryAdapter)
                    }

                    else -> {

                    }
                }
            }
        }
    }


    fun showMore() {
        categoryAdapter?.setLoadMore(true, categoryList!!)
    }

    fun getOpenBooking() {


        viewModelScope.launch {
            ModelRepository(iRepositoryListener).getOpenBooking()
                .collect {

                    when (it) {
                        is State.Success -> {
                            it.data.data?.let {
                                bookingList = it as MutableList<OpenBookingData>
                            }

                            bookingAdapter = BookingAdapter(
                                bookingList as ArrayList<OpenBookingData>,
                                true,
                                Constants.GlowType.booking,
                                this@DashboardViewModel
                            )
                            state = DashboardState.UpdateBookingAdapter(bookingAdapter)

                        }
                        is State.Error -> {
                            Timber.d("error ${it.message}")
                        }
                        else -> {

                        }
                    }
                }
        }
    }
}