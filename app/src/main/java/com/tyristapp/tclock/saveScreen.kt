package com.tyristapp.tclock

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_save_screen.*
import kotlinx.android.synthetic.main.recycler_row.*
import kotlinx.android.synthetic.main.recycler_row.view.*
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class saveScreen : AppCompatActivity() {
    var zamanListee = ArrayList<Long>()
    var gunListee = ArrayList<String>()
    var tf by Delegates.notNull<Boolean>()
    lateinit var sharedPreferences4 : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_screen)
        loadData()
        loadData2()
        buildRecyler()
        sharedPreferences4 = getSharedPreferences("com.tyristapp.tclock", MODE_PRIVATE)
        tf = sharedPreferences4.getBoolean("acildii",true)

        ilkmi(tf)


    }

    fun ilkmi(x : Boolean){
        var b = x
        val gunn = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
        sharedPreferences4 = getSharedPreferences("com.tyristapp.tclock", MODE_PRIVATE)
        if (b == true){
            gunListee.add(gunn)
            zamanListee.add(0)
            saveData()
            saveData2()
            b = false
            sharedPreferences4.edit().putBoolean("acildii", b).apply()
        }
    }


    private fun loadData() {
        val sharedPreferences = getSharedPreferences("com.tyristapp.tclock", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("task list", null)
        val type: Type = object : TypeToken<ArrayList<Long?>?>() {}.type
        zamanListee = gson.fromJson<Any>(json, type) as ArrayList<Long>
        if (zamanListee  == null) {
            zamanListee  = ArrayList()
        }
    }
    private fun loadData2() {
        val sharedPreferences = getSharedPreferences("com.tyristapp.tclock", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("date", null)
        val type: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        gunListee = gson.fromJson<Any>(json, type) as ArrayList<String>
        if (gunListee == null) {
            gunListee = ArrayList()
        }
    }

    fun buildRecyler(){
        val layoutManager = LinearLayoutManager(this)
        rows.layoutManager = layoutManager

        val adapter = RecyclerAdapter(zamanListee,gunListee)
        rows.adapter = adapter
    }

    private fun saveData() {
        val sharedPreferences = getSharedPreferences("com.tyristapp.tclock", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json: String = gson.toJson(zamanListee)
        editor.putString("task list", json)
        editor.apply()
    }
    private fun saveData2() {
        val sharedPreferences = getSharedPreferences("com.tyristapp.tclock", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val json2: String = gson.toJson(gunListee)
        editor.putString("date", json2)
        editor.apply()
    }

    fun lastSaveFun(view: View){
        saveData()
        saveData2()
    }

}