package com.trifsdev.wallpaper.domain.usecase

import com.trifsdev.wallpaper.domain.repository.WallpaperRepository

class DownloadPhotoUseCase(private val repository: WallpaperRepository) {

    suspend fun execute(url: String){
        repository.downloadPhoto(url = url)
    }
}