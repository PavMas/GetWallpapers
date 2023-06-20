package com.trifsdev.wallpaper.data.storage

interface PhotoDownload {

    suspend fun downloadPhoto(url: String)


}