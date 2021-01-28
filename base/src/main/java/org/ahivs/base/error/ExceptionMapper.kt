package org.ahivs.base.error

import com.google.gson.Gson
import okhttp3.Response
import org.json.JSONException
import javax.inject.Inject

//Customize this to map to generic API errors for the application
class ExceptionMapper @Inject constructor(private val gson: Gson) {
    fun mapApiErrorResponse(response: Response): ApiException {
        return when (response.code) {
            401 -> ApiException(ErrorCode.Network.SERVER, getErrorFromResponse(response))
            else -> ApiException(apiError = getErrorFromResponse(response))
        }
    }

    //map UnknownhostException and other connection issue exception
    fun mapApiException(exception: Exception): ApiException {
        return when (exception) {
            is ApiException -> exception
            else -> ApiException(errorCode = ErrorCode.Network.CONNECTION)
        }
    }

    private fun getErrorFromResponse(response: Response): ApiError? {
        return try {
            val errorResponse: ErrorResponse =
                    gson.fromJson(response.body?.string(), ErrorResponse::class.java)
            errorResponse.error
        } catch (jsonException: JSONException) {
            null
        }
    }
}