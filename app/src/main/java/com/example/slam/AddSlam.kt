package com.example.slam

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.slam.adapter.SlamPageAdapter
import com.example.slam.databinding.ActivityAddSlamBinding
import com.example.slam.model.AddSlamView
import com.google.android.material.tabs.TabLayoutMediator
import org.json.JSONArray
import org.json.JSONObject


class AddSlam : AppCompatActivity() {

    private lateinit var binding: ActivityAddSlamBinding
    private val viewModel: AddSlamView by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddSlamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = SlamPageAdapter(this)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Know Me"
                1 -> "Faves"
                else -> "More"
            }
        }.attach()

        binding.btnSaveSlam.setOnClickListener {
            saveSlamData()
        }

        binding.btnBack.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
                .setTitle("Discard Changes")
                .setMessage("Are you sure you want to discard this entry?")
                .setPositiveButton("Yes") { _, _ -> finish() }
                .setNegativeButton("No", null)
                .create()
            dialog.show()
        }
    }

    private fun saveSlamData() {
        val missingFields = validateFields()
        if (missingFields.isNotEmpty()) {
            Toast.makeText(this, "Please fill in missing fields", Toast.LENGTH_LONG).show()
            return
        } else {

            val sharedPreferences = getSharedPreferences("SlamBook", MODE_PRIVATE)
            val editor = sharedPreferences.edit()

            val existingData = sharedPreferences.getString("slamList", "[]")
            val slamArray = JSONArray(existingData)

            val slamObject = JSONObject().apply {
                put("nickname", viewModel.nickname)
                put("firstName", viewModel.firstName)
                put("lastName", viewModel.lastName)
                put("birthdate", viewModel.birthdate)
                put("gender", viewModel.gender)
                put("contactNumber", viewModel.contactNo)
                put("address", viewModel.address)

                put("faveColor", viewModel.favoriteColor)
                put("sport", viewModel.favoriteSport)
                put("placeToGo", viewModel.placeToGo)
                put("movie", viewModel.movie)
                put("song", viewModel.song)
                put("roleModel", viewModel.roleModel)
                put("quoteToLiveBy", viewModel.favoriteQuote)

                put("hobby", viewModel.hobby)
                put("good", viewModel.good)
                put("bad", viewModel.bad)
                put("proud", viewModel.proud)
                put("funFact", viewModel.funFact)
            }

            slamArray.put(slamObject)
            editor.putString("slamList", slamArray.toString()).apply()

            Toast.makeText(this, "Slam entry saved!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
    private fun validateFields(): String {
        val missingFields = mutableListOf<String>()

        if (viewModel.nickname.isEmpty()) missingFields.add("Nickname")
        if (viewModel.firstName.isEmpty()) missingFields.add("First Name")
        if (viewModel.lastName.isEmpty()) missingFields.add("Last Name")
        if (viewModel.birthdate.isEmpty()) missingFields.add("Birthdate")
        if (viewModel.gender.isEmpty()) missingFields.add("Gender")
        if (viewModel.contactNo.isEmpty()) missingFields.add("Contact Number")
        if (viewModel.address.isEmpty()) missingFields.add("Address")

        if (viewModel.favoriteColor.isEmpty()) missingFields.add("Favorite Color")
        if (viewModel.favoriteSport.isEmpty()) missingFields.add("Favorite Sport")
        if (viewModel.placeToGo.isEmpty()) missingFields.add("Place to Go")
        if (viewModel.movie.isEmpty()) missingFields.add("Movie")
        if (viewModel.song.isEmpty()) missingFields.add("Song")
        if (viewModel.roleModel.isEmpty()) missingFields.add("Role Model")
        if (viewModel.favoriteQuote.isEmpty()) missingFields.add("Favorite Quote")

        if (viewModel.hobby.isEmpty()) missingFields.add("Hobby")
        if (viewModel.good.isEmpty()) missingFields.add("Good")
        if (viewModel.bad.isEmpty()) missingFields.add("Bad")
        if (viewModel.proud.isEmpty()) missingFields.add("Proud")
        if (viewModel.funFact.isEmpty()) missingFields.add("Fun Fact")

        return missingFields.joinToString(", ")
    }
}