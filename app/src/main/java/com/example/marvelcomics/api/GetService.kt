package com.example.marvelcomics.api

import com.example.marvelcomics.data.dto.CharactersDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetService {

    @GET("/v1/public/characters?limit=1")
    fun getAllCharacters(
        @Query("ts") ts: String = Constants.timeStamp,
        @Query("apikey") apikey: String = Constants.API_KEY,
        @Query("hash") hash: String = Constants.md5Hash(),
    ): Call<CharactersDTO>

}