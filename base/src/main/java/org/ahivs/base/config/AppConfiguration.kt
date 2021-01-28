package org.ahivs.base.config

import okhttp3.logging.HttpLoggingInterceptor
import org.ahivs.base.BuildConfig

//TODO: Move this configuration to a separate module such that both base and companion module will depend upon it
class AppConfiguration {
    var networkConfig: NetworkConfig = NetworkConfig()

    companion object {
        fun getConfiguration(): AppConfiguration {
            return if (BuildConfig.DEBUG) {
                appConfiguration {
                    networkConfig {
                        apiBaseUrl = "https://identitytoolkit.googleapis.com/"
                        httpLoggingLevel = HttpLoggingInterceptor.Level.BODY.name
                        defaultQueryParams = mapOf("key" to "AIzaSyDxJrUG6EqjMu7Vbcm1Ne8FDLI0eVFNwQ4")
                    }
                }
            } else {
                appConfiguration {
                    networkConfig {
                        apiBaseUrl = "https://identitytoolkit.googleapis.com/"
                        httpLoggingLevel = HttpLoggingInterceptor.Level.NONE.name
                    }
                }
            }
        }
    }
}

class NetworkConfig {
    var apiBaseUrl = ""
    var httpLoggingLevel = ""
    var defaultQueryParams: Map<String, String> = mutableMapOf()
    var connectionTimeOutInSec = 30L
    var readTimeOutInSec = 30L
    var writeTimeOutInSec = 30L
}

fun appConfiguration(block: AppConfiguration.() -> Unit): AppConfiguration = AppConfiguration().apply(block)
fun AppConfiguration.networkConfig(block: NetworkConfig.() -> Unit) {
    networkConfig = NetworkConfig().apply(block)
}