package com.example.pet.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.pet.databinding.TaskItemLayoutBinding
import com.example.pet.dogFragments.TaskItem
import java.time.format.DateTimeFormatter
import java.util.*

class TaskViewHolder(val binding: TaskItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {

    fun bindTaskItem(taskItem: TaskItem){
        binding.taskName.text = taskItem.name

        binding.dueTime.text
    }
}