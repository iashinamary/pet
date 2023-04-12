package com.example.pet.ui.dogFragments

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.pet.oneTimeWorkRequest.TaskWorker
import com.example.pet.data.TaskRepo
import com.example.pet.domain.models.TaskEntity
import com.example.pet.ui.uiModels.TaskItem
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit

class TaskViewModel(
    private val taskRepo: TaskRepo,
) : ViewModel() {

    val tasksFlow = taskRepo.getAllTasks().map { list ->
        list.map {
            it.toItem()
        }
    }

    fun addTaskItem(taskText: String, timeToStart: Long, workManager: WorkManager) {
        viewModelScope.launch {
            val uuid =  UUID.randomUUID()
            val newTask = TaskEntity(
                taskText,
                false,
                timeToStart,
                uuid.toString(),
                false
            )
            taskRepo.addTask(newTask)
            val customDelay =  timeToStart - System.currentTimeMillis()
            val workRequest = OneTimeWorkRequestBuilder<TaskWorker>()
                .setId(uuid)
                .setInitialDelay(customDelay, TimeUnit.MILLISECONDS)
                .build()
            workManager.enqueue(workRequest)
        }
    }

    fun saveChecked(item: TaskEntity) {
        viewModelScope.launch {
            if (!item.completed) {
                taskRepo.saveCheck(item.id)
            } else {
                taskRepo.setUncomplited(item.id)
            }
        }
    }

    fun deleteTask(item: TaskItem) {
        viewModelScope.launch {
            taskRepo.deleteTask(item.id)
        }
    }



}