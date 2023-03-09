package com.example.pet.ui.uiModels

data class TaskItem(
    val name: String,
    val completed: Boolean = false,
    val creationTime: String,
    val id: String
)