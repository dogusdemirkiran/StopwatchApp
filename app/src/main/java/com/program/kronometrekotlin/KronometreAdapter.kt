package com.program.kronometrekotlin

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_kronometre.view.*
import kotlinx.android.synthetic.main.item_row.view.*

class KronometreAdapter(val kronometreLap: ArrayList<String>): RecyclerView.Adapter<KronometreHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KronometreHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_row,parent,false)
        return KronometreHolder(view)
    }

    override fun onBindViewHolder(holder: KronometreHolder, position: Int) {
        holder.itemView.textView_RecyclerTime.text = "${position+1} ${kronometreLap[position]}"

    }

    override fun getItemCount(): Int {
        return kronometreLap.size
    }
}

class KronometreHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
}
