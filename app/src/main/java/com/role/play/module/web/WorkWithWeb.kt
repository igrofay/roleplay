package com.role.play.module.web

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WorkWithWeb {
    private const val SERVER_URL = "https://server.krea-company.keenetic.pro"
    private val client: Retrofit by lazy {
        Retrofit.Builder().baseUrl(SERVER_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val server: ServerAPI by lazy {
        client.create(ServerAPI::class.java)
    }
}