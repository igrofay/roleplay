package com.role.play.module.web

import com.role.play.data.Place
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ServerAPI {
    @POST("/generateStory")
    fun getDescription(@Body place: Place) : Call<Place>
}