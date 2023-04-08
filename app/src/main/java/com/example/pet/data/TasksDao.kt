package com.example.pet.data

import androidx.room.*
import com.example.pet.domain.models.TaskEntity
import com.example.pet.ui.uiModels.TaskItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TasksDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(entity: TaskEntity)

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): Flow<List<TaskEntity>>

    @Query("DELETE FROM tasks WHERE id =:id")
    suspend fun deleteById(id: String)

    @Query("UPDATE tasks SET name = :name, timeStamp = :timeStamp  WHERE id =:id")
    suspend fun update(name: String, timeStamp: Long, id: String)

    @Transaction
    suspend fun select(id: String){
        onShowNotification(id)
        setCompleted(id)
        }

    @Query("UPDATE tasks SET completed = 1 where id =:id")
    suspend fun onShowNotification(id: String)

    @Query("UPDATE tasks SET completed = 1 where id =:id")
    suspend fun setCompleted(id: String)

    @Query("UPDATE tasks SET completed = 0 WHERE id =:id")
    suspend fun setUncompleted(id: String)


    @Query("SELECT * FROM tasks WHERE completed = 0")
    suspend fun getTaskstoNotificate(): TaskEntity?



}