package com.developer.u_glow.view.fragment.page


import androidx.fragment.app.viewModels
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentContactUsBinding
import com.developer.u_glow.viewmodel.page.ContactUsViewModel

class ContactUsFragment : BaseFragment<ContactUsViewModel,FragmentContactUsBinding>() {
    override val mViewModel: ContactUsViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_contact_us

    override fun subscribeObservers() {

    }

    override fun onFragmentCreated() {

        mViewBinding.view=this
    }

    fun onClickNavigation(){
//        val action= ContactUsFragmentDirections.actionContactUsFragmentToAddQualificationFragment()
//        findNavController().navigate(action)
    }

}