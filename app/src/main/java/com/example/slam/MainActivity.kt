package com.example.slam


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.slam.adapter.SlamAdapter
import com.example.slam.databinding.ActivityMainBinding
import com.example.slam.model.SlamEntry
import org.json.JSONArray
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var slamAdapter: SlamAdapter
    private val slamList = mutableListOf<SlamEntry>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.slamRecyclerView.layoutManager = LinearLayoutManager(this)
        slamAdapter = SlamAdapter(slamList, clickListener = { slamEntry ->
            val intent = Intent(this, ViewSlam::class.java)
            intent.putExtra("nickname", slamEntry.nickname)
            intent.putExtra("firstName", slamEntry.firstName)
            intent.putExtra("lastName", slamEntry.lastName)
            intent.putExtra("birthdate", slamEntry.birthdate)
            intent.putExtra("gender", slamEntry.gender)
            intent.putExtra("contactNumber", slamEntry.contactNumber)
            intent.putExtra("address", slamEntry.address)

            intent.putExtra("faveColor", slamEntry.faveColor)
            intent.putExtra("sport", slamEntry.sport)
            intent.putExtra("placeToGo", slamEntry.placeToGo)
            intent.putExtra("movie", slamEntry.movie)
            intent.putExtra("song", slamEntry.song)
            intent.putExtra("roleModel", slamEntry.roleModel)
            intent.putExtra("quoteToLiveBy", slamEntry.favoriteQuote)

            intent.putExtra("hobby", slamEntry.hobby)
            intent.putExtra("good", slamEntry.good)
            intent.putExtra("bad", slamEntry.bad)
            intent.putExtra("proud", slamEntry.proud)
            intent.putExtra("funFact", slamEntry.funFact)

            startActivity(intent)
        },
            deleteListener = { slamEntry ->
                slamList.remove(slamEntry)
                slamAdapter.notifyDataSetChanged()
                saveSlamData()
            }
        )
        binding.slamRecyclerView.adapter = slamAdapter

        loadSlamData()

        binding.addSlam.setOnClickListener {
            val intent = Intent(this, AddSlam::class.java)
            startActivity(intent)
        }
    }


    override fun onResume() {
        super.onResume()
        loadSlamData()
    }

    private fun loadSlamData() {
        slamList.clear()

        val sharedPreferences = getSharedPreferences("SlamBook", MODE_PRIVATE)
        val existingData = sharedPreferences.getString("slamList", "[]")
        val slamArray = JSONArray(existingData)

        for (i in 0 until slamArray.length()) {
            val slamObject = slamArray.getJSONObject(i)

            val nickname = slamObject.optString("nickname", "Unknown")
            val firstName = slamObject.optString("firstName", "")
            val lastName = slamObject.optString("lastName", "")
            val birthdate = slamObject.optString("birthdate", "")
            val gender = slamObject.optString("gender", "")
            val contactNumber = slamObject.optString("contactNumber", "")
            val address = slamObject.optString("address", "")

            val faveColor = slamObject.optString("faveColor", "")
            val sport = slamObject.optString("sport", "")
            val placeToGo = slamObject.optString("placeToGo", "")
            val movie = slamObject.optString("movie", "")
            val song = slamObject.optString("song", "")
            val roleModel = slamObject.optString("roleModel", "")
            val quoteToLiveBy = slamObject.optString("quoteToLiveBy", "")

            val hobby = slamObject.optString("hobby", "")
            val good = slamObject.optString("good", "")
            val bad = slamObject.optString("bad", "")
            val proud = slamObject.optString("proud", "")
            val funFact = slamObject.optString("funFact", "")

            slamList.add(
                SlamEntry(
                    nickname,
                    firstName,
                    lastName,
                    birthdate,
                    gender,
                    contactNumber,
                    address,
                    faveColor,
                    sport,
                    placeToGo,
                    movie,
                    song,
                    roleModel,
                    quoteToLiveBy,
                    hobby,
                    good,
                    bad,
                    proud,
                    funFact
                )
            )
        }

        slamAdapter.notifyDataSetChanged()
    }
    private fun saveSlamData() {
    val sharedPreferences = getSharedPreferences("SlamBook", MODE_PRIVATE)
    val editor = sharedPreferences.edit()

    val slamArray = JSONArray()
    slamList.forEach { slam ->
        val slamObject = JSONObject()
        slamObject.put("nickname", slam.nickname)
        slamObject.put("firstName", slam.firstName)
        slamObject.put("lastName", slam.lastName)
        slamObject.put("birthdate", slam.birthdate)
        slamObject.put("gender", slam.gender)
        slamObject.put("contactNumber", slam.contactNumber)
        slamObject.put("address", slam.address)

        slamObject.put("faveColor", slam.faveColor)
        slamObject.put("sport", slam.sport)
        slamObject.put("placeToGo", slam.placeToGo)
        slamObject.put("movie", slam.movie)
        slamObject.put("song", slam.song)
        slamObject.put("roleModel", slam.roleModel)
        slamObject.put("quoteToLiveBy", slam.favoriteQuote)

        slamObject.put("hobby", slam.hobby)
        slamObject.put("good", slam.good)
        slamObject.put("bad", slam.bad)
        slamObject.put("proud", slam.proud)
        slamObject.put("funFact", slam.funFact)

        slamArray.put(slamObject)
    }

    editor.putString("slamList", slamArray.toString())
    editor.apply()
}
}
