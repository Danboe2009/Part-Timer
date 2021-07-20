package com.missingcontroller.parttimer

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.missingcontroller.parttimer.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var data: List<PartObject>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val request = RetroFitBuilder.buildService(PartsURLService::class.java)
        val response = request.listParts("V7xjN8ZQcio3RP2zp3Eqp09B6YAnKALvV2AaEM7baESVMLMjfOhaa7RuKUFXvncH")

        response.enqueue(object : Callback<List<PartObject>> {
            override fun onResponse(call: Call<List<PartObject>>, response: Response<List<PartObject>>) {
                if (response.isSuccessful){
                    println("Response: Success: ${response}")
                    data = response.body()!!
                    binding.rvPartsList.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(this@MainActivity)
                        adapter = PartListAdapter(data.sortedBy { it.date }, 1)
                    }
                } else {
                    println("Response: Not: ${response}")
                }
            }
            override fun onFailure(call: Call<List<PartObject>>, t: Throwable) {
                println("Response: Fail: ${t.message}")
                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })

        binding.fab.root.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onResume() {
        super.onResume()
    }
}