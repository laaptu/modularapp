package org.ahivs.base.error

//Customize the ErrorCodes as per the app need and don't create duplicate error codes
object ErrorCode {
    object Network {
        const val GENERIC = 2
        const val SERVER = 3
        const val CONNECTION = 4
    }

    const val APPLICATION = 1
}