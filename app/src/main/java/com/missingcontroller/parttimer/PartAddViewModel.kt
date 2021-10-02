package com.missingcontroller.parttimer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PartAddViewModel : ViewModel() {
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    var partName: String = "Water Cooler"
        set(value) {
            field = value.trim()
        }
    var partMileage: String = "100400"
        set(value) {
            field = value.trim()
        }
    var partDescription: String = "It cools the water"
        set(value) {
            field = value.trim()
        }
    var partType: String = "Water Cooler"
        set(value) {
            field = value.trim()
        }
    var partConsumable: Boolean = false

    private val _onSubmitClick = MutableLiveData<Boolean>()
    val onSubmitClick: LiveData<Boolean>
        get() = _onSubmitClick

    fun onSubmitClicked(){
        _onSubmitClick.value = true
    }

    fun addPart() {
        val partToAdd = AddPartObject(
            partName,
            partMileage,
            java.util.Calendar.getInstance().time,
            partDescription,
            partConsumable,
            partType,
            CredentialManager.getUserId(),
            false)
        coroutineScope.launch {
            println("Part To Add: $partToAdd")
            val deferredPartAdd = PartsApi.retrofitService.addPart(CredentialManager.getToken(),partToAdd)
            try {
                val result = deferredPartAdd.await()
                println("Part Add Result: $result")

            } catch (e: Exception) {
                println("Part Add Exception: $e")
            }
        }
    }
}