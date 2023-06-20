package com.trifsdev.wallpaper.domain.usecase

import com.trifsdev.wallpaper.data.network.model.PhotosResponse
import com.trifsdev.wallpaper.domain.repository.WallpaperRepository

class GetPhotosByQueryUseCase(private val repository: WallpaperRepository) {

    suspend fun execute(query: String, page: Int, perPage: Int) : PhotosResponse? {
        return repository.getPhotosByQuery(query = query, page = page, perPage = perPage)
    }

}