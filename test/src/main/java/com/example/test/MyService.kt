package com.example.test

import android.app.Service
import android.bluetooth.BluetoothClass
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MyService: Service() {

    val D_HELLO = "com.cg.d.HELLO"
    var intent: Intent? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return super.onStartCommand(intent, flags, startId)
    }


    override fun onBind(intent: Intent?): IBinder {
        this.intent = intent
        if(intent!!.action == D_HELLO){

        }
        return Binder()
    }

}