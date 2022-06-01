package com.missingcontroller.parttimer.part

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.missingcontroller.parttimer.CredentialManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

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

    var lifeSpanYear: String = "1"
        set(value) {
            field = value.trim()
        }

    var lifeSpanMileage: String = "10000"
        set(value) {
            field = value.trim()
        }

    var dateMonth: String = "01"
        set(value) {
            field = value.trim()
        }

    var dateDay: String = "10"
        set(value) {
            field = value.trim()
        }

    var dateYear: String = "1987"
        set(value) {
            field = value.trim()
        }
    var partConsumable: Boolean = false

    private val _onSubmitClick = MutableLiveData<Boolean>()
    val onSubmitClick: LiveData<Boolean>
        get() = _onSubmitClick

    fun onSubmitClicked() {
        _onSubmitClick.value = true
    }

    fun addPart() {
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(
            "$dateYear-$dateMonth-$dateDay"
        )
        println("Date: $date")
        val partToAdd = AddPartObject(
            partName,
            partMileage,
            date,
            partDescription,
            partConsumable,
            partType,
            CredentialManager.getUserId(),
            false,
            lifeSpanYear,
            lifeSpanMileage
        )
        coroutineScope.launch {
            println("Part To Add: $partToAdd")
            val deferredPartAdd =
                PartsApi.retrofitService.addPart(CredentialManager.getToken(), partToAdd)
            try {
                val result = deferredPartAdd.await()
                println("Part Add Result: $result")

            } catch (e: Exception) {
                println("Part Add Exception: $e")
            }
        }
    }
}