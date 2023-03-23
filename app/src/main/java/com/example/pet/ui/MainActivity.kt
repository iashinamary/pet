package com.example.pet.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.pet.OneTimeWorkRequest.TaskWorker
import com.example.pet.databinding.ActivityLayoutBinding
import com.example.pet.databinding.ProfileLayoutBinding
import com.example.pet.domain.models.TaskEntity
import java.util.concurrent.TimeUnit

class MainActivity: AppCompatActivity() {

    private lateinit var binding: ActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}