package com.missingcontroller.parttimer

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.await

class MainViewModel : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private val _partList = MutableLiveData<List<PartObject>>()
    val partList: MutableLiveData<List<PartObject>>
        get() = _partList

    fun getPartsList() {
        coroutineScope.launch {
            val deferredPartList = PartsApi.retrofitService.listParts(CredentialManager.getToken())
            try {
                val result = deferredPartList.await()
               _partList.value = result
            } catch (e: Exception) {
                println("Parts List Exception: $e")
            }
        }
    }
}