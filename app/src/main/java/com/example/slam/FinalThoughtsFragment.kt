package com.example.slam

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.slam.databinding.FragmentFinalThoughtBinding
import com.example.slam.model.AddSlamView

class FinalThoughtsFragment : Fragment() {
    private var _binding: FragmentFinalThoughtBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences

    private val viewModel: AddSlamView by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFinalThoughtBinding.inflate(inflater, container, false)
        sharedPreferences = requireContext().getSharedPreferences("SlamBookPrefs", Context.MODE_PRIVATE)

        return binding.root
    }


    override fun onPause() {
        super.onPause()

        if (validateFields()) {
            viewModel.hobby = binding.hobby.text.toString()
            viewModel.good = binding.good.text.toString()
            viewModel.bad = binding.bad.text.toString()
            viewModel.proud = binding.proud.text.toString()
            viewModel.funFact = binding.funFact.text.toString()
        }
    }

    private fun validateFields(): Boolean {
        var isValid = true

        if (binding.hobby.text.isNullOrEmpty()) {
            binding.hobby.error = "This field is required"
            isValid = false
        } else {
            binding.hobby.error = null
        }

        if (binding.good.text.isNullOrEmpty()) {
            binding.good.error = "This field is required"
            isValid = false
        } else {
            binding.good.error = null
        }

        if (binding.bad.text.isNullOrEmpty()) {
            binding.bad.error = "This field is required"
            isValid = false
        } else {
            binding.bad.error = null
        }

        if (binding.proud.text.isNullOrEmpty()) {
            binding.proud.error = "This field is required"
            isValid = false
        } else {
            binding.proud.error = null
        }

        if (binding.funFact.text.isNullOrEmpty()) {
            binding.funFact.error = "This field is required"
            isValid = false
        } else {
            binding.funFact.error = null
        }

        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}