package com.missingcontroller.parttimer

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.missingcontroller.parttimer.databinding.ActivityMainBinding
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

        val date = SimpleDateFormat("dd-MM-yyyy", Locale.US).parse("10-10-2020")
        val longDate = SimpleDateFormat("dd-MM-yyyy", Locale.US).parse("10-10-2005")
        val wedDate = SimpleDateFormat("dd-MM-yyyy", Locale.US).parse("13-05-2018")


        data = mutableListOf(
            PartObject("Cold Air", Date(), 94000),
            PartObject("Cold Air", date, 94000),
            PartObject("Cold Air", longDate, 94000),
            PartObject("Cold Air", wedDate, 94000)
        )

        binding.fab.root.setOnClickListener {
			view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.rvPartsList.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PartListAdapter(data.sortedBy { it.installDate }, 1)
        }
    }
}