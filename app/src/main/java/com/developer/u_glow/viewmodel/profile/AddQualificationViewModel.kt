package com.developer.u_glow.viewmodel.profile

import android.os.Bundle
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.adapter.AddQualificationAdapter
import com.developer.u_glow.model.dto.AddQualificationData
import com.developer.u_glow.state.profile.AddQualificationState
import com.developer.u_glow.state.profile.ProfileState
import com.developer.u_glow.util.Constants

class AddQualificationViewModel:BaseViewModel<AddQualificationState>() {

    private var qualificationList: MutableList<AddQualificationData>? = ArrayList()
    private var addQualificationAdapter: AddQualificationAdapter? = null
    var bundleInstance: Bundle? = null

    private var state: AddQualificationState = AddQualificationState.Init
        set(value) {
            field = value
            publishState(state)
        }
    override fun onInitialized(bundle: Bundle?) {
        if (bundle != null){
            bundleInstance = bundle
            if (bundle.containsKey(Constants.BundleKey.isShowProgress))
                state = AddQualificationState.IsShowProgress
        }
        initAddQualification()
    }

     fun initAddQualification() {
        qualificationList?.add(AddQualificationData())
        if (addQualificationAdapter==null){
            addQualificationAdapter = AddQualificationAdapter(qualificationList as ArrayList<AddQualificationData>)
            state=
                AddQualificationState.UpdateAddQualificationAdapter(addQualificationAdapter)
        }
        addQualificationAdapter?.notifyDataSetChanged()
    }

}