package com.developer.u_glow.adapter

import android.view.ViewGroup
import com.base.app.adapter.BaseRecyclerAdapter
import com.developer.u_glow.R
import com.developer.u_glow.adapter.viewholder.OfferViewHolder
import com.developer.u_glow.databinding.InflateOffersBinding
import com.developer.u_glow.model.dto.OfferData

class OfferAdapter(
    list: MutableList<OfferData>
) : BaseRecyclerAdapter<OfferData, OfferViewHolder>(list) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        return OfferViewHolder(
            inflateDataBinding(
                R.layout.inflate_offers,
                parent
            ) as InflateOffersBinding
        )
    }
}