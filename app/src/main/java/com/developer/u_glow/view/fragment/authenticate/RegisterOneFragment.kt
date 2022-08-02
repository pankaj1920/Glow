package com.developer.u_glow.view.fragment.authenticate

import android.view.View
import android.widget.AdapterView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.adapter.PeopleAdapter
import com.developer.u_glow.databinding.FragmentRegisterOneBinding
import com.developer.u_glow.model.dto.PeopleData
import com.developer.u_glow.state.authenticate.RegisterOneState
import com.developer.u_glow.state.booking.PickCategoryState
import com.developer.u_glow.viewmodel.authenticate.RegisterOneViewModel

class RegisterOneFragment(override val layoutId: Int = R.layout.fragment_register_one) :
    BaseFragment<RegisterOneViewModel, FragmentRegisterOneBinding>() {

    override val mViewModel: RegisterOneViewModel by viewModels()

    override fun subscribeObservers() {

        mViewModel.stateObserver.observe(this, Observer {
            when (it) {

                is RegisterOneState.UpdateUserType -> {
                   mViewBinding.isCustomer = it.isCustomer
                }

                else -> {
                }
            }
        })
    }

    override fun onFragmentCreated() {
        val list = ArrayList<PeopleData>()
        list.add(PeopleData(name = resources.getString(R.string.online)))
        mViewBinding.etHear.adapter = PeopleAdapter(requireContext(), list)
        mViewBinding.etHear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
//                mViewModel.updateHear(list[position].name)
            }

        }
    }
}