package com.example.pet.data

import androidx.room.*
import com.example.pet.domain.models.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(entity: TaskEntity)

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("DELETE FROM tasks WHERE id =:id")
    suspend fun deleteById(id: String)

    @Transaction
    suspend fun select(id: String){
        removeSelect()
        selectById(id)
        }

    @Query("UPDATE data SET completed = 0")
    suspend fun removeSelect()

    @Query("UPDATE data SET completed = 1 where id =:id")
    suspend fun selectById(id: String)

    @Query("UPDATE data SET completed = 0 WHERE id =:id")
    suspend fun unselectById(id: String)

    @Query("SELECT * FROM tasks WHERE completed = 0")
    suspend fun getTaskstoNotificate(): TaskEntity?



}