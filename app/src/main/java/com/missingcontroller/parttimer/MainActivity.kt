package com.missingcontroller.parttimer

import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.missingcontroller.parttimer.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var data: List<PartObject>
    private lateinit var navController: NavController
    private var bottomNavigationView: BottomNavigationView? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

    }

//        val request = RetroFitBuilder.buildService(PartsURLService::class.java)
//        val response = request.listParts("V7xjN8ZQcio3RP2zp3Eqp09B6YAnKALvV2AaEM7baESVMLMjfOhaa7RuKUFXvncH")
//
//        response.enqueue(object : Callback<List<PartObject>> {
//            override fun onResponse(call: Call<List<PartObject>>, response: Response<List<PartObject>>) {
//                if (response.isSuccessful){
//                    println("Response: Success: ${response}")
//                    data = response.body()!!
//                    binding.rvPartsList.apply {
//                        setHasFixedSize(true)
//                        layoutManager = LinearLayoutManager(this@MainActivity)
//                        adapter = PartListAdapter(data.sortedBy { it.date }, 1)
//                    }
//                } else {
//                    println("Response: Not: ${response}")
//                    if(response.code() == 401){
//
//                    }
//                }
//            }
//            override fun onFailure(call: Call<List<PartObject>>, t: Throwable) {
//                println("Response: Fail: ${t.message}")
//                Toast.makeText(this@MainActivity, "${t.message}", Toast.LENGTH_SHORT).show()
//            }

//        binding.fab.root.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
}