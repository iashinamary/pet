package com.example.pet.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.pet.R
import com.example.pet.databinding.ProfileLayoutBinding


class HomeFragment: Fragment() {

    private lateinit var binding: ProfileLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ProfileLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
    }

    private fun initViews() {
        binding.apply {
            catProfile.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_toDoListFragment)
            }
            dogProfile.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_toDoListFragment)
            }
        }
    }

}