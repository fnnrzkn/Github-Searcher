package com.irfannurrizki.githubsearcher

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.R
import com.example.githubuser.databinding.FragmentFollowersBinding
import com.irfannurrizki.githubsearcher.adapter.UserAdapter
import com.irfannurrizki.githubsearcher.alldata.api.ItemsItem
import com.irfannurrizki.githubsearcher.viewmodel.FollowersViewModel

class Followers : Fragment() {
    private lateinit var binding: FragmentFollowersBinding
    private val followersViewModel: FollowersViewModel by activityViewModels()

    companion object{
        const val ARG_SECTION_NUMBER = "section_number"
        const val USERNAME_FOLLOWERS = "username"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)
        val username = arguments?.getString(USERNAME_FOLLOWERS)

        val layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollowers.layoutManager = layoutManager

        followersViewModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }

        if (index == 1) {
            showLoading(true)
            followersViewModel.getUserFollowers(username.toString())

            followersViewModel.followerslUser.observe(viewLifecycleOwner) {
                setFollower(it)
            }
            showLoading(false)
        } else {
            showLoading(true)
            followersViewModel.getUserFollowing(username.toString())

            followersViewModel.followinglUser.observe(viewLifecycleOwner) {
                setFollower(it)
            }
            showLoading(false)
        }
    }

    private fun setFollower(data: List<ItemsItem>) {
        val adapter = UserAdapter(data)
        binding.rvFollowers.setHasFixedSize(true)
        binding.rvFollowers.layoutManager = LinearLayoutManager(requireActivity())
        binding.rvFollowers.adapter = adapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}