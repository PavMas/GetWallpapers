package com.trifsdev.wallpaper.data.storage

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PhotoDownloadImpl(private val context: Context) : PhotoDownload {

    override suspend fun downloadPhoto(url: String) {

        CoroutineScope(Dispatchers.IO).launch {
            val request =
                DownloadManager.Request(Uri.parse(url))
            request.setTitle("Photo Download")
            request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or DownloadManager.Request.NETWORK_MOBILE)
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DCIM, "photo.jpg")
            val manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            manager.enqueue(request)
        }
    }


}