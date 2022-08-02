package com.base.app.webservice

object BaseMapping {

    fun getUrl(feature: Feature): String {
        return when (feature) {
            Feature.UserService -> "http://doodlebluelive.com:2385/"
        }
    }
}

sealed class Feature {
    object UserService : Feature()
}