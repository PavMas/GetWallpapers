package com.trifsdev.wallpaper

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.trifsdev.wallpaper.data.network.WallpaperApiImpl
import com.trifsdev.wallpaper.data.repository.WallpaperRepositoryImpl
import com.trifsdev.wallpaper.data.storage.PhotoDownloadImpl
import com.trifsdev.wallpaper.data.wallpaper.SetWallpaperImpl
import com.trifsdev.wallpaper.domain.usecase.*

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val wallpaperRepository by lazy(LazyThreadSafetyMode.NONE) { WallpaperRepositoryImpl(WallpaperApiImpl(), PhotoDownloadImpl(context), SetWallpaperImpl(context))}

    private val getPhotosByQueryUseCase by lazy(LazyThreadSafetyMode.NONE) { GetPhotosByQueryUseCase(repository = wallpaperRepository) }

    private val setWallpaperAsAllUseCase by lazy(LazyThreadSafetyMode.NONE) { SetWallpaperAsAllUseCase(repository = wallpaperRepository) }
    private val setWallpaperAsLockUseCase by lazy(LazyThreadSafetyMode.NONE) { SetWallpaperAsLockUseCase(repository = wallpaperRepository) }
    private val setWallpaperAsSystemUseCase by lazy(LazyThreadSafetyMode.NONE) { SetWallpaperAsSystemUseCase(repository = wallpaperRepository) }

    private val downloadPhotoUseCase by lazy(LazyThreadSafetyMode.NONE) { DownloadPhotoUseCase(repository = wallpaperRepository) }



    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getPhotosByQueryUseCase = getPhotosByQueryUseCase,
            setWallpaperAsAllUseCase = setWallpaperAsAllUseCase,
            setWallpaperAsLockUseCase = setWallpaperAsLockUseCase,
            setWallpaperAsSystemUseCase = setWallpaperAsSystemUseCase,
            downloadPhotoUseCase = downloadPhotoUseCase
        ) as T
    }
}