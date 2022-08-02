package com.base.app.webservice

import android.os.Looper
import com.base.app.listener.IRepositoryListener
import com.base.app.model.ErrorResponse
import com.base.app.model.State
import com.base.app.utils.Constants
import com.base.app.utils.Constants.HttpErrorMessage.Companion.INTERNAL_SERVER_ERROR
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import timber.log.Timber
import java.io.IOException


/**
 * A repository which provides resource from local database as well as remote end point.
 *
 * [RESULT] represents the type for database.
 * [REQUEST] represents the type for network.
 */

abstract class NetworkBoundRepository<RESULT>(
    var iRepositoryListener: IRepositoryListener?,
    private val isShowProgress: Boolean = true
) {

    fun asFlow() = flow<State<RESULT>> {

        // Emit Loading State

        if (iRepositoryListener?.isNetworkConnected()!!) {

            if (isShowProgress) {
                iRepositoryListener?.showLoader()
            }

            // Emit Database content first
            // Fetch latest posts from remote
            val apiResponse: Response<RESULT>

            // Parse body
            val remotePosts: RESULT?

            withContext(Dispatchers.IO) {
                apiResponse = fetchData()
                remotePosts = apiResponse.body()
            }

            iRepositoryListener?.hideLoader()

            // Check for response validation
            if (apiResponse.isSuccessful && remotePosts != null) {
                // Save posts into the persistence storage
                if (apiResponse.code() == Constants.InternalHttpCode.SUCCESS || apiResponse.code() == Constants.InternalHttpCode.ACCEPTED)
                    emit(State.success(remotePosts))
                else {
                    emit(State.error(apiResponse.message()))
                    iRepositoryListener?.showMessage(apiResponse.message())
                }
            } else if (apiResponse.code() == Constants.InternalHttpCode.UNAUTHORIZED_CODE) {
                iRepositoryListener?.unAuthorizedUser()
            } else {
                // Something went wrong! Emit Error state.
                val error: ErrorResponse? =
                    getObjectFromJsonString(
                        apiResponse.errorBody()?.string()!!,
                        ErrorResponse::class.java
                    )

                /*val jObjError = JSONObject(apiResponse.errorBody()?.string()!!)
                iRepositoryListener?.showMessage(jObjError.getString("message"))
                emit(State.error(jObjError.getString("message")))*/

//                    val error: ErrorResponse? = apiResponse.errorBody()?.string()?.moshiStringToObj(ErrorResponse::class.java)
                error?.message?.let {
                    iRepositoryListener?.showMessage(error.message!!)
                    emit(State.error(error.message!!))
                }
            }
            iRepositoryListener?.hideLoader()
        }

    }.catch { e ->
        // Exception occurred! Emit error
        withContext(Dispatchers.Main){
            Timber.d("scope===network-->${Looper.myLooper() == Looper.getMainLooper()}")
            iRepositoryListener?.hideLoader()
            emit(State.error(e.message!!))
            iRepositoryListener?.showMessage(e.message!!)
            e.printStackTrace()
        }
    }


    protected abstract suspend fun fetchData(): Response<RESULT>

    private fun <T> getObjectFromJsonString(jsonString: String, classType: Class<T>): T? {
        try {
            val gson = Gson()
            return gson.fromJson(jsonString, classType)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

        return null
    }
}
