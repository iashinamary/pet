package com.example.pet.dogFragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.pet.adapter.TasksAdapter
import com.example.pet.databinding.ToDoListLayoutBinding
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*


class ToDoListFragment : Fragment() {
    private lateinit var binding: ToDoListLayoutBinding
    private val taskvm by sharedViewModel<TaskViewModel>()
    private val adapter by lazy { TasksAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ToDoListLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            todoListRecyclerview.adapter = adapter
            newTaskButton.setOnClickListener {
        NewTaskSheet().show(parentFragmentManager, "newTaskTag")
    }

    lifecycleScope.launchWhenStarted {
        taskvm.tasksFlow.collectLatest {
            adapter.add(it)
        }
    }
        }
    }
}