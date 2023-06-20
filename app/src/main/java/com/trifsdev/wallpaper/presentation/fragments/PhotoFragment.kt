package com.trifsdev.wallpaper.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.trifsdev.wallpaper.MainViewModel
import com.trifsdev.wallpaper.databinding.FragmentCategoryBinding
import com.trifsdev.wallpaper.presentation.adapter.PhotoAdapter

class PhotoFragment : Fragment() {

    private val args: PhotoFragmentArgs by navArgs()

    private lateinit var binding: FragmentCategoryBinding

    private lateinit var vm: ViewModel

    private lateinit var adapter: PhotoAdapter
    private lateinit var rv: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoryBinding.inflate(layoutInflater)
        val query = args.query
        vm = ViewModelProviders.of(requireActivity())[MainViewModel::class.java]
        initRecyclerView()
        (vm as MainViewModel).clearLivePhotos()
        (vm as MainViewModel).getPhotosByQuery(query, 2, 8)
        (vm as MainViewModel).resultLivePhotos.observe(viewLifecycleOwner){ response ->
            response?.results?.let {
                    it -> adapter.setPhotos(it)
            }
        }
        adapter.setOnItemClickListener {
            val action = PhotoFragmentDirections.photoToWallpaperPreview(it.urls.full, it.links.download)
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
        adapter = PhotoAdapter()
        rv.adapter = adapter
    }




}