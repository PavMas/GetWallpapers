package com.trifsdev.wallpaper.data.network

import android.util.Log
import com.trifsdev.wallpaper.data.network.model.PhotosResponse
import com.trifsdev.wallpaper.data.network.retrofit2.WallpaperApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WallpaperApiImpl : WallpaperApi {

    private lateinit var api: WallpaperApiService

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun getPhotosByQuery(query: String, page: Int, perPage: Int): PhotosResponse? {
        api = WallpaperApiService.getInstance()
        var responseFromServer: PhotosResponse? = null
        val response = api.getPhotosByQuery(KEY, perPage = 20, page = 1, query = query)
        CoroutineScope(Dispatchers.IO).launch {
            try{
                val getPhotosByQuery = response.execute()
                if (getPhotosByQuery.code() == 200)
                    responseFromServer = getPhotosByQuery.body()!!
                responseFromServer?.query = query
            }
            catch (e: Exception){
                Log.d(
                    "API_EXCEPTION", e.toString()
                )
            }
        }.join()
        return responseFromServer
    }
}