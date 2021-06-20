package com.example.todoapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.database.TodoDatabase
import com.example.todoapp.databinding.FragmentAddTodoBinding
import com.example.todoapp.todo_view_model.TodoViewModel
import com.example.todoapp.todo_view_model.TodoViewModelFactory


class AddTodoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentAddTodoBinding>(inflater
            , R.layout.fragment_add_todo, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = TodoDatabase.getInstance(application).todoDao

        val todoViewModelFactory = TodoViewModelFactory(dataSource)
        val todoViewModel =
            ViewModelProvider(
                this, todoViewModelFactory).get(TodoViewModel::class.java)

        binding.addButton.setOnClickListener{
            todoViewModel.onAddTodo(binding.enteredTodo.text.toString())
            Log.i("AddTodoFragment", "Inserted the todo!")
            parentFragmentManager.popBackStack()
        }

        return binding.root
    }
}