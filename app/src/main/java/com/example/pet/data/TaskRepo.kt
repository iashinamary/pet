package com.example.pet.data

import com.example.pet.domain.models.TaskEntity
import com.example.pet.ui.uiModels.TaskItem

class TaskRepo(
    private val dao: TasksDao
) {

    suspend fun addTask(entity: TaskEntity) = dao.insertTask(entity)

    fun getAllTasks() = dao.getAllTasks()

    suspend fun onShow(id: String) = dao.onShowNotification(id)
    suspend fun deleteTask(id: String) = dao.deleteById(id)

    suspend fun saveCheck(id: String) = dao.select(id)

    suspend fun getTaskToNotificate(id: String) = dao.getTaskstoNotificate(id)

    suspend fun setUncomplited(id: String) = dao.setUncompleted(id)

    suspend fun update(name: String, timeStamp: Long, id: String) = dao.update(name, timeStamp, id)

}