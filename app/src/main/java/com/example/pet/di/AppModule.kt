package com.example.pet.di

import com.example.pet.dogFragments.TaskViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val module = module {
    viewModel {
        TaskViewModel()
    }
}