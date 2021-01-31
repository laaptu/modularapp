package org.ahivs.base.cache

import javax.inject.Inject

//TODO: link this to db or shared preference or any place from which we can get authenticated params like token and stuff
class TokenRepository @Inject constructor() {
    fun getAuthenticatedHeader(): Map<String, String> = mapOf()

    fun getRefreshedAuthenticatedHeader(): Map<String, String> = mapOf()
}