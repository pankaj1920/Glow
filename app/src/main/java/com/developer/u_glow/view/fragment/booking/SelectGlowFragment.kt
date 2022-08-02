package com.developer.u_glow.view.fragment.booking

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.base.app.utils.Toaster
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.adapter.AddServiceAdapter
import com.developer.u_glow.databinding.FragmentSelectGlowBinding
import com.developer.u_glow.model.dto.PositionData
import com.developer.u_glow.model.dto.PostGlowData
import com.developer.u_glow.model.dto.Services
import com.developer.u_glow.state.booking.SelectGlowFragmentState
import com.developer.u_glow.util.Constants
import com.developer.u_glow.viewmodel.booking.SelectGlowViewModel
import timber.log.Timber


class SelectGlowFragment : BaseFragment<SelectGlowViewModel, FragmentSelectGlowBinding>() {
    override val mViewModel: SelectGlowViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_select_glow
    var adapter: AddServiceAdapter? = null
    private var addServiceList: PostGlowData? =null
    private var selectedPositionList: MutableList<PositionData>? = ArrayList()
    var servicesList: MutableList<Services>? = ArrayList()
    var subId:List<Int>? = null
    var id:Int? = null
    val bundle = Bundle()
    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this, Observer {
            when (it) {
                is SelectGlowFragmentState.SetSelectGlowAdapter -> {
                    mViewBinding.rvSelectGlow.adapter = it.selectGlowAdapter
                }
                is SelectGlowFragmentState.SetAddServiceAdapter -> {
                    mViewBinding.rvAddService.adapter = it.addServiceAdapter

                }
                is SelectGlowFragmentState.SaveList -> {
                    if (it.data != null) {
                        it.data?.let { data ->

                            servicesList?.add(Services(id = data.id, name = data.name))
                        }
                    } else {
                        subId = it.subId
                        Toaster.show(requireContext(),it.subId.toString())

                        it.list?.servicesList?.forEach {
                            servicesList?.add(it)
                        }
                        id=it.list?.id?.toInt()
                        selectedPositionList?.addAll(it.selectedPositionList!!)
                    }
                }
                is SelectGlowFragmentState.DeleteList -> {
                    servicesList?.removeAt(it.position!!)
                }
                else -> {

                }
            }
        })
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onFragmentCreated() {

        mViewBinding.viewModel = mViewModel
        mViewBinding.view = this
        mViewBinding.id = mViewModel.getId()
        mViewBinding.position = mViewModel.getSubPosition()
        mViewBinding.cardEnterDetailsWant.setOnTouchListener { p0, p1 ->
            if (p0?.id == R.id.cardEnterDetailsWant) {
                p0.parent.requestDisallowInterceptTouchEvent(true)
            }
            false
        }

    }

    fun onClickNavigation() {
        if (servicesList?.isNotEmpty()!!) {
            val bundle = Bundle()
            addServiceList=
                PostGlowData(
                    subId = subId, servicesList = servicesList
                )

            bundle.putParcelable(Constants.Key.post, addServiceList)
            findNavController().navigate(R.id.nav_detail_fragment,bundle)
        } else {
            Toaster.show(requireContext(), "please select item")
        }
    }

    fun onClickNavigationBack(id: Int) {
        addServiceList = PostGlowData(

                id=id.toString(),subId = subId, servicesList = servicesList
            )
        Timber.d("servicelist ${servicesList}")
        bundle.putString(Constants.Key.id, id.toString())
        bundle.putParcelable(Constants.Key.post, addServiceList)
        findNavController().popBackStack()
        findNavController().navigate(R.id.nav_select_sub_category_fragment, bundle)
    }

}