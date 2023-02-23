package com.example.pet.dase

import android.view.View

interface RecyclerItemsInteractor<T> {

    fun onClick(item: T)

    fun onLongClick(item: T, view: View)
}