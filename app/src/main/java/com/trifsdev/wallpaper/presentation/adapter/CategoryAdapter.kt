package com.trifsdev.wallpaper.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.trifsdev.wallpaper.databinding.ItemCategoryBinding
import com.trifsdev.wallpaper.domain.model.Category
import com.trifsdev.wallpaper.presentation.adapter.holder.CategoryItemHolder

class CategoryAdapter : RecyclerView.Adapter<CategoryItemHolder>(){

    private var mCallback: ((result: String) -> Unit)? = null

    private var categoryList = mutableListOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryItemHolder {
        return CategoryItemHolder(
            ItemCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryItemHolder, position: Int) {
        val item = categoryList[position]
        Picasso.get().load(item.previewUrl).into(holder.binding.preview)
        holder.binding.categoryName.text = item.name
        holder.binding.card.setOnClickListener{
            mCallback?.invoke(item.name)
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun setOnItemClickListener(function: ((String) -> Unit)?){
        mCallback = function
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addCategory(category: Category){
        categoryList.add(category)
        notifyDataSetChanged()
    }

}