package com.example.pet.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pet.databinding.TaskItemLayoutBinding
import com.example.pet.dogFragments.TaskItem

class TasksAdapter(): RecyclerView.Adapter<TaskViewHolder>() {
    private var list = listOf<TaskItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding =
            TaskItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int){
        holder.bindTaskItem(getItem(position))
    }

    override fun getItemCount(): Int = list.size

    private fun getItem(position: Int) = list[position]


    fun add(newTaskItem: List<TaskItem>){
        list.toMutableList().addAll(newTaskItem)
    }

}