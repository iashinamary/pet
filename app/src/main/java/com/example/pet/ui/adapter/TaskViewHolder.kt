package com.example.pet.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.pet.databinding.TaskItemLayoutBinding
import com.example.pet.domain.models.TaskEntity
import com.example.pet.ui.uiModels.TaskItem

class TaskViewHolder(private val binding: TaskItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    fun bindTaskItem(taskItem: TaskItem){
        binding.taskName.text = taskItem.name

        binding.dueTime.text = taskItem.creationTime

        binding.completeButton.isChecked = taskItem.completed
    }
}