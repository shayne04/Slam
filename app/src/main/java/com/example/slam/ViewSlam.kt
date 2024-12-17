package com.example.slam

import android.app.DatePickerDialog
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.slam.databinding.ActivityViewSlamBinding
import com.example.slam.databinding.DialogEditBinding
import java.util.Calendar


class ViewSlam : AppCompatActivity() {

    private lateinit var binding: ActivityViewSlamBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewSlamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("slam_details", MODE_PRIVATE)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnEdit.setOnClickListener {
            showEditDialog()
        }

        populateDetailsFromIntent()
    }

    private fun populateDetailsFromIntent() {
        val nickname = intent.getStringExtra("nickname") ?: "Not provided"
        val firstName = intent.getStringExtra("firstName") ?: "Not provided"
        val lastName = intent.getStringExtra("lastName") ?: "Not provided"
        val birthdate = intent.getStringExtra("birthdate") ?: "Not provided"
        val gender = intent.getStringExtra("gender") ?: "Not provided"
        val contactNumber = intent.getStringExtra("contactNumber") ?: "Not provided"
        val address = intent.getStringExtra("address") ?: "Not provided"
        val faveColor = intent.getStringExtra("faveColor") ?: "Not provided"
        val sport = intent.getStringExtra("sport") ?: "Not provided"
        val placeToGo = intent.getStringExtra("placeToGo") ?: "Not provided"
        val movie = intent.getStringExtra("movie") ?: "Not provided"
        val song = intent.getStringExtra("song") ?: "Not provided"
        val roleModel = intent.getStringExtra("roleModel") ?: "Not provided"
        val favoriteQuote = intent.getStringExtra("quoteToLiveBy") ?: "Not provided"
        val hobby = intent.getStringExtra("hobby") ?: "Not provided"
        val good = intent.getStringExtra("good") ?: "Not provided"
        val bad = intent.getStringExtra("bad") ?: "Not provided"
        val proud = intent.getStringExtra("proud") ?: "Not provided"
        val funFact = intent.getStringExtra("funFact") ?: "Not provided"


        binding.apply {
            detailNickname.text = "Nickname: $nickname"
            detailFirstName.text = "First Name: $firstName"
            detailLastName.text = "Last Name: $lastName"
            detailBirthdate.text = "Birthdate: $birthdate"
            detailGender.text = "Gender: $gender"
            detailContactNumber.text = "Contact Number: $contactNumber"
            detailAddress.text = "Address: $address"

            detailFaveColor.text = "Favorite Color: $faveColor"
            detailSport.text = "Sport: $sport"
            detailPlaceToGo.text = "Place to Go: $placeToGo"
            detailMovie.text = "Movie: $movie"
            detailSong.text = "Song: $song"
            detailRoleModel.text = "Role Model: $roleModel"
            detailQuote.text = "Favorite Quote: $favoriteQuote"

            detailHobby.text = "Hobby: $hobby"
            detailGood.text = "Good at: $good"
            detailBad.text = "Bad at: $bad"
            detailProud.text = "Proud of: $proud"
            detailFunFact.text = "Fun Fact: $funFact"

        }
    }

    private fun showEditDialog() {
        val dialogBinding = DialogEditBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setTitle("Edit Slam Details")
            .setView(dialogBinding.root)
            .setPositiveButton("Update") { _, _ ->
                updateDetailsFromDialog(dialogBinding)
            }
            .setNegativeButton("Cancel", null)
            .create()

        preFillDialogFields(dialogBinding)
        setupBirthdatePicker(dialogBinding)
        setupGenderSpinner(dialogBinding)

        dialog.show()
    }
    private fun preFillDialogFields(dialogBinding: DialogEditBinding) {
        dialogBinding.apply {
            editNickname.setText(binding.detailNickname.text.removePrefix("Nickname: "))
            editFirstName.setText(binding.detailFirstName.text.removePrefix("First Name: "))
            editLastName.setText(binding.detailLastName.text.removePrefix("Last Name: "))
            editBirthdate.setText(binding.detailBirthdate.text.removePrefix("Birthdate: "))
            editContact.setText(binding.detailContactNumber.text.removePrefix("Contact Number: "))
            editAddress.setText(binding.detailAddress.text.removePrefix("Address: "))
            editFaveColor.setText(binding.detailFaveColor.text.removePrefix("Favorite Color: "))
            editSport.setText(binding.detailSport.text.removePrefix("Sport: "))
            editPlaceToGo.setText(binding.detailPlaceToGo.text.removePrefix("Place to Go: "))
            editFaveMovie.setText(binding.detailMovie.text.removePrefix("Movie: "))
            editFaveSong.setText(binding.detailSong.text.removePrefix("Song: "))
            editRoleModel.setText(binding.detailRoleModel.text.removePrefix("Role Model: "))
            editQuote.setText(binding.detailQuote.text.removePrefix("Favorite Quote: "))
            editHobby.setText(binding.detailHobby.text.removePrefix("Hobby: "))
            editGood.setText(binding.detailGood.text.removePrefix("Good at: "))
            editBad.setText(binding.detailBad.text.removePrefix("Bad at: "))
            editProud.setText(binding.detailProud.text.removePrefix("Proud of: "))
            editFunfact.setText(binding.detailFunFact.text.removePrefix("Fun Fact: "))
        }
    }

    private fun setupBirthdatePicker(dialogBinding: DialogEditBinding) {
        dialogBinding.editBirthdate.setOnClickListener {
            val currentDate = Calendar.getInstance()
            val picker = DatePickerDialog(
                this,
                { _, year, month, dayOfMonth ->
                    dialogBinding.editBirthdate.setText("$year-${month + 1}-$dayOfMonth")
                },
                currentDate.get(Calendar.YEAR),
                currentDate.get(Calendar.MONTH),
                currentDate.get(Calendar.DAY_OF_MONTH)
            )
            picker.show()
        }
    }

    private fun setupGenderSpinner(dialogBinding: DialogEditBinding) {
        val genderOptions = arrayOf("Male", "Female", "Other")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, genderOptions)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        dialogBinding.spinnerGender.adapter = adapter

        val currentGender = binding.detailGender.text.removePrefix("Gender: ")
        val selectedIndex = genderOptions.indexOf(currentGender)
        if (selectedIndex != -1) dialogBinding.spinnerGender.setSelection(selectedIndex)
    }

    private fun updateDetailsFromDialog(dialogBinding: DialogEditBinding) {
        binding.apply {
            detailNickname.text = "Nickname: ${dialogBinding.editNickname.text}"
            detailFirstName.text = "First Name: ${dialogBinding.editFirstName.text}"
            detailLastName.text = "Last Name: ${dialogBinding.editLastName.text}"
            detailBirthdate.text = "Birthdate: ${dialogBinding.editBirthdate.text}"
            detailGender.text = "Gender: ${dialogBinding.spinnerGender.selectedItem}"
            detailContactNumber.text = "Contact Number: ${dialogBinding.editContact.text}"
            detailAddress.text = "Address: ${dialogBinding.editAddress.text}"

            detailFaveColor.text = "Favorite Color: ${dialogBinding.editFaveColor.text}"
            detailSport.text = "Sport: ${dialogBinding.editSport.text}"
            detailPlaceToGo.text = "Place to Go: ${dialogBinding.editPlaceToGo.text}"
            detailMovie.text = "Movie: ${dialogBinding.editFaveMovie.text}"
            detailSong.text = "Song: ${dialogBinding.editFaveSong.text}"
            detailRoleModel.text = "Role Model: ${dialogBinding.editRoleModel.text}"
            detailQuote.text = "Favorite Quote: ${dialogBinding.editQuote.text}"

            detailHobby.text = "Hobby: ${dialogBinding.editHobby.text}"
            detailGood.text = "Good at: ${dialogBinding.editGood.text}"
            detailBad.text = "Bad at: ${dialogBinding.editBad.text}"
            detailProud.text = "Proud of: ${dialogBinding.editProud.text}"
            detailFunFact.text = "Fun Fact: ${dialogBinding.editFunfact.text}"
        }
        Toast.makeText(this, "Update Successful!", Toast.LENGTH_SHORT).show()
    }
}