package org.ahivs.base.error

import java.io.IOException

//customize the response to any objects that is required by the application
class ApiException(val errorCode: Int = ErrorCode.Network.GENERIC,
                   val apiError: ApiError? = null) : IOException(apiError?.message)