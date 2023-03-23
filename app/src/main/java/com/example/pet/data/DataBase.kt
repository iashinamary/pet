package com.example.pet.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pet.domain.models.TaskEntity

@Database(entities = [TaskEntity::class], version = 2)
abstract class DataBase: RoomDatabase() {

    abstract fun getDao(): TasksDao
}