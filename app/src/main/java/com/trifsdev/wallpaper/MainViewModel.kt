package com.trifsdev.wallpaper

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trifsdev.wallpaper.data.network.model.PhotosResponse
import com.trifsdev.wallpaper.domain.usecase.*
import kotlinx.coroutines.launch

class MainViewModel(
    private val getPhotosByQueryUseCase: GetPhotosByQueryUseCase,
    private val setWallpaperAsAllUseCase: SetWallpaperAsAllUseCase,
    private val setWallpaperAsLockUseCase: SetWallpaperAsLockUseCase,
    private val setWallpaperAsSystemUseCase: SetWallpaperAsSystemUseCase,
    private val downloadPhotoUseCase: DownloadPhotoUseCase
) : ViewModel() {

    private val resultLivePhotosMutable = MutableLiveData<PhotosResponse?>()
    val resultLivePhotos: LiveData<PhotosResponse?> = resultLivePhotosMutable

    private val resultLiveSetWallpapersMutable = MutableLiveData<Boolean?>()
    val resultLiveSetWallpaper = resultLiveSetWallpapersMutable

    fun getPhotosByQuery(query: String, page: Int, perPage: Int) = viewModelScope.launch {
        resultLivePhotosMutable.value = getPhotosByQueryUseCase.execute(query, page, perPage)
    }

    fun clearLivePhotos() = viewModelScope.launch {
        if (resultLivePhotosMutable.value != null)
            resultLivePhotosMutable.value = null
    }

    fun setWallpaperAsAll(resource: Bitmap?) = viewModelScope.launch {
        resultLiveSetWallpapersMutable.value = setWallpaperAsAllUseCase.execute(resource)
    }
    fun setWallpaperAsLock(resource: Bitmap?) = viewModelScope.launch {
        resultLiveSetWallpapersMutable.value = setWallpaperAsLockUseCase.execute(resource)
    }
    fun setWallpaperAsSystem(resource: Bitmap?) = viewModelScope.launch {
        resultLiveSetWallpapersMutable.value = setWallpaperAsSystemUseCase.execute(resource)
    }

    fun clearLiveSetWallpaper() = viewModelScope.launch {
        if (resultLiveSetWallpapersMutable.value != null)
            resultLiveSetWallpapersMutable.value = null
    }

    fun downloadPhoto(url: String) = viewModelScope.launch {
        downloadPhotoUseCase.execute(url)
    }
}