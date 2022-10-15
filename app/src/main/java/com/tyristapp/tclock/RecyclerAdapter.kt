package com.tyristapp.tclock

import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.recycler_row.view.*

class RecyclerAdapter(val deger : ArrayList<Long>, val gunler : ArrayList<String>):RecyclerView.Adapter<RecyclerAdapter.kayitliSureler>() {
    class kayitliSureler(itemView: View): RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): kayitliSureler {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row,parent,false)
        return kayitliSureler(itemView)
    }

    override fun onBindViewHolder(holder: kayitliSureler, position: Int) {

        holder.itemView.savedChronometer.base = SystemClock.elapsedRealtime() + deger.get(position)

        // val date_n: String = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(Date())
        holder.itemView.dates.text = gunler.get(position)

        holder.itemView.removeButton.setOnClickListener {
            deger.removeAt(position)
            gunler.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position,deger.size)
            notifyItemRangeChanged(position,gunler.size)
            Toast.makeText(holder.itemView.context, "Please click the save button", Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return deger.size
        //kac tane oldugu
    }
}