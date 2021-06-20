package com.example.todoapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.todoapp.adapter.ItemAdapter
import com.example.todoapp.database.Todo
import com.example.todoapp.database.TodoDatabase
import com.example.todoapp.databinding.FragmentHomeBinding
import com.example.todoapp.todo_view_model.TodoViewModel
import com.example.todoapp.todo_view_model.TodoViewModelFactory


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentHomeBinding>(inflater
            , R.layout.fragment_home, container, false)

        val application = requireNotNull(this.activity).application
        val dataSource = TodoDatabase.getInstance(application).todoDao

        val todoViewModelFactory = TodoViewModelFactory(dataSource)
        val todoViewModel =
            ViewModelProvider(
                this, todoViewModelFactory).get(TodoViewModel::class.java)


        var todos: List<Todo> = listOf()

        binding.recyclerView.adapter = ItemAdapter(todos, todoViewModel)
        binding.recyclerView.setHasFixedSize(true)

        binding.fab.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_addTodoFragment)
        }

        todoViewModel.todos.observe(viewLifecycleOwner, Observer {listOfTodos ->
            if(listOfTodos != null){
                todos = listOfTodos
            }
            binding.recyclerView.adapter = ItemAdapter(todos, todoViewModel)
        })

        return binding.root
    }


}