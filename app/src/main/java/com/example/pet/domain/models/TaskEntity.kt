package com.example.pet.domain.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.pet.ui.uiModels.TaskItem
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "tasks")
data class TaskEntity(
    val name: String,
    val completed: Boolean,
    @ColumnInfo(name = "time_stamp")
    val timeStamp: Long,
    @PrimaryKey
    val id: String
) {

    fun toItem(): TaskItem {
        val date = SimpleDateFormat(
            "hh:mm", Locale.getDefault()
        ).format(Date(timeStamp))
        return TaskItem(name, completed, date, id)
    }

}