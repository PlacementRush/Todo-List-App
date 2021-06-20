package com.example.todoapp

// You can use this as a template for code for testing any room database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.todoapp.database.TodoDatabase
import com.example.todoapp.database.TodoDao
import com.example.todoapp.database.Todo
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class TodoDatabaseTest {

    private lateinit var todoDao: TodoDao
    private lateinit var db: TodoDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, TodoDatabase::class.java)
                // Allowing main thread queries, just for testing.
                .allowMainThreadQueries()
                .build()
        todoDao = db.todoDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetLatestTodo() {
        val todo = Todo(task = "Buy Orange")
        todoDao.insert(todo)
        val latestTodo = todoDao.getLatestTodo()
        assertEquals(latestTodo?.completed, false)
    }

    @Test
    @Throws(Exception::class)
    fun updateTodo() {
        val todo = todoDao.getLatestTodo()
        todo?.completed = true
        if (todo != null) {
            todoDao.update(todo)
        }
    }

    @Test
    @Throws(Exception::class)
    fun getAllTodos() {
        todoDao.getAllTodos()
    }

    @Test
    @Throws(Exception::class)
    fun clearTable() {
        todoDao.clear()
    }
}