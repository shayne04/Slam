package com.example.slam.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.slam.FavesFragment
import com.example.slam.FinalThoughtsFragment
import com.example.slam.KnowMeFragment

class SlamPageAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> KnowMeFragment()
            1 -> FavesFragment()
            else -> FinalThoughtsFragment()
        }
    }
}