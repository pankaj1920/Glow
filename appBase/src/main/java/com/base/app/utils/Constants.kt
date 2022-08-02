package com.base.app.utils

interface Constants {


    interface SharedKey {
        companion object {
            const val USER_DATA = "userData"
            const val TOKEN = "token"
            const val FCM_TOKEN = "fcmToken"
            const val ON_BOARDING_VIEWED = "onBoardingViewed"
            const val IS_CUSTOMER = "isCustomer"
        }
    }

    interface BaseKeys {
        companion object {
            const val SHOW_LOADER = "showLoader"
            const val SHOW_LOADER_MESSAGE = "showLoaderMessage"
            const val HIDE_LOADER = "hideLoader"
            const val SHOW_MESSAGE = "showMessage"
            const val APP = "com.developer.u_glow"
        }
    }

    interface Header {
        companion object {
            const val AUTHORIZATION = "Authorization"
        }
    }

    interface InternalHttpCode {
        companion object {
            const val SUCCESS = 200
            const val ACCEPTED = 202
            const val FAILURE_CODE = 404
            const val BAD_REQUEST_CODE = 400
            const val UNAUTHORIZED_CODE = 401
            const val INTERNAL_SERVER_ERROR_CODE = 500
            const val TIMEOUT_CODE = 504
        }
    }

    interface HttpErrorMessage {
        companion object {

            const val NO_INTERNET = "No internet connection"
            const val INTERNAL_SERVER_ERROR =
                "Our server is under maintenance. We will resolve shortly!"
        }

    }
    interface  AuthKey{
        companion object{
            const val AUTH_KEY="eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6Miwicm9sZUlkIjozLCJpYXQiOjE2MjgwNTcyMjd9.cdGGIUyWECI_Dq16cbJrHjEJ7wh5GPwktFBgHtbs-6E"
        }
    }
}

