package com.example.tests

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class GiphyResponse(
    val data: List<GifData>
)

data class GifData(
    val images: GifImages
)

data class GifImages(
    val downsized_medium: GifOriginal
)

data class GifOriginal(
    val url: String
)

interface GiphyApiService {
    @GET("v1/gifs/search")
    suspend fun getRandomGif(
        @Query("api_key") apiKey: String,
        @Query("q") query: String,
        @Query("limit") limit: Int
    ): GiphyResponse
}

object GiphyApi {
    private const val BASE_URL = "https://api.giphy.com/"

    val service: GiphyApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GiphyApiService::class.java)
    }
}
