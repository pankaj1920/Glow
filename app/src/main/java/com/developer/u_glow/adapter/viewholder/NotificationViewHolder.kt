package com.developer.u_glow.adapter.viewholder

import com.base.app.adapter.BaseViewHolder
import com.developer.u_glow.databinding.InflateNotificationBinding
import com.developer.u_glow.model.dto.NotificationData

class NotificationViewHolder(
    view: InflateNotificationBinding,
    private var isWhite: Boolean? = false
) : BaseViewHolder<NotificationData, InflateNotificationBinding>(view) {
    override fun populateData(data: NotificationData) {
        viewBinding.isWhite = isWhite!!
    }
}