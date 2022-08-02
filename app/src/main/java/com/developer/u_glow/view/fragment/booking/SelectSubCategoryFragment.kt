package com.developer.u_glow.view.fragment.booking

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.base.app.utils.Toaster
import com.base.app.utils.navigateTo
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentSubcategoryBinding
import com.developer.u_glow.model.dto.CategoryData
import com.developer.u_glow.model.dto.PositionData
import com.developer.u_glow.model.dto.PostGlowData
import com.developer.u_glow.model.dto.Services
import com.developer.u_glow.state.booking.SelectSubCategoryState
import com.developer.u_glow.util.Constants
import com.developer.u_glow.util.Dialog
import com.developer.u_glow.viewmodel.booking.SelectSubCategoryViewModel
import kotlinx.android.synthetic.main.alert_layout_item_exist.view.*
import timber.log.Timber


class SelectSubCategoryFragment(override val layoutId: Int = R.layout.fragment_subcategory) :
    BaseFragment<SelectSubCategoryViewModel, FragmentSubcategoryBinding>() {
    var list: ArrayList<CategoryData> = ArrayList()
    override val mViewModel: SelectSubCategoryViewModel by viewModels()
    var id: Int? = null
    var position: Int? = null
    var serviceList = PostGlowData()
    var selectedPosition: ArrayList<PositionData> = ArrayList()
    var services: ArrayList<Services>? = ArrayList()
    var postGlowData: PostGlowData? = null

    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this, Observer {
            when (it) {

                is SelectSubCategoryState.UpdateSubCategoryAdapter -> {
                    mViewBinding.rvCategory.adapter = it.adapter
                    list = it.adapter?.data as ArrayList<CategoryData>
                }


                is SelectSubCategoryState.NavigateToServiceFromAlert -> {
                    val bundle = Bundle()
                    bundle.putString(Constants.Key.id, it.id)
                    bundle.putParcelableArrayList(
                        Constants.Key.position,
                        it.selectedPositionList!! as ArrayList<PositionData>
                    )
                    bundle.putParcelable(
                        Constants.Key.post,
                        serviceList
                    )

//                    findNavController().popBackStack()
//                    findNavController().navigateTo(R.id.nav_select_glow_fragment, bundle)
                    Timber.d("checklist ${serviceList}")
                }


                is SelectSubCategoryState.ReSendDataToService -> {

                    if (it.postGlow?.servicesList != null) {
                        services?.clear()
                        it.postGlow!!.servicesList?.forEach {
                            services?.add(it)
                        }

                        serviceList =
                            PostGlowData(
                                id=postGlowData?.id,
                                subId=postGlowData?.subId,
                                numberOfPeople = postGlowData?.numberOfPeople,
                                isGroup = postGlowData?.isGroup, servicesList = services
                            )
                    }


                }
                is SelectSubCategoryState.NavigateToService -> {
                    id = it.id.toInt()
                    position = it.position
                    selectedPosition.add(PositionData(it.position))

                    it.subId?.forEach { subId->
                        if ( it.id.toInt()== subId) {
                            onClickShowAlert(it.id.toInt())
                        }
                    }
                    Toaster.show(requireContext()," ${it.id}  ${it.subId}")
                }
                is SelectSubCategoryState.DeleteService -> {
//                    serviceList?.removeAt(it.position!!)

                }
                else -> {
                }
            }
        })
        mViewBinding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {
                mViewModel.getFilterData(p0.toString(), list, requireContext())
            }
        })
    }

    override fun onFragmentCreated() {
        mViewBinding.view = this
    }

    fun navigateToService() {
        val bundle = Bundle()
        if (id != null) {
            bundle.putString(Constants.Key.id, id.toString())
            bundle.putParcelableArrayList(Constants.Key.position, selectedPosition)
            bundle.putParcelable(
                Constants.Key.post,
                serviceList
            )
            Timber.d("bottomlist ${serviceList}")
            findNavController().navigateTo(R.id.nav_select_glow_fragment, bundle)
        } else {
            Toaster.show(requireContext(), "please select item")
        }
    }


    private fun onClickShowAlert(id: Int, position: Int? = null) {
        val dialog = Dialog(context as Activity)
        val alert = dialog.createAlert(R.layout.alert_layout_item_exist)
        alert.first.btnAdd.setOnClickListener {
//            state = SelectSubCategoryState.NavigateToServiceFromAlert(
//                id.toString(),
//                selectedPositionList
//            )
            alert.second.cancel()
        }
        alert.first.btnRemove.setOnClickListener {

//            listData?.forEach {
//                if (it.subId!! == id) {
//                    state = SelectSubCategoryState.DeleteService(findIndex(listData!!, it))
//
//                }
//            }
//            val iterator = selectedPositionList?.iterator()
//            while (iterator?.hasNext()!!) {
//                val item = iterator.next()
//                if (item.subCategoryPosition == position) {
//                    iterator.remove()
//                }
//            }
            alert.second.cancel()
        }
        alert.second.show()
    }

}