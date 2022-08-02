package com.developer.u_glow.viewmodel.booking

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.base.app.model.State
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.adapter.SelectGlowAdapter
import com.developer.u_glow.adapter.AddServiceAdapter
import com.developer.u_glow.model.dto.PositionData
import com.developer.u_glow.model.dto.PostGlowData
import com.developer.u_glow.model.dto.ServiceData
import com.developer.u_glow.model.dto.Services
import com.developer.u_glow.state.booking.SelectGlowFragmentState
import com.developer.u_glow.util.Constants
import com.developer.u_glow.webservices.ModelRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber


class SelectGlowViewModel : BaseViewModel<SelectGlowFragmentState>() {
    private var selectGlowList: MutableList<ServiceData>? = ArrayList()
    var list: MutableList<ServiceData>? = ArrayList()
    private var selectGlowAdapter: SelectGlowAdapter? = null
    private var addServiceList: MutableList<ServiceData>? = ArrayList()
    private var addServiceAdapter: AddServiceAdapter? = null
    var id: Int? = null
    var position: ArrayList<PositionData>? = null

    var servicesList: MutableList<Services>? = ArrayList()
    private var state: SelectGlowFragmentState = SelectGlowFragmentState.Init
        set(value) {
            field = value
            publishState(state)
        }

    override fun onInitialized(bundle: Bundle?) {
//        initSelectGlow()


//        val dat = bundle?.getParcelableArrayList<Parcelable>("post") as ArrayList<SampleData>
//        Timber.d("dfatl ${dat.get(1).id}")

        if (bundle != null) {

            val postGlow = bundle.getParcelable<PostGlowData>(Constants.Key.post)
            getService(bundle.getString(Constants.Key.id)!!.toInt(), this)
            if (addServiceAdapter == null && postGlow?.servicesList!=null ) {
                addServiceAdapter =
                    AddServiceAdapter( postGlow?.servicesList as MutableList<Services>, this)
                state = SelectGlowFragmentState.SetAddServiceAdapter(addServiceAdapter!!)
//                Timber.d("checkingth ${postGlow}")
            }
            Timber.d("checkingth ${postGlow?.subId}")
            addServiceAdapter?.notifyDataSetChanged()

            id = bundle.getString(Constants.Key.id)!!.toInt()
            position = bundle.getParcelableArrayList<PositionData>(Constants.Key.position)!!
            state = SelectGlowFragmentState.SaveList(
                subId = postGlow?.subId,
                list = postGlow,
                selectedPositionList = position
            )
        }

    }

    private fun initSelectGlow() {

        selectGlowAdapter = SelectGlowAdapter(selectGlowList as ArrayList<ServiceData>, this)
        state = SelectGlowFragmentState.SetSelectGlowAdapter(selectGlowAdapter!!)

    }


    fun onClickAddData(data: ServiceData, context: Context) {

        state = SelectGlowFragmentState.SaveList(data = data)

    }

    fun deleteService(position: Int) {
        addServiceAdapter?.removeItem(position)
        addServiceAdapter?.notifyDataSetChanged()
        state = SelectGlowFragmentState.DeleteList(position)
    }

    private fun getService(id: Int, viewModel: SelectGlowViewModel) {

        viewModelScope.launch {

            ModelRepository(iRepositoryListener).getService(id).collect {
                when (it) {
                    is State.Success -> {
                        selectGlowAdapter =
                            SelectGlowAdapter(it.data.data as MutableList<ServiceData>, viewModel)
                        state = SelectGlowFragmentState.SetSelectGlowAdapter(selectGlowAdapter!!)
                    }
                    else -> {
                    }
                }
            }
        }
    }

    fun getId(): Int {
        if (id != null) {
            return id!!
        } else {
            return null!!
        }
    }

    fun getSubPosition(): List<PositionData> {
        if (position != null) {
            return position!!
        } else {
            return null!!
        }
    }
}