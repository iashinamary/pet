package com.example.pet.dogFragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pet.databinding.NewTaskSheetLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewTaskSheet: BottomSheetDialogFragment() {
    private lateinit var binding: NewTaskSheetLayoutBinding

            override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewTaskSheetLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }
}