package com.example.numbersimple.service

import android.app.Service
import android.content.Intent
import android.os.IBinder

class SimpleNumberService : Service()  {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        sendBroadcast(
            Intent("calculate").putExtra(
                "N",
                simpleNumber(intent?.getIntExtra("N", 1) ?: 1)
            )
        )
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

        private fun simpleNumber(N: Int): Boolean {
            var count = 0
            var temp = 0

            for(i in 1..N) {
                temp = N % i
                if (temp == 0){
                    count++
                }
            }

            return count == 0
        }
}
