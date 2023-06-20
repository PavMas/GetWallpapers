package com.trifsdev.wallpaper.presentation.adapter

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.trifsdev.wallpaper.data.network.model.PhotosResponse
import com.trifsdev.wallpaper.databinding.ItemPhotoBinding
import com.trifsdev.wallpaper.presentation.adapter.holder.PhotoItemHolder

class PhotoAdapter : RecyclerView.Adapter<PhotoItemHolder>() {

    private var mCallback: ((result: PhotosResponse.Photo) -> Unit)? = null

    private var photoList = mutableListOf<PhotosResponse.Photo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoItemHolder {
        return PhotoItemHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoItemHolder, position: Int) {
        val item = photoList[position]
        //Picasso.get().load(item.urls.small).into(holder.binding.photo)
        Glide.with(holder.itemView).load(item.urls.small).listener(object : RequestListener<Drawable>{
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                holder.binding.indicator.visibility = View.GONE
                return false
            }

        }).into(holder.binding.photo)
        holder.binding.photoCard.setOnClickListener{
            mCallback?.invoke(item)
        }
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    fun setOnItemClickListener(function: ((PhotosResponse.Photo) -> Unit)?){
        mCallback = function
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setPhotos(photo: MutableList<PhotosResponse.Photo>){
        photoList = photo
        notifyDataSetChanged()
    }
}