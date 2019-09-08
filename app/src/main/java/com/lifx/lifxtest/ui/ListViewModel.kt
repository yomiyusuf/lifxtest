package com.lifx.lifxtest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lifx.lifxtest.model.ListItem
import com.lifx.lifxtest.repo.ListRepository

class ListViewModel: ViewModel() {
    val list: LiveData<List<ListItem>> = ListRepository.getList()

    fun cancelJobs(){
        ListRepository.cancelJobs()
    }
}