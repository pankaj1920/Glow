package com.developer.u_glow.adapter

import android.view.ViewGroup
import com.base.app.adapter.BaseRecyclerAdapter
import com.developer.u_glow.R
import com.developer.u_glow.adapter.viewholder.NotificationViewHolder
import com.developer.u_glow.databinding.InflateNotificationBinding
import com.developer.u_glow.model.dto.NotificationData

class NotificationAdapter(
    list: MutableList<NotificationData>,
    private var isWhite: Boolean? = false
) : BaseRecyclerAdapter<NotificationData, NotificationViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
        return NotificationViewHolder(
            inflateDataBinding(
                R.layout.inflate_notification,
                parent
            ) as InflateNotificationBinding, isWhite
        )
    }
}