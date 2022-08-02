package com.base.app.utils

import android.os.Bundle
import androidx.navigation.NavController

fun NavController.navigateNew(id: Int, graphId: Int, bundle: Bundle? = null) {
    this.popBackStack(graphId, true)
    if (bundle != null)
        this.navigate(id, bundle)
    else
        this.navigate(id)
}

fun NavController.navigateTo(id: Int, bundle: Bundle? = null){
    if (bundle != null)
        this.navigate(id, bundle)
    else
        this.navigate(id)
}