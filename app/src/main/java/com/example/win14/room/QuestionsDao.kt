package com.example.win14.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.win14.model.FirstQuestions

@Dao
interface QuestionsDao {
    @Query("SELECT * FROM quiz_table")
    fun getAll():List<FirstQuestions>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(firstQuestions: FirstQuestions)

    @Delete()
    suspend fun delete(firstQuestions: FirstQuestions)

    @Query("DELETE FROM quiz_table")
    suspend fun deleteDatabase()

}