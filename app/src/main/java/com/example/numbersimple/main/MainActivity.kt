package com.example.numbersimple.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.numbersimple.R
import com.example.numbersimple.service.SimpleNumberService

class MainActivity : AppCompatActivity() {
    private var tempNumber = 0
    private val broadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if ("calculate" == intent.action) {
                val result = intent.getIntExtra("N", 1)
                showInf(result.toString())
                Log.d("BelyaevNP ", result.toString())
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        registerReceiver(broadcastReceiver, IntentFilter("calculate"))
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(broadcastReceiver)
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        savedInstanceState.putInt("tempNumber", tempNumber)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        tempNumber = savedInstanceState.getInt("tempNumber") + 1
        Log.w("BelelyaevNP ", tempNumber.toString())
        startService(
            Intent(
                this, SimpleNumberService::class.java
            ).putExtra("N", tempNumber)
        )
    }

    private fun showInf(line: String) {
        val toastGreet = Toast.makeText(this, line, Toast.LENGTH_SHORT)
        toastGreet.show()
    }
}