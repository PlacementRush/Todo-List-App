package com.example.todoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.R
import com.example.todoapp.database.Todo
import com.example.todoapp.todo_view_model.TodoViewModel


class ItemAdapter(
    private val dataset: List<Todo>,
    private val viewModel: TodoViewModel) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_title)
        val deleteButton: ImageView = view.findViewById(R.id.delete_icon)
        val checkBox: CheckBox = view.findViewById(R.id.completed_checkbox)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.textView.text =  dataset[position].task
        holder.checkBox.isChecked = dataset[position].completed

        holder.deleteButton.setOnClickListener {
            viewModel.onDeleteTodo(dataset[position])
        }

        holder.checkBox.setOnClickListener {
            holder.checkBox.isChecked = !dataset[position].completed
            dataset[position].completed = !dataset[position].completed
            viewModel.onUpdateTodo(dataset[position])
        }
    }
}