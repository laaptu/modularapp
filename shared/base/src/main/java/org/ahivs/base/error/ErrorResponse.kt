package org.ahivs.base.error

data class ErrorResponse(val error: ApiError)
data class ApiError(val code: Int, val message: String, val status: String = "")
