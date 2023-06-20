package com.trifsdev.wallpaper.domain.usecase

import android.graphics.Bitmap
import com.trifsdev.wallpaper.domain.repository.WallpaperRepository

class SetWallpaperAsAllUseCase(private val repository: WallpaperRepository) {

    suspend fun execute(resource: Bitmap?): Boolean{
        return repository.setWallpaperAsAll(resource = resource)
    }
}