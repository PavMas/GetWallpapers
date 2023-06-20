package com.trifsdev.wallpaper.data.network.model

data class PhotosResponse(
    var query: String,
    val results: MutableList<Photo>){
    inner class Photo(val urls: URLs,
                      val links: Links){
        inner class URLs(val full: String,
        val small: String)
        inner class Links(val download: String)
    }
}
