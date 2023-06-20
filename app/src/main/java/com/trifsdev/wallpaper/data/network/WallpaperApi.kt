package com.trifsdev.wallpaper.data.network

import com.trifsdev.wallpaper.data.network.model.PhotosResponse

interface WallpaperApi {

    suspend fun getPhotosByQuery(query: String, page: Int, perPage: Int): PhotosResponse?

}