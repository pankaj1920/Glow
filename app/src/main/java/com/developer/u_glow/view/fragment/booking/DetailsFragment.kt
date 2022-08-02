package com.developer.u_glow.view.fragment.booking


import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.base.app.utils.Toaster
import com.base.app.view.BaseFragment
import com.developer.u_glow.R
import com.developer.u_glow.databinding.FragmentDetailsBinding
import com.developer.u_glow.state.booking.DetailsState
import com.developer.u_glow.util.createImageFile
import com.developer.u_glow.util.initDatePicker
import com.developer.u_glow.util.initTimePicker
import com.developer.u_glow.util.requestPermission
import com.developer.u_glow.viewmodel.booking.DetailsViewModel
import kotlinx.android.synthetic.main.alert_image_option.view.*
import timber.log.Timber
import java.io.File


class DetailsFragment : BaseFragment<DetailsViewModel, FragmentDetailsBinding>() {
    override val mViewModel: DetailsViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_details
    private var readPermissionGranted = false
    private var writePermissionGranted = false
    private var uri: Uri? = null


    private var permissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            readPermissionGranted =
                it[android.Manifest.permission.READ_EXTERNAL_STORAGE] ?: readPermissionGranted


            writePermissionGranted =
                it[android.Manifest.permission.WRITE_EXTERNAL_STORAGE] ?: writePermissionGranted

        }

    override fun subscribeObservers() {

        mViewModel.stateObserver.observe(this, Observer {
            when (it) {
                is DetailsState.UpdateGalleryAdapter -> {
                    mViewBinding.rvGallery.adapter = it.adapter
                }
                else -> {
                }
            }

        })
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onFragmentCreated() {
        mViewBinding.view = this
        mViewBinding.listner=mViewModel
        mViewBinding.time="time"
        mViewBinding.noTime="noTime"
        mViewBinding.date="date"
        mViewBinding.nodate="noDate"

        mViewBinding.cardEnterDetailsWant.setOnTouchListener { p0, p1 ->
            if (p0?.id == R.id.cardEnterDetailsWant) {
                p0.parent.requestDisallowInterceptTouchEvent(true)
            }
            false
        }


        mViewBinding.edtYourAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mViewBinding.selected = true
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })

        mViewBinding.spinnerFlexible.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val value = p0!!.getItemAtPosition(p2).toString()
                hideFlexible(value)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        mViewBinding.spinnerFlexible.setPopupBackgroundResource(R.color.chartColor)
    }

    private fun hideFlexible(status:String) {
        mViewBinding.edtThisTime.isVisible = status.equals("yes",true)
        mViewBinding.edtThisDay.isVisible = status.equals("yes",true)
        mViewBinding.edtAnyDay.isVisible = status.equals("yes",true)
        mViewBinding.edtAnyTime.isVisible = status.equals("yes",true)
    }

    fun onClickNavigation() {
        findNavController().navigate(R.id.nav_glow_posted_fragment)
    }


    fun onClickChangeColor() {
        mViewBinding.selected = !mViewBinding.selected
    }

    fun onClickChangeAnyTimeColor(date:String) {
        mViewBinding.selectedAny = !mViewBinding.selectedAny
        if (date.equals("date")){
            context?.initDatePicker(mViewBinding.edtThisDay)
        }

    }
    fun onClickChangeAnyDayColor(time:String) {
        mViewBinding.selectedOn = !mViewBinding.selectedOn
        if (time.equals("time")){
            context?.initTimePicker(mViewBinding.edtThisTime)
        }
    }


    private val pickImage =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { it ->
            Timber.d("checking data ${it.data}")
            it.data?.let {
                mViewModel.initGallery(it.data)
            }
            if (it.resultCode == -1) {
                uri?.let {
                    mViewModel.initGallery(it)
                    uri = null
                }
            }
        }


    private fun captureImage() {

        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(requireContext().packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    context?.createImageFile()
                } catch (e: Exception) {
                    // Error occurred while creating the File
                    Toast.makeText(requireContext(), "null", Toast.LENGTH_LONG)
                        .show()
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also { file ->
                    val photoUri: Uri = FileProvider.getUriForFile(
                        requireContext(),
                        "com.developer.u_glow",
                        file
                    )
                    uri = photoUri
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                    pickImage.launch(takePictureIntent)
                }
            }
        }
    }

    fun showCalendar() {
        context?.initDatePicker(mViewBinding.edtDate)
    }


    fun showTime() {
        context?.initTimePicker(mViewBinding.edtTime)
    }

    fun pickImageAlert(){

        val alert = createAlert(R.layout.alert_image_option)
        alert.first.btnCapture.setOnClickListener {
            if (context?.requestPermission(
                    readPermissionGranted,
                    writePermissionGranted,
                    permissionLauncher
                )!!
            ) {
                captureImage()
            } else {
                context?.requestPermission(
                    readPermissionGranted,
                    writePermissionGranted,
                    permissionLauncher
                )
            }
            alert.second.dismiss()
        }
        alert.first.btnGallery.setOnClickListener {

            if (context?.requestPermission(
                    readPermissionGranted,
                    writePermissionGranted,
                    permissionLauncher
                )!!
            ) {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "image/*"
                pickImage.launch(intent)
            } else {
                context?.requestPermission(
                    readPermissionGranted,
                    writePermissionGranted,
                    permissionLauncher
                )
            }
            alert.second.dismiss()
        }
        alert.second.show()
    }


}