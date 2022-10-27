package com.example.broadcastreceiver

import android.content.*
import android.os.BatteryManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var  button : Button
    lateinit var progressview : ProgressBar
    lateinit var sharedPreferences: SharedPreferences
    var txtBatteryStatus : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       sharedPreferences = getSharedPreferences(RegisterActivity.FILE_NAME,Context.MODE_PRIVATE)
        if(sharedPreferences.getString("user name","")!=""){
            val intent = Intent(this,DashActivity::class.java)
            startActivity(intent)
            finish()
        }
        setContentView(R.layout.activity_main)


        txtBatteryStatus = findViewById(R.id.txtBatteryStatus)
        progressview = findViewById(R.id.progressbar)
        button = findViewById(R.id.btn_register)
        button.setOnClickListener{
            val intent = Intent(this,RegisterActivity::class.java)
            intent.putExtra("username" , "email")
            startActivity(intent)
            finish()
        }

        registerReceiver(this.mBatteryInfo, IntentFilter(Intent.ACTION_BATTERY_CHANGED))

    }



    private val mBatteryInfo : BroadcastReceiver = object : BroadcastReceiver () {
        override fun onReceive(context: Context?, intent: Intent?) {
            val level = intent!!.getIntExtra(BatteryManager.EXTRA_LEVEL,1)
//            val scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE,1)
            progressview.progress = level

//            val batteryPercent = level * 100/ scale.toFloat()

//            txtBatteryStatus!!.text = "Battery Status : $batteryPercent%"
        }
    }


}