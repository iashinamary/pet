package com.example.pet.dogFragments

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.time.LocalTime
import java.util.*

class TaskViewModel: ViewModel() {

    var taskItems = MutableLiveData<MutableList<TaskItem>>()

    init {
        taskItems.value = mutableListOf()
    }

    fun addTaskItem(newTask: TaskItem){
        val list = taskItems.value
        list!!.add(newTask)
        taskItems.postValue(list!!)
    }

    fun updateTaskItem(id: UUID, name: String, dueTime: LocalTime?){
        val list = taskItems.value
        val task = list!!.find { it.id == id }
        task!!.name = name
        task!!.dueTime = dueTime
        taskItems.postValue(list!!)
    }

}