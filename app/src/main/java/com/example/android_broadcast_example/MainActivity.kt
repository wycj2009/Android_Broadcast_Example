package com.example.android_broadcast_example

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android_broadcast_example.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var testReceiver: BroadcastReceiver? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configureReceiver()

        binding.send.setOnClickListener {
            send()
        }
    }

    private fun configureReceiver() {
        testReceiver = TestReceiver().also {
            val filter = IntentFilter().apply {
                addAction("com.example.android_broadcast_example")
                addAction("android.intent.action.ACTION_POWER_DISCONNECTED")
                /**
                 * 암시적 브로드캐스트 예외
                 * https://developer.android.com/guide/components/broadcast-exceptions
                 */
            }
            registerReceiver(it, filter)
        }
    }

    private fun send() {
        val intent = Intent().apply {
            action = "com.example.android_broadcast_example"
            flags = Intent.FLAG_INCLUDE_STOPPED_PACKAGES
        }
        sendBroadcast(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(testReceiver)
    }

}