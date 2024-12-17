package com.example.slam

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.slam.databinding.FragmentFavesBinding
import com.example.slam.model.AddSlamView

class FavesFragment : Fragment() {

    private var _binding: FragmentFavesBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AddSlamView by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavesBinding.inflate(inflater, container, false)
return binding.root

       return binding.root
    }

    override fun onPause() {
        super.onPause()

        if (validateFields()) {
            viewModel.favoriteColor = binding.favColor.text.toString()
            viewModel.favoriteSport = binding.sport.text.toString()
            viewModel.placeToGo = binding.placeToGo.text.toString()
            viewModel.movie = binding.movie.text.toString()
            viewModel.song = binding.song.text.toString()
            viewModel.roleModel = binding.roleModel.text.toString()
            viewModel.favoriteQuote = binding.quote.text.toString()
        }
    }

    private fun validateFields(): Boolean {
        var isValid = true

        if (binding.favColor.text.isNullOrEmpty()) {
            binding.favColor.error = "This field is required"
            isValid = false
        } else {
            binding.favColor.error = null
        }

        if (binding.sport.text.isNullOrEmpty()) {
            binding.sport.error = "This field is required"
            isValid = false
        } else {
            binding.sport.error = null
        }

        if (binding.placeToGo.text.isNullOrEmpty()) {
            binding.placeToGo.error = "This field is required"
            isValid = false
        } else {
            binding.placeToGo.error = null
        }

        if (binding.movie.text.isNullOrEmpty()) {
            binding.movie.error = "This field is required"
            isValid = false
        } else {
            binding.movie.error = null
        }

        if (binding.song.text.isNullOrEmpty()) {
            binding.song.error = "This field is required"
            isValid = false
        } else {
            binding.song.error = null
        }

        if (binding.roleModel.text.isNullOrEmpty()) {
            binding.roleModel.error = "This field is required"
            isValid = false
        } else {
            binding.roleModel.error = null
        }

        if (binding.quote.text.isNullOrEmpty()) {
            binding.quote.error = "This field is required"
            isValid = false
        } else {
            binding.quote.error = null
        }

        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}