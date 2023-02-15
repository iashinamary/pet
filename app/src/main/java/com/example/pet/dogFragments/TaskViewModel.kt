package com.example.pet.dogFragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import java.time.LocalTime
import java.util.*

class TaskViewModel: ViewModel() {

    private val _tasksFlow = MutableSharedFlow<List<TaskItem>>()
    val tasksFlow: SharedFlow<List<TaskItem>> = _tasksFlow

    private val cache = mutableListOf<TaskItem>()

    fun addTaskItem(taskText: String){
        val newTask = TaskItem(
            taskText,
            System.currentTimeMillis(),
            UUID.randomUUID().toString()
        )
        cache.add(newTask)
        viewModelScope.launch {
            _tasksFlow.emit(cache)
        }
    }
}