package com.example.pet.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.pet.dase.RecyclerItemsInteractor
import com.example.pet.databinding.TaskItemLayoutBinding
import com.example.pet.domain.models.TaskEntity
import com.example.pet.ui.uiModels.TaskItem

class TasksAdapter(): RecyclerView.Adapter<TaskViewHolder>() {
    private var list = listOf<TaskItem>()

    private var interactor: RecyclerItemsInteractor<TaskItem>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding =
            TaskItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int){
        val item = getItem(position)
        holder.bindTaskItem(item)
        holder.itemView.setOnClickListener {
            interactor?.onClick(item)
        }
        holder.itemView.setOnLongClickListener {
            interactor?.onLongClick(item, holder.itemView)
            true
        }
    }

    override fun getItemCount(): Int = list.size

    private fun getItem(position: Int) = list[position]


    fun add(newTaskItems: List<TaskItem>){
        val result = DiffUtil.calculateDiff(TaskDiffUtil(list, newTaskItems))
        list = newTaskItems
        result.dispatchUpdatesTo(this)
    }

    fun bindActions(interactor: RecyclerItemsInteractor<TaskItem>){
        this.interactor = interactor
    }

}