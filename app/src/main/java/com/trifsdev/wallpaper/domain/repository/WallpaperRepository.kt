package com.trifsdev.wallpaper.domain.repository

import android.graphics.Bitmap
import com.trifsdev.wallpaper.data.network.model.PhotosResponse

interface WallpaperRepository {
    suspend fun getPhotosByQuery(query: String, page: Int, perPage: Int) : PhotosResponse?

    suspend fun setWallpaperAsAll(resource: Bitmap?): Boolean
    suspend fun setWallpaperAsLock(resource: Bitmap?): Boolean
    suspend fun setWallpaperAsSystem(resource: Bitmap?): Boolean

    suspend fun downloadPhoto(url: String)

}