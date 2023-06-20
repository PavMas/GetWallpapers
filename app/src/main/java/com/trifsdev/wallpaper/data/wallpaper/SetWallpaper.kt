package com.trifsdev.wallpaper.data.wallpaper

import android.graphics.Bitmap

interface SetWallpaper {

    suspend fun setWallpaperAsAll(resource: Bitmap?): Boolean

    suspend fun setWallpaperAsSystem(resource: Bitmap?): Boolean

    suspend fun setWallpaperAsLock(resource: Bitmap?): Boolean

}