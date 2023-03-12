package com.example.pet.ui.dogFragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pet.data.TaskRepo
import com.example.pet.domain.models.TaskEntity
import com.example.pet.ui.uiModels.TaskItem
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*

class TaskViewModel(
    private val taskRepo: TaskRepo
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

    fun deleteTask(item: TaskItem) {
        viewModelScope.launch {
            taskRepo.deleteTask(item.id)
        }
    }
}