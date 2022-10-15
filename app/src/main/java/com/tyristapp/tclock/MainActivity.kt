package com.tyristapp.tclock

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun stopwatchMenuFun(view : View){
        val intent = Intent(applicationContext, stopwatchScreen::class.java)
        startActivity(intent)
    }
    fun saveMenuFun(view : View){
        val intent = Intent(applicationContext, saveScreen::class.java)
        startActivity(intent)
    }

}