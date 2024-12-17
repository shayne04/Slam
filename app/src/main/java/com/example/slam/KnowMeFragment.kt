package com.example.slam

import android.R
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.activityViewModels
import com.example.slam.databinding.FragmentKnowMeeBinding
import com.example.slam.model.AddSlamView
import java.util.Calendar


class KnowMeFragment : Fragment() {

    private var _binding: FragmentKnowMeeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AddSlamView by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentKnowMeeBinding.inflate(inflater, container, false)

        binding.bday.setOnClickListener {
            showDatePicker()
        }

        setupGenderSpinner()
        return binding.root
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                val formattedDate = "${selectedMonth + 1}/${selectedDay}/${selectedYear}"
                binding.bday.setText(formattedDate)
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun setupGenderSpinner() {
        val genderOptions = listOf("Select Gender", "Male", "Female", "Other")
        val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, genderOptions)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        binding.genderSpinner.adapter = adapter

        val savedGender = viewModel.gender
        if (savedGender != null && genderOptions.contains(savedGender)) {
            binding.genderSpinner.setSelection(genderOptions.indexOf(savedGender))
        }

        binding.genderSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                if (position > 0) {
                    viewModel.gender = genderOptions[position]
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }
    }
    override fun onPause() {
        super.onPause()

        if (validateFields()) {
            viewModel.nickname = binding.nickName.text.toString()
            viewModel.firstName = binding.firstName.text.toString()
            viewModel.lastName = binding.lastName.text.toString()
            viewModel.birthdate = binding.bday.text.toString()
            viewModel.contactNo = binding.contactNo.text.toString()
            viewModel.address = binding.address.text.toString()
        }
    }

    private fun validateFields(): Boolean {
        var isValid = true

        if (binding.nickName.text.isNullOrEmpty()) {
            binding.nickName.error = "This field is required"
            isValid = false
        } else {
            binding.nickName.error = null
        }

        if (binding.firstName.text.isNullOrEmpty()) {
            binding.firstName.error = "This field is required"
            isValid = false
        } else {
            binding.firstName.error = null
        }

        if (binding.lastName.text.isNullOrEmpty()) {
            binding.lastName.error = "This field is required"
            isValid = false
        } else {
            binding.lastName.error = null
        }

        if (binding.bday.text.isNullOrEmpty()) {
            binding.bday.error = "This field is required"
            isValid = false
        } else {
            binding.bday.error = null
        }

        if (binding.contactNo.text.isNullOrEmpty()) {
            binding.contactNo.error = "This field is required"
            isValid = false
        } else {
            binding.contactNo.error = null
        }

        if (binding.address.text.isNullOrEmpty()) {
            binding.address.error = "This field is required"
            isValid = false
        } else {
            binding.address.error = null
        }

        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}