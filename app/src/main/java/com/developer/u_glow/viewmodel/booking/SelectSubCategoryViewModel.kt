package com.developer.u_glow.viewmodel.booking

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.base.app.model.State
import com.base.app.utils.Toaster
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.adapter.SubCategoryAdapter
import com.developer.u_glow.model.dto.CategoryData
import com.developer.u_glow.model.dto.PositionData
import com.developer.u_glow.model.dto.PostGlowData
import com.developer.u_glow.state.booking.SelectSubCategoryState
import com.developer.u_glow.util.Constants
import com.developer.u_glow.webservices.ModelRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.*
import kotlin.collections.ArrayList

class SelectSubCategoryViewModel : BaseViewModel<SelectSubCategoryState>() {

    private var categoryList: MutableList<CategoryData>? = null
    private var subCategoryAdapter: SubCategoryAdapter? = null
    var id: String? = null
    var subId: List<Int>? = null
    private var selectedPositionList: MutableList<PositionData>? = ArrayList()
    private var listData: ArrayList<PostGlowData>? = null

    private var state: SelectSubCategoryState = SelectSubCategoryState.Init
        set(value) {
            field = value
            publishState(state)
        }

    override fun onInitialized(bundle: Bundle?) {

        if (bundle != null) {
//            id = bundle.getString(Constants.Key.id)
            if (bundle.getString(Constants.Key.position) != null) {
                selectedPositionList = bundle.getParcelableArrayList(Constants.Key.position)!!
            }
           val postGlow = bundle.getParcelable<PostGlowData>(Constants.Key.post)
            id=postGlow?.id
            subId=postGlow?.subId
            Timber.d("categoryId ${postGlow?.subId}")
            if (postGlow!!.subId != null) {
                getSubCategory(postGlow.subId!!, this)
            }else{
                getSubCategory( viewModel = this)
            }

            state = SelectSubCategoryState.ReSendDataToService(
                postGlow = postGlow
            )
            Timber.d("selectedPosition ${postGlow}")

        }

    }

    fun onClickSubCategory(position: Int, data: CategoryData, context: Context? = null) {
        data.select = !data.select
        subCategoryAdapter?.notifyDataSetChanged()
        if (selectedPositionList?.isNotEmpty()!!) {
            selectedPositionList?.forEach {
                subCategoryAdapter?.setPreviousToTrue(it.subCategoryPosition!!, context!!)

            }

        }
        state = SelectSubCategoryState.NavigateToService(
            position = position,
            id = data.id!!,
            data,
            subId = subId
        )

    }

    private fun getSubCategory(subId: List<Int>?=null, viewModel: SelectSubCategoryViewModel) {

        viewModelScope.launch {
            ModelRepository(iRepositoryListener).getSubCategory(id!!.toInt()).collect {
                when (it) {
                    is State.Success -> {

                        categoryList = it.data.data

                        subId?.forEach {
                            categoryList!![it].select = true
                        }
                        subCategoryAdapter = SubCategoryAdapter(
                            it.data.data as MutableList<CategoryData>,
                            viewModel
                        )
                        state = SelectSubCategoryState.UpdateSubCategoryAdapter(subCategoryAdapter)
                    }
                    else -> {
                    }
                }
            }
        }
    }

    fun getFilterData(searchTxt: String, list: ArrayList<CategoryData>, context: Context) {
        val filterList: ArrayList<CategoryData> = ArrayList()

        list.forEach {
            if (it.name?.toLowerCase(Locale.ROOT)?.contains(searchTxt.toLowerCase(Locale.ROOT))!!) {
                filterList.add(it)
            } else {
                Toaster.show(context, "No result found")
            }
        }
        subCategoryAdapter?.updateList(filterList)
    }


    private fun findIndex(arr: ArrayList<PostGlowData>, item: PostGlowData): Int {
        return arr.indexOf(item)
    }

}