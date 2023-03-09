package com.example.pet.ui.dogFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.pet.databinding.NewTaskSheetLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class NewTaskSheet : BottomSheetDialogFragment() {
    private lateinit var binding: NewTaskSheetLayoutBinding

    private val taskvm by sharedViewModel<TaskViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = NewTaskSheetLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.subbmitBtn.setOnClickListener {
            if (!binding.name.text.isNullOrEmpty()) {
                binding.name.text?.let {
                    taskvm.addTaskItem(it.toString())
                    dismiss()
                }
            } else {
               Toast.makeText(context, "Введите текст задачи", Toast.LENGTH_LONG).show()
            }
        }
    }
}