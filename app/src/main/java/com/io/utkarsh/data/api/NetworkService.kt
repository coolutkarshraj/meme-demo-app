package com.io.utkarsh.data.api

import com.io.utkarsh.data.model.MemeApiResponseModel
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface NetworkService {
    @GET("gimme")
    suspend fun getMemeApi(): MemeApiResponseModel

}