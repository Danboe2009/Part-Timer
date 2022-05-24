package com.missingcontroller.parttimer

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.missingcontroller.parttimer.PartsTimerApplication.Companion.context

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PartsTimerApplication.mainActivity = this
        setContentView(R.layout.activity_main)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
    }

    fun badPassword() {
        runOnUiThread {
            Toast.makeText(context, "Bad Login", Toast.LENGTH_LONG).show()
            logout()
        }
    }

    fun logout() {
        CredentialManager.erasePrefs()
        val action = MainFragmentDirections.navigationMainFragmentToLoginFragment()
        runOnUiThread {
            navController.navigate(action)
        }
    }
}