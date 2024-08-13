package com.projects.myapplication.ViewModels

import android.icu.util.Calendar
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.projects.myapplication.MainApplication
import com.projects.myapplication.ToDo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel : ViewModel(){

    val todoDao = MainApplication.todoDatabase.getTodoDao()

    val todoList : LiveData<List<ToDo>> = todoDao.getAllTodo()


    fun addToDo(title : String){
//        Creating a thread
        viewModelScope.launch (Dispatchers.IO) {
            todoDao.addTodo(ToDo(title = title, createdAt = Calendar.getInstance().time ))
        }
    }

    fun deleteToDo(id : Int){
        viewModelScope.launch (Dispatchers.IO) {
            todoDao.deleteTodo(id)
        }
    }
}