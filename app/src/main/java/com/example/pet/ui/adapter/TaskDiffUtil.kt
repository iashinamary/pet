package com.example.pet.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.pet.ui.uiModels.TaskItem

class TaskDiffUtil(
    private val oldList: List<TaskItem>,
    private val newList: List<TaskItem>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id ==  newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}