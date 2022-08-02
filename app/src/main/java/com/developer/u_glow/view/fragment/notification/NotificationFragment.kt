package com.developer.u_glow.view.fragment.notification

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentNotificationBinding
import com.developer.u_glow.state.notification.NotificationFragmentState
import com.developer.u_glow.viewmodel.notification.NotificationViewModel


class NotificationFragment : BaseFragment<NotificationViewModel, FragmentNotificationBinding>(){
    override val mViewModel:NotificationViewModel by viewModels()
    override val layoutId: Int
        get() =R.layout.fragment_notification

    override fun subscribeObservers() {

    }

    override fun onFragmentCreated() {

        mViewModel.stateObserver.observe(this, Observer {
            when (it) {
                is NotificationFragmentState.UpdateNotificationAdapter -> {
                    mViewBinding.rvNotification.adapter= it.adapter
                }

                else-> {}
            }
        })

    }


}