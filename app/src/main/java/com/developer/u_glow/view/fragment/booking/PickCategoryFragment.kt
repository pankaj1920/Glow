package com.developer.u_glow.view.fragment.booking

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.base.app.utils.navigateTo
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.adapter.PeopleAdapter
import com.developer.u_glow.databinding.FragmentPickCategoryBinding
import com.developer.u_glow.model.dto.CategoryData
import com.developer.u_glow.model.dto.PostGlowData
import com.developer.u_glow.state.booking.PickCategoryState
import com.developer.u_glow.util.Constants

import com.developer.u_glow.viewmodel.booking.PickCategoryViewModel
import org.parceler.Parcels
import timber.log.Timber


class PickCategoryFragment(override val layoutId: Int = R.layout.fragment_pick_category) :
    BaseFragment<PickCategoryViewModel, FragmentPickCategoryBinding>() {
    var list: ArrayList<CategoryData> = ArrayList()
    override val mViewModel: PickCategoryViewModel by viewModels()
    var position: String? = null
    var forMe: Boolean? = false
    var id: String? = null
    var numberOfPeople: Int? = null
    var isGroup: Boolean? = false
    val bundle = Bundle()
    var postGlow: PostGlowData? = null
    override fun subscribeObservers() {
        mViewModel.stateObserver.observe(this, Observer {
            when (it) {

                is PickCategoryState.SetPeopleAdapter -> {
                    mViewBinding.spinner.adapter = PeopleAdapter(requireContext(), it.list)
                }

                is PickCategoryState.UpdatePickCategoryAdapter -> {
                    mViewBinding.rvCategory.adapter = it.adapter
                    list = it.adapter?.data as ArrayList<CategoryData>
                }
                is PickCategoryState.HideAndShowForMe -> {
                    Timber.d("cateId ${it.id}")
                    hideAndShowForMe(it.forMe, it.forGroup)
                    mViewBinding.tvGlowHistory.setOnClickListener { view ->



                            postGlow = PostGlowData(
                                id = it.id,
                                numberOfPeople = numberOfPeople,
                                isGroup = isGroup
                            )
                            bundle.putParcelable(Constants.Key.post, postGlow)
                            findNavController().navigateTo(
                                R.id.nav_select_sub_category_fragment,
                                bundle
                            )

                        Timber.d("postGlowPick ${ it.id}  ${postGlow}")
                    }
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
        mViewBinding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val value = p0!!.getItemAtPosition(p2).toString()
                if (value.equals("ONE", true)) {
                    numberOfPeople = 1
                } else {
                    numberOfPeople = 2
                }

                isGroup = !value.equals("ONE", true)

            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

    }

    private fun hideAndShowForMe(forMe: Boolean, forGroup: Boolean) {
        mViewBinding.ivGroup.isVisible = forGroup
        mViewBinding.tvGroup.isVisible = forGroup
        mViewBinding.tvPeople.isVisible = forGroup
        mViewBinding.spinner.isVisible = forGroup
        mViewBinding.ivJustMe.isVisible = forMe
        mViewBinding.tvJustMe.isVisible = forMe
    }


}