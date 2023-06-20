package com.trifsdev.wallpaper.domain.usecase

import android.graphics.Bitmap
import com.trifsdev.wallpaper.domain.repository.WallpaperRepository

class SetWallpaperAsSystemUseCase(private val repository: WallpaperRepository) {

    suspend fun execute(resource: Bitmap?): Boolean{
        return repository.setWallpaperAsSystem(resource = resource)
    }
}