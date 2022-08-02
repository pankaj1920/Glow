package com.developer.u_glow.viewmodel.booking

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import androidx.lifecycle.viewModelScope
import com.base.app.model.State
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.adapter.CategoryAdapter
import com.developer.u_glow.model.dto.CategoryData
import com.developer.u_glow.model.dto.PeopleData
import com.developer.u_glow.model.dto.PostGlowData
import com.developer.u_glow.state.booking.PickCategoryState
import com.developer.u_glow.util.Constants
import com.developer.u_glow.webservices.ModelRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import kotlin.collections.ArrayList

class PickCategoryViewModel : BaseViewModel<PickCategoryState>() {

    private var categoryList: ArrayList<CategoryData>? = null
    private var categoryAdapter: CategoryAdapter? = null
    var list: ArrayList<PeopleData>? = null
    var selectedItemPos = -1
    var previousPos = -1
    var postGlow: PostGlowData? = null
    var bundle:Bundle?=null

    private var state: PickCategoryState = PickCategoryState.Init
        set(value) {
            field = value
            publishState(state)
        }

    override fun onInitialized(bundle: Bundle?) {
        initPeopleAdapter()


        if (bundle != null) {
            postGlow = bundle.getParcelable<Parcelable>(Constants.Key.post) as PostGlowData
            if (previousPos == -1) {
                previousPos = postGlow!!.position!!.toInt()
            }

            getAllCategory()
            state = PickCategoryState.HideAndShowForMe(
                postGlow!!.forMe!!,
                postGlow!!.forGroup!!,
               id= postGlow!!.id
            )
        }
    }


    private fun initPeopleAdapter() {
        list = ArrayList<PeopleData>()
        list?.add(PeopleData(name = "ONE"))
        list?.add(PeopleData(name = "TWO"))
        state = PickCategoryState.SetPeopleAdapter(list!!)
    }


    fun getAllCategory() {

        viewModelScope.launch {
            ModelRepository(iRepositoryListener).getAllCategory()
                .collect {

                    when (it) {
                        is State.Success -> {

                            categoryList = it.data.data as ArrayList<CategoryData>
                            viewModelScope.launch {
                                categoryList!!.forEach {
                                    if (it.id == postGlow?.id) {
                                        it.select = true
                                    }
                                }
                            }
                            categoryAdapter = CategoryAdapter(
                                categoryList!!,
                                this@PickCategoryViewModel, true
                            )
                            state = PickCategoryState.UpdatePickCategoryAdapter(categoryAdapter)

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

    fun onClickSubCategory(position: Int, data: CategoryData, lastPos: Int? = null) {
        data.select = !data.select
        if (previousPos != -1) {
            categoryAdapter?.setPreviousToTrue(previousPos)
        }
        previousPos = position

        state = PickCategoryState.HideAndShowForMe(
            data.forMe!!,
            data.forGroup!!,
            id = data.id,
        )

    }


    fun getFilterData(search: String, list: ArrayList<CategoryData>, context: Context) {
        val filterList: ArrayList<CategoryData> = ArrayList()
        viewModelScope.launch {
            list.forEach {
                if (it.name!!.toLowerCase().contains(search)) {
                    filterList.add(it)
                    categoryAdapter?.updateList(filterList)
                    Timber.d("contains")
                } else {
                    categoryAdapter?.updateList(filterList)
                }
            }
        }

    }

}