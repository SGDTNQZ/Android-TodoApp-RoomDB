package com.projects.myapplication.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.projects.myapplication.ToDo

@Dao
interface TodoDao {

    @Query("SELECT * FROM TODO")
    fun getAllTodo() : LiveData<List<ToDo>>

    @Insert
    fun addTodo(toDo: ToDo)

    @Query("DELETE FROM TODO WHERE id = :id")
    fun deleteTodo(id : Int)
}