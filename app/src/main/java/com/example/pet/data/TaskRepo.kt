package com.example.pet.data

import com.example.pet.domain.models.TaskEntity

class TaskRepo(
    private val dao: TasksDao
) {

    suspend fun addTask(entity: TaskEntity) = dao.insertTask(entity)

    fun getAllTasks() = dao.getAllTasks()

}