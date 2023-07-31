package com.irfannurrizki.githubsearcher.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.githubuser.R
import com.irfannurrizki.githubsearcher.DetailActivity
import com.irfannurrizki.githubsearcher.Followers

class SectionPagerAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    var username :String = ""

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        val fragment = Followers()
        fragment.arguments = Bundle().apply {
            putInt(Followers.ARG_SECTION_NUMBER, position+1)
            putString(DetailActivity.USERNAME, username)
        }

        return fragment
    }
}

