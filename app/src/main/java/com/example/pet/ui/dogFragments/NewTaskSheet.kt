package com.example.pet.ui.dogFragments

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.work.WorkManager
import com.example.pet.databinding.NewTaskSheetLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.util.*
import java.util.concurrent.TimeUnit

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
        binding.timePick.setIs24HourView(true)
        binding.datapicker.minDate = System.currentTimeMillis()
        binding.subbmitBtn.setOnClickListener {
            val datePicker = binding.datapicker
            val timePickerCompact = binding.timePick
            val customCalendar = Calendar.getInstance()
            customCalendar.set(datePicker.year, datePicker.month, datePicker.dayOfMonth, timePickerCompact.hour, timePickerCompact.minute)
            val customTime = customCalendar.timeInMillis
            if (!binding.name.text.isNullOrEmpty() && System.currentTimeMillis() < customTime) {
                binding.name.text?.let {
                    taskvm.addTaskItem(
                        it.toString(),
                        customTime,
                        WorkManager.getInstance(
                            requireContext()
                        )
                    )
                    dismiss()
                }
            } else if(binding.name.text.isNullOrEmpty()) {
               Toast.makeText(context, "Введите текст задачи", Toast.LENGTH_LONG).show()
            } else if(System.currentTimeMillis() > customTime){
                Toast.makeText(context, "Неправильное врмея", Toast.LENGTH_LONG).show()
            }
        }
    }
}