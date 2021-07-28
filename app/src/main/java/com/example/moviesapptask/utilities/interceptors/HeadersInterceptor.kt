package com.example.moviesapptask.utilities.interceptors


import com.example.moviesapptask.utilities.enums.UserSavedDataKeys
import com.example.moviesapptask.utilities.managers.SharedPreferencesManagerInterface
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class HeadersInterceptor(private val sharedPreferencesManager: SharedPreferencesManagerInterface) : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()

                request
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Accept", "application/json")
                    .addHeader("country_id", sharedPreferencesManager.get(UserSavedDataKeys.COUNTRY_ID.key, ""))
                    .addHeader("city_id", sharedPreferencesManager.get(UserSavedDataKeys.CITY_ID.key, ""))

        if (sharedPreferencesManager.get(UserSavedDataKeys.ACCESS_TOKEN.key, "").isNotEmpty()) {
            request.addHeader("Access-Token", sharedPreferencesManager.get(UserSavedDataKeys.ACCESS_TOKEN.key, ""))
        }

        return chain.proceed(request.build())
    }
}