package com.trifsdev.wallpaper.presentation.fragments

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.trifsdev.wallpaper.MainViewModel
import com.trifsdev.wallpaper.databinding.BottomDialogBinding
import com.trifsdev.wallpaper.databinding.FragmentPhotoPreviewBinding


class WallpaperPreviewFragment : Fragment() {
    private val args: WallpaperPreviewFragmentArgs by navArgs()


    private lateinit var binding: FragmentPhotoPreviewBinding

    private lateinit var bindingDialog: BottomDialogBinding

    private lateinit var vm: ViewModel

    private lateinit var wallpaperManager: WallpaperManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val url = args.photoUrl
        val download = args.download
        binding = FragmentPhotoPreviewBinding.inflate(layoutInflater)
        vm = ViewModelProviders.of(requireActivity())[MainViewModel::class.java]
        (vm as MainViewModel).clearLiveSetWallpaper()
        wallpaperManager = WallpaperManager.getInstance(requireContext())
        binding.download.setOnClickListener{
            (vm as MainViewModel).downloadPhoto(download)
        }
        Glide.with(this).asBitmap().load(url)
            .listener(object : RequestListener<Bitmap>{
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: Target<Bitmap>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            @RequiresApi(Build.VERSION_CODES.N)
            override fun onResourceReady(
                resource: Bitmap?,
                model: Any?,
                target: Target<Bitmap>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                binding.previewIndicator.visibility = View.GONE
                binding.setAs.setOnClickListener{

                    val bottomSheetDialog = BottomSheetDialog(requireContext())
                    bindingDialog = BottomDialogBinding.inflate(layoutInflater)
                    bottomSheetDialog.setContentView(bindingDialog.root)
                    bottomSheetDialog.show()

                    (vm as MainViewModel).resultLiveSetWallpaper.observe(viewLifecycleOwner){
                        if (it == true) {
                            Toast.makeText(requireContext(), "Обои установлены", Toast.LENGTH_SHORT)
                                .show()
                            bottomSheetDialog.cancel()
                        }
                    }
                    bindingDialog.setAsSystem.setOnClickListener{
                        (vm as MainViewModel).setWallpaperAsSystem(resource)
                    }
                    bindingDialog.setAsLock.setOnClickListener{
                        (vm as MainViewModel).setWallpaperAsLock(resource)

                    }
                    bindingDialog.setAsAll.setOnClickListener {
                        (vm as MainViewModel).setWallpaperAsAll(resource)
                    }
                }
                return false
            }

        })
            .into(binding.previewWallpaper)
        return binding.root
    }


}