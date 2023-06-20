package com.trifsdev.wallpaper.data.repository

import android.graphics.Bitmap
import com.trifsdev.wallpaper.data.network.WallpaperApi
import com.trifsdev.wallpaper.data.network.model.PhotosResponse
import com.trifsdev.wallpaper.data.storage.PhotoDownload
import com.trifsdev.wallpaper.data.wallpaper.SetWallpaper
import com.trifsdev.wallpaper.domain.repository.WallpaperRepository

class WallpaperRepositoryImpl(
    private val wallpaperApi: WallpaperApi,
    private val photoDownload: PhotoDownload,
    private val setWallpaper: SetWallpaper
) : WallpaperRepository {

    override suspend fun getPhotosByQuery(query: String, page: Int, perPage: Int) : PhotosResponse?{
        return wallpaperApi.getPhotosByQuery(query = query, page = page, perPage = perPage)
    }

    override suspend fun setWallpaperAsLock(resource: Bitmap?): Boolean {
       return setWallpaper.setWallpaperAsLock(resource = resource)
    }
    override suspend fun setWallpaperAsSystem(resource: Bitmap?): Boolean {
        return setWallpaper.setWallpaperAsSystem(resource = resource)
    }

    override suspend fun setWallpaperAsAll(resource: Bitmap?): Boolean {
        return setWallpaper.setWallpaperAsAll(resource = resource)
    }


    override suspend fun downloadPhoto(url: String) {
        photoDownload.downloadPhoto(url = url)
    }

}