package com.developer.u_glow.viewmodel.booking

import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.viewModelScope
import com.base.app.model.State
import com.base.app.viewmodel.BaseViewModel
import com.developer.u_glow.adapter.AddServiceAdapter
import com.developer.u_glow.adapter.GalleryAdapter
import com.developer.u_glow.model.dto.GalleryData
import com.developer.u_glow.model.dto.PostGlowData
import com.developer.u_glow.model.dto.Services
import com.developer.u_glow.model.request.PostGlowRequest
import com.developer.u_glow.state.booking.DetailsState
import com.developer.u_glow.state.booking.SelectGlowFragmentState
import com.developer.u_glow.util.Constants
import com.developer.u_glow.webservices.ModelRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class DetailsViewModel : BaseViewModel<DetailsState>() {

    private var galleryList: MutableList<GalleryData>? = ArrayList()
    private var galleryAdapter: GalleryAdapter? = null
    var postGlow: ArrayList<PostGlowData>? = null
    private var state: DetailsState = DetailsState.Init
        set(value) {
            field = value
            publishState(state)
        }

    override fun onInitialized(bundle: Bundle?) {
//        getOpenBooking()
        if (bundle!=null){
            postGlow =
                bundle.getParcelableArrayList<PostGlowData>(Constants.Key.post) as ArrayList<PostGlowData>
                Timber.d("checkingth ${postGlow}")
        }
    }

    fun initGallery(data: Uri? = null) {
        galleryList?.add(GalleryData(data))
        if (galleryAdapter == null) {
            galleryAdapter = GalleryAdapter(galleryList as ArrayList<GalleryData>, this)
            state = DetailsState.UpdateGalleryAdapter(galleryAdapter)
        }
        galleryAdapter?.notifyDataSetChanged()
    }

    fun deleteItem(position: Int) {
        galleryAdapter?.removeItem(position)
        galleryAdapter?.notifyDataSetChanged()

    }


    fun  postGlow() {
       val services = ArrayList<Int>()
       postGlow?.get(0)?.servicesList?.forEach{
           services.add(it.id!!)
       }

        val imageUrl = listOf<String>("im1","im2")
        val postGlowRequest = PostGlowRequest(
            userId=1,
            address="chenai", alternativeDate = "200", alternativeTime = "3", amount = "300",
            date = "2021-08-09", detail = "jkljf",
            isGroup = false, latitude = "8909".toDouble(), longitude = "898989".toDouble(),
            numberOfPeople = 3,otherService = "jlkjljljf",time = "3",services = services, imageUrl = imageUrl
        )
        viewModelScope.launch {
            ModelRepository(iRepositoryListener).postGlow(postGlowRequest)
                .collect {

                    when (it) {
                        is State.Success -> {
                            Timber.d("post Success")
                        }
                        is State.Error -> {
                            Timber.d("errorPost ${it.message}")
                        }

                        else -> {
                        }
                    }
                }
        }
    }

}