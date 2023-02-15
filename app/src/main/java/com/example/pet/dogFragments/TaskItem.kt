package com.example.pet.dogFragments

import java.time.LocalTime
import java.util.*

class TaskItem(
    var name: String,
    var dueTime: LocalTime?,
    var completedDate: LocalTime?,
    var id: UUID = UUID.randomUUID()
){

}