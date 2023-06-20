package com.trifsdev.wallpaper.data.wallpaper

import android.app.WallpaperManager
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SetWallpaperImpl(private val context: Context) : SetWallpaper{


    @RequiresApi(Build.VERSION_CODES.N)
    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun setWallpaperAsAll(resource: Bitmap?): Boolean {
        CoroutineScope(Dispatchers.IO).launch{
            val wallpaperManager = WallpaperManager.getInstance(context)
            wallpaperManager.setBitmap(resource, null, true, WallpaperManager.FLAG_SYSTEM)
            wallpaperManager.setBitmap(resource, null, true, WallpaperManager.FLAG_LOCK)
        }.join()
        return true
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun setWallpaperAsSystem(resource: Bitmap?): Boolean {
        CoroutineScope(Dispatchers.IO).launch{
            val wallpaperManager = WallpaperManager.getInstance(context)
            wallpaperManager.setBitmap(resource, null, true, WallpaperManager.FLAG_SYSTEM)
        }.join()
        return true
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun setWallpaperAsLock(resource: Bitmap?): Boolean {
        CoroutineScope(Dispatchers.IO).launch{
            val wallpaperManager = WallpaperManager.getInstance(context)
            wallpaperManager.setBitmap(resource, null, true, WallpaperManager.FLAG_LOCK)
        }.join()
        return true
    }
}