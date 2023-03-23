package com.example.pet.ui.dogFragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.pet.OneTimeWorkRequest.TaskWorker
import com.example.pet.data.TaskRepo
import com.example.pet.domain.models.TaskEntity
import com.example.pet.ui.uiModels.TaskItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit

class TaskViewModel(
    private val taskRepo: TaskRepo,
): ViewModel() {

    val tasksFlow = taskRepo.getAllTasks().map {list->
        list.map {
            it.toItem()
        }
    }

    fun addTaskItem(taskText: String, timeToStart: Long){
        viewModelScope.launch {
            val newTask = TaskEntity(
                taskText,
                false,
                timeToStart,
                UUID.randomUUID().toString()
            )
            taskRepo.addTask(newTask)
        }
    }

    fun saveChecked(item: TaskEntity) {
        viewModelScope.launch {
            if (!item.completed) {
                taskRepo.saveCheck(item.id)
            } else {
                taskRepo.unselect(item.id)
            }
        }
    }

    fun deleteTask(item: TaskItem) {
        viewModelScope.launch {
            taskRepo.deleteTask(item.id)
        }
    }


}