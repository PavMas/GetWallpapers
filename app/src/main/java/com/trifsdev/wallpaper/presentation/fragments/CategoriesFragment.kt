package com.trifsdev.wallpaper.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trifsdev.wallpaper.MainViewModel
import com.trifsdev.wallpaper.databinding.FragmentCategoryBinding
import com.trifsdev.wallpaper.domain.model.Category
import com.trifsdev.wallpaper.presentation.adapter.CategoryAdapter

class CategoriesFragment : Fragment() {

    private lateinit var adapter: CategoryAdapter
    private lateinit var rv: RecyclerView

    private lateinit var binding: FragmentCategoryBinding

    private lateinit var vm: ViewModel

    private val categories = mutableListOf(Category("Animals", ""),
        Category("Weather", ""),
        Category("Cars", ""),
        Category("Rain", ""),
        Category("Abstract", ""),
        Category("Still-life", ""),
        Category("Nature", ""),
        Category("Sea", ""),
        Category("Rivers", ""),
        Category("Lifestyle", ""),
        Category("Art", ""),
        Category("Plants", ""),
        Category("Texture", ""),
        Category("Quotes", ""),
        Category("Space", ""),
        Category("Flowers", "")

    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(inflater, container, false)
        vm = ViewModelProviders.of(requireActivity())[MainViewModel::class.java]
        initRecyclerView()
        (vm as MainViewModel).clearLivePhotos()
        categories.forEach {
            (vm as MainViewModel).getPhotosByQuery(it.name, 1, 1)
        }
        (vm as MainViewModel).resultLivePhotos.observe(viewLifecycleOwner){ response ->
            response?.let {
                adapter.addCategory(category = Category(it.query, it.results[0].urls.small))
            }
        }
        adapter.setOnItemClickListener {
            val action = CategoriesFragmentDirections.categoryToPhoto(it)
            findNavController()
                .navigate(action)
        }
        return binding.root
    }

    private fun initRecyclerView() {
        rv = binding.categoryRecycler
        setManagerAndAdapter()
    }

    private fun setManagerAndAdapter() {
        rv.layoutManager = GridLayoutManager(
            this.context, 2,
            GridLayoutManager.VERTICAL, false)
        adapter = CategoryAdapter()
        rv.adapter = adapter
    }
}