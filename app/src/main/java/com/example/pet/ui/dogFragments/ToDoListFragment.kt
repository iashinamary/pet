package com.example.pet.ui.dogFragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.pet.R
import com.example.pet.dase.RecyclerItemsInteractor
import com.example.pet.ui.adapter.TasksAdapter
import com.example.pet.databinding.ToDoListLayoutBinding
import com.example.pet.ui.adapter.TaskViewHolder
import com.example.pet.ui.uiModels.TaskItem
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
            todoListRecyclerview.adapter = adapter.also {
                it.bindActions(object: RecyclerItemsInteractor<TaskItem>{

                    override fun onClick(item: TaskItem) {
                        
                    }

                    override fun onLongClick(item: TaskItem, view: View) {

                    }

                })
            }
            newTaskButton.setOnClickListener {
                findNavController().navigate(R.id.action_toDoListFragment_to_newTaskSheet)
            }

            lifecycleScope.launchWhenStarted {
                taskvm.tasksFlow.collectLatest {
                    adapter.add(it)
                }
            }
        }
    }
}