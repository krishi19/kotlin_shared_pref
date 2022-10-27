


package com.example.broadcastreceiver

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class DashActivity : AppCompatActivity() {
    private lateinit var  button : Button
    private lateinit var builder : AlertDialog.Builder

    private lateinit var textUserName : TextView
    private lateinit var textEmail : TextView
    private lateinit var preferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash)
        button = findViewById(R.id.btn_exit)
        builder = AlertDialog.Builder(this)

        textUserName = findViewById(R.id.tv_username1)
        textEmail = findViewById(R.id.tv_email1)

//        val userName = intent.getStringExtra("username")
//        val email = intent.getStringExtra("email")
//
//        textUserName.text = "Username : " +userName
//        textEmail.text = "Email :" +email
        preferences = getSharedPreferences(RegisterActivity.FILE_NAME,Context.MODE_PRIVATE)
        val username = preferences.getString("user name","")
        val email = preferences.getString("email" , "")
        textUserName.text = username
        textEmail.text = email

        button.setOnClickListener {
            builder.setTitle("Alert!").setMessage("Do you want to exit?")
                .setCancelable(true)
                .setPositiveButton("Yes") {dialogInterface, it -> finish()}
                .setNegativeButton("No") {dialogInterface, it-> dialogInterface.cancel()}
                .setNeutralButton("Help") {dialogInterface, it->
                    Toast.makeText(this@DashActivity, "Help clicked", Toast.LENGTH_SHORT).show()}

                .show()

        }
        val logout:Button = findViewById(R.id.logout)
        logout.setOnClickListener(){
            preferences = getSharedPreferences(RegisterActivity.FILE_NAME,Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = preferences.edit()
            editor.clear()
            editor.apply()
//            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }


    }
}