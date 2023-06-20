package com.trifsdev.wallpaper.data.network.retrofit2

import com.trifsdev.wallpaper.data.network.BASE_URL
import com.trifsdev.wallpaper.data.network.model.PhotosResponse
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface WallpaperApiService {

    @GET("/search/photos")
    fun getPhotosByQuery(@Query("client_id") key: String, @Query("per_page") perPage: Int, @Query("page") page: Int, @Query("query") query: String) : Call<PhotosResponse>

    companion object {
        private var apiService: WallpaperApiService? = null

        fun getInstance() : WallpaperApiService{
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            apiService = retrofit.create(WallpaperApiService::class.java)
            return apiService!!
        }
    }
}