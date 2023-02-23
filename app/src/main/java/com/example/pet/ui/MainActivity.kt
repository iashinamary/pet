package com.example.pet.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pet.databinding.ActivityLayoutBinding
import com.example.pet.databinding.ProfileLayoutBinding

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}