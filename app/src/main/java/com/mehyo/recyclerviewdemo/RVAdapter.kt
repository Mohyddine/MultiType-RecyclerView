package com.mehyo.recyclerviewdemo

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

interface OnItemClickedListener {
    fun onItemClicked(word: String)
}

class RVAdapter(
    private val list: ArrayList<Word>,
    private val itemClickedListener: OnItemClickedListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val VIEW_TYPE_HEADER = 1
        const val VIEW_TYPE_WORD = 2
        const val VIEW_TYPE_NUMBER_ROW = 3
    }


    class WordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var tvWord: TextView
        private lateinit var cvWord: CardView

        fun bind(word: Word, clickedListener: OnItemClickedListener) {
            tvWord = itemView.findViewById(R.id.tvWord)
            cvWord = itemView.findViewById(R.id.cvWord)

            tvWord.text = word.text

            cvWord.setCardBackgroundColor(Color.parseColor(word.color))
            cvWord.setOnClickListener {
                clickedListener.onItemClicked(word.text)
            }
        }
    }

    class NumberRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var rvNumbers: RecyclerView

        fun bind(numbersList: ArrayList<Number>) {
            rvNumbers = itemView.findViewById(R.id.rvNumbers)
            rvNumbers.apply {
                layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                isNestedScrollingEnabled = false
                setHasFixedSize(true)
                adapter = NumberRowAdapter(numbersList)
            }
        }
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int) =
        when {
            position == 0 -> 1
            position % 4 == 0 -> 3
            else -> 2
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {
            VIEW_TYPE_HEADER -> {
                HeaderViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.row_header, parent, false)
                )
            }
            VIEW_TYPE_NUMBER_ROW -> {
                NumberRowViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.row_ribbon_numbers, parent, false)
                )
            }
            else -> {
                WordViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.row_word, parent, false)
                )
            }
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when {
            position == 0 -> {
                (holder as HeaderViewHolder)
            }
            position % 4 == 0 -> {
                (holder as NumberRowViewHolder).bind(Data.numbersList)
            }
            else -> {
                (holder as WordViewHolder).bind(list[position], itemClickedListener)
            }
        }
    }

    override fun getItemCount() = list.size
}