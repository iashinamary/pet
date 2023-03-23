package com.example.pet.data

import com.example.pet.domain.models.TaskEntity

class TaskRepo(
    private val dao: TasksDao
) {

    suspend fun addTask(entity: TaskEntity) = dao.insertTask(entity)

    fun getAllTasks() = dao.getAllTasks()
    suspend fun deleteTask(id: String)  = dao.deleteById(id)

    suspend fun saveCheck(id: String) = dao.select(id)

    suspend fun getTaskToNotificate() = dao.getTaskstoNotificate()

    suspend fun unselect(id: String) = dao.unselectById(id)

}