package com.tyristapp.tclock

import android.content.SharedPreferences
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.os.SystemClock
import android.view.View
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_stopwatch_screen.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class stopwatchScreen : AppCompatActivity() {

    var zamaniAl :Long = 0
    var zamanListe = ArrayList<Long>()
    var gunListe = ArrayList<String>()
    lateinit var gun : String
    var kaydedilecekDeger :Long = 0
    var t by Delegates.notNull<Boolean>()
    lateinit var sharedPreferences3 : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stopwatch_screen)
        stopButton.visibility = View.GONE
        contiuneButton.visibility = View.GONE
        resetButton.visibility = View.GONE
        saveButton.visibility = View.GONE

        sharedPreferences3 = getSharedPreferences("com.tyristapp.tclock", MODE_PRIVATE)
        t = sharedPreferences3.getBoolean("acildi",true)

        ilkmi(t)

    }

    fun ilkmi(x : Boolean){
        var b = x
        val gunn = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
        sharedPreferences3 = getSharedPreferences("com.tyristapp.tclock", MODE_PRIVATE)
        if (b == true){
            gunListe.add(gunn)
            zamanListe.add(0)
            saveData()
            saveData2()
            b = false
            sharedPreferences3.edit().putBoolean("acildi", b).apply()
        }
    }

    fun startFun(view: View){
        chronometer.base = SystemClock.elapsedRealtime() + zamaniAl
        chronometer.start()
        startButton.visibility = View.GONE
        stopButton.visibility = View.VISIBLE
        loadData()
        loadData2()

    }

    fun stopFun (view: View){
        zamaniAl = chronometer.base- SystemClock.elapsedRealtime()
        chronometer.stop()
        contiuneButton.visibility = View.VISIBLE
        resetButton.visibility = View.VISIBLE
        saveButton.visibility = View.VISIBLE
        stopButton.visibility = View.GONE
    }

    fun resetFun (view: View){
       chronometer.text = "00:00"
        zamaniAl = 0
        contiuneButton.visibility = View.GONE
        resetButton.visibility = View.GONE
        saveButton.visibility = View.GONE
        stopButton.visibility = View.GONE
        startButton.visibility = View.VISIBLE
    }

    fun contiuneFun (view: View){
        chronometer.base = SystemClock.elapsedRealtime() + zamaniAl
        chronometer.start()
        resetButton.visibility = View.GONE
        stopButton.visibility = View.VISIBLE
        saveButton.visibility = View.GONE
        contiuneButton.visibility = View.GONE
    }

    fun saveFun (view: View){
        gun = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
        kaydedilecekDeger =  chronometer.base-SystemClock.elapsedRealtime()
        gunListe.add(gun)
        zamanListe.add(kaydedilecekDeger)
        saveData()
        saveData2()
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("com.tyristapp.tclock", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(zamanListe)
        editor.putString("task list", json)
        editor.apply()
    }

    private fun loadData() {
        val sharedPreferences = getSharedPreferences("com.tyristapp.tclock", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("task list", null)
        val type: Type = object : TypeToken<ArrayList<Long?>?>() {}.type
        zamanListe = gson.fromJson<Any>(json, type) as ArrayList<Long>
        if (zamanListe  == null) {
            zamanListe  = ArrayList()
        }
    }

    private fun saveData2() {
        val sharedPreferences = getSharedPreferences("com.tyristapp.tclock", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json2: String = gson.toJson(gunListe)
        editor.putString("date", json2)
        editor.apply()
    }

    private fun loadData2() {
        val sharedPreferences = getSharedPreferences("com.tyristapp.tclock", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("date", null)
        val type: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        gunListe = gson.fromJson<Any>(json, type) as ArrayList<String>
        if (gunListe == null) {
            gunListe = ArrayList()
        }
    }



}