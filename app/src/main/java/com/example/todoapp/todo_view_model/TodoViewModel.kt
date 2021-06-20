package com.example.todoapp.todo_view_model

import androidx.lifecycle.*
import com.example.todoapp.database.Todo
import com.example.todoapp.database.TodoDao
import kotlinx.coroutines.launch

class TodoViewModel(val database: TodoDao) : ViewModel() {

    val todos: LiveData<List<Todo>> = database.getAllTodos()



    fun onAddTodo(task: String) {
        viewModelScope.launch {
            val newTodo = Todo(task = task)
            insert(newTodo)
        }
    }

    private suspend fun insert(todo: Todo) {
        database.insert(todo)
    }

    fun onUpdateTodo(todo: Todo) {
        viewModelScope.launch {
            update(todo)
        }
    }

    private suspend fun update(todo: Todo) {
        database.update(todo)
    }

    fun onDeleteTodo(todoToBeDeleted: Todo) {
        viewModelScope.launch {
            delete(todoToBeDeleted)
        }
    }

    private suspend fun delete(todo: Todo) {
        database.delete(todo)
    }


}