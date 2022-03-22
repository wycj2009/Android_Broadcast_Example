package com.example.android_broadcast_example

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class TestReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, "${intent.action}", Toast.LENGTH_SHORT).show()
    }

}