package com.example.pet.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.pet.data.DataBase
import com.example.pet.data.TaskRepo
import com.example.pet.ui.dogFragments.TaskViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val module = module {


    viewModel {
        TaskViewModel(get())
    }

    single {
        Room.databaseBuilder(androidContext(), DataBase::class.java, "pet_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }


    single {
        get<DataBase>().getDao()
    }

    single {
        TaskRepo(get())
    }
}