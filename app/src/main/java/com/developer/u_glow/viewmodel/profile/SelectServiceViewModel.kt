package com.developer.u_glow.viewmodel.profile

import android.os.Bundle
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.adapter.SelectServiceAdapter
import com.developer.u_glow.adapter.SelectSubServiceAdapter
import com.developer.u_glow.adapter.SelectSubServiceListAdapter
import com.developer.u_glow.model.dto.SelectServiceData
import com.developer.u_glow.model.dto.SelectSubServiceData
import com.developer.u_glow.state.profile.ProfileState
import com.developer.u_glow.state.profile.SelectServiceFragmentState
import com.developer.u_glow.util.Constants

class SelectServiceViewModel : BaseViewModel<SelectServiceFragmentState>() {

    private var selectServiceList: MutableList<SelectServiceData>? = null
    private var selectSubServiceList: MutableList<SelectSubServiceData>? = null
    var selectServiceAdapter: SelectServiceAdapter? = null
    private var selectSubServiceAdapter: SelectSubServiceAdapter? = null
    private var selectSubServiceListAdapter: SelectSubServiceListAdapter? = null
    var bundleInstance: Bundle? = null


    private var state: SelectServiceFragmentState = SelectServiceFragmentState.Init
        set(value) {
            field = value
            publishState(state)
        }

    override fun onInitialized(bundle: Bundle?) {
        if (bundle != null){
            bundleInstance = bundle
            if (bundle.containsKey(Constants.BundleKey.isShowProgress))
                state = SelectServiceFragmentState.IsShowProgress
        }
        initializeSelectService()
        initializeSelectSubService()
        initializeSelectSubServiceList()
    }

    private fun initializeSelectService() {

        selectServiceList = ArrayList()
        selectSubServiceList = ArrayList()
        selectSubServiceList?.add(SelectSubServiceData(""))
        selectSubServiceList?.add(SelectSubServiceData(""))
        selectSubServiceList?.add(SelectSubServiceData(""))

        selectServiceList?.add(SelectServiceData("" ))
        selectServiceList?.add(SelectServiceData(""))
        selectServiceList?.add(SelectServiceData(""))
        selectServiceAdapter =
            SelectServiceAdapter(selectServiceList as ArrayList<SelectServiceData>, this)
        state = SelectServiceFragmentState.UpdateSelectServiceAdapter(selectServiceAdapter)
    }

    fun initializeSelectSubService(): SelectSubServiceAdapter {

        selectSubServiceList = ArrayList()
        selectSubServiceList?.add(SelectSubServiceData(""))
        selectSubServiceList?.add(SelectSubServiceData(""))
        selectSubServiceList?.add(SelectSubServiceData(""))
        selectSubServiceAdapter =
            SelectSubServiceAdapter(selectSubServiceList as ArrayList<SelectSubServiceData>, this)
//        state = SelectServiceFragmentState.UpdateSelectSubServiceAdapter(selectSubServiceAdapter)

        return selectSubServiceAdapter!!
    }

    fun initializeSelectSubServiceList():SelectSubServiceListAdapter{

        selectSubServiceList = ArrayList()
        selectSubServiceList?.add(SelectSubServiceData(""))
        selectSubServiceList?.add(SelectSubServiceData(""))
        selectSubServiceList?.add(SelectSubServiceData(""))
        selectSubServiceListAdapter =
            SelectSubServiceListAdapter(selectSubServiceList as ArrayList<SelectSubServiceData>, this)

        return selectSubServiceListAdapter!!
    }

}