package com.lifx.lifxtest.repo

import androidx.lifecycle.LiveData
import com.lifx.lifxtest.model.ListItem
import com.lifx.lifxtest.network.RetrofitBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object ListRepository {
    var job: CompletableJob? = null

    fun getList(): LiveData<List<ListItem>>{
        job = Job()
        return object: LiveData<List<ListItem>>(){
            override fun onActive() {
                super.onActive()
                job?.let{ theJob ->
                    CoroutineScope(IO + theJob ).launch {
                        val list = RetrofitBuilder.apiService.getList()
                        withContext(Main){
                            value = list
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs(){
        job?.cancel()
    }
}