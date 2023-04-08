package com.example.pet.ui.dogFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.pet.R
import com.example.pet.dase.RecyclerItemsInteractor
import com.example.pet.ui.adapter.TasksAdapter
import com.example.pet.databinding.ToDoListLayoutBinding
import com.example.pet.domain.models.TaskEntity
import com.example.pet.ui.uiModels.TaskItem
import com.example.pet.ui.utils.PermissionUtil
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

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
        checkPermission()
        binding.apply {
            todoListRecyclerview.adapter = adapter.also {
                it.bindActions(object : RecyclerItemsInteractor<TaskItem> {

                    override fun onClick(item: TaskItem) {
                        
                    }

                    override fun onLongClick(item: TaskItem, view: View) {
                        val popup =  PopupMenu(requireContext(), view)
                        popup.inflate(R.menu.task_popup_menu)
                        popup.setOnMenuItemClickListener {
                            when(it.itemId){
                                R.id.delete_menu_item ->{
                                    taskvm.deleteTask(item)
                                }
                                R.id.edit_menu_item ->{
                                    taskvm.updateItem(item)
                                }
                            }
                            popup.dismiss()
                            true
                        }
                        popup.show()
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
    private fun checkPermission() {
        if (!PermissionUtil.hasPermissions(requireContext())) {
            val resultLauncher =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                    doOnPermission()
                }
            PermissionUtil.registerLauncher(resultLauncher)
            val oldResultLauncher =
                registerForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
                    if (result) {
                        doOnPermission()
                    }
                }
            PermissionUtil.oldRegisterLauncher(oldResultLauncher)
            PermissionUtil.requestPermissions(requireActivity())
        }

    }
    private fun doOnPermission() {
        if (PermissionUtil.hasPermissions(requireContext())) {
            Toast.makeText(
                requireContext(),
                "permission",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}