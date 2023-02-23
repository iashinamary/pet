package com.example.pet.dogFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
                }
            } else {
//                Toast.makeText().show()
            }
        }
    }
}