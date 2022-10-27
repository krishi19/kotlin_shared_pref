package com.example.broadcastreceiver

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class RegisterActivity : AppCompatActivity() {
    companion object{
        lateinit var sharedPreferences: SharedPreferences
        var FILE_NAME: String = "shared_pref"


    }

    private  lateinit var  btnSave : Button
     private lateinit var username : EditText
     private lateinit var email : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        btnSave = findViewById(R.id.btn)


        btnSave.setOnClickListener{
            username = findViewById(R.id.editText_name)
            email = findViewById(R.id.editText_email)

//                .putExtra("username", editUsername.text.toString())
//                .putExtra("email", editEmail.text.toString()))
//
                  val Username= username.text.toString()

            val email = email.text.toString()
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("user name", Username)
            editor.putString("email" , email)
            editor.apply()
            startActivity(Intent())
            finish()
        }
    }
}