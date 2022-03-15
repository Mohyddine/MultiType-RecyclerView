package com.mehyo.recyclerviewdemo

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class NumberRowAdapter(private val list:ArrayList<Number>) :RecyclerView.Adapter<NumberRowAdapter.NumberViewHolder>(){

    class NumberViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var tvNumber: TextView
        lateinit var cvNumber: CardView

        fun bind(num:Number){
            tvNumber=itemView.findViewById(R.id.tvNumber)
            cvNumber=itemView.findViewById(R.id.cvNumber)

            tvNumber.text=num.number.toString()

            cvNumber.setCardBackgroundColor(Color.parseColor(num.color))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder
    = NumberViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_number, parent, false))

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) =  holder.bind(list[position])

    override fun getItemCount() = list.size
}

