package com.example.pet

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pet.databinding.ProfileLayoutBinding

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ProfileLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}