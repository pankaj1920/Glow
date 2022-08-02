package com.base.app.utils

import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.text.TextUtils
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.exifinterface.media.ExifInterface
import com.base.app.R
import com.bumptech.glide.GenericTransitionOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import java.io.File

object GlideExtension {
    @BindingAdapter("imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url: String) {
        view.loadUrl(url)
    }

    @BindingAdapter("circleImageUrl")
    @JvmStatic
    fun loadCircleImage(view: ImageView, url: String) {
        view.loadCircleImage(url)
    }
}

fun ImageView.loadBitmapCircleImage(bitmap: Bitmap?) {
    if (bitmap != null) {
        Glide.with(context).load(bitmap)
            .transition(GenericTransitionOptions.with(R.anim.zoom_in_center))
            .apply(RequestOptions.circleCropTransform().error(R.drawable.ic_place_holder))
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            })
            .into(this)
    } else {
        Glide.with(context).load(R.drawable.ic_place_holder)
            .transition(GenericTransitionOptions.with(R.anim.zoom_in_center))
            .apply(RequestOptions.circleCropTransform())
            .into(this)
    }
}

fun ImageView.loadUrl(url: String?) {
    Glide.with(context).load(url)
        .transition(GenericTransitionOptions.with(R.anim.zoom_in_center))
        .apply(
            RequestOptions().placeholder(
                ContextCompat.getDrawable(
                    context, R.drawable.ic_place_holder
                )
            )/*.diskCacheStrategy(DiskCacheStrategy.NONE) // because file name is always same
                        .skipMemoryCache(true)*/
        )
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: com.bumptech.glide.load.DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

        })
        .into(this)
}


fun ImageView.loadCircleImage(url: String?) {
    if (!TextUtils.isEmpty(url)) {
        Glide.with(context).load(url)
            .transition(GenericTransitionOptions.with(R.anim.zoom_in_center))
            .apply(RequestOptions.circleCropTransform().error(R.drawable.ic_place_holder))
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: com.bumptech.glide.request.target.Target<Drawable>?,
                    dataSource: com.bumptech.glide.load.DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            })
            .into(this)
    } else {
        Glide.with(context).load(R.drawable.ic_place_holder)
            .transition(GenericTransitionOptions.with(R.anim.zoom_in_center))
            .apply(RequestOptions.circleCropTransform())
            .into(this)
    }
}

fun String.isCompatibleSize(): Boolean {
    val file = File(this)
    val fileSizeInBytes = file.length()
    // Convert the bytes to Kilobytes (1 KB = 1024 Bytes)
    val fileSizeInKB = fileSizeInBytes / 1024
    // Convert the KB to MegaBytes (1 MB = 1024 KBytes)
    val fileSizeInMB = fileSizeInKB / 1024

    return fileSizeInMB > 5
}

/**
 * Get Orientation of Uri
 * @param selectedImage
 * @param context
 */
fun getOrientation(uri: Uri, context: Context?): Int {
    var orientation = 0
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val projection = arrayOf(MediaStore.Images.Media.ORIENTATION)
        val cursor: Cursor? = context?.contentResolver?.query(uri, projection, null, null, null)
        if (cursor != null) {
            val orientationColumnIndex: Int =
                cursor.getColumnIndex(MediaStore.Images.Media.ORIENTATION)
            if (cursor.moveToFirst()) {
                orientation = if (cursor.isNull(orientationColumnIndex)) 0 else cursor.getInt(
                    orientationColumnIndex
                )
            }
            cursor.close()
        }
    } else {
        try {
            val exif = ExifInterface(uri.path ?: "")
            val imgOrientation: Int = exif.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )
            when (imgOrientation) {
                ExifInterface.ORIENTATION_ROTATE_270 -> orientation = 270
                ExifInterface.ORIENTATION_ROTATE_180 -> orientation = 180
                ExifInterface.ORIENTATION_ROTATE_90 -> orientation = 90
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    return orientation
}

/**
 * Rotate Bitmap with given angle
 * @param rotateAngel
 */
fun Bitmap.rotateBitmap(rotateAngel: Float): Bitmap? {
    val matrix = Matrix()
    matrix.postRotate(rotateAngel)
    return Bitmap.createBitmap(this, 0, 0, this.width, this.height, matrix, true)
}