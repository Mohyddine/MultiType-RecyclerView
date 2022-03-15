package com.mehyo.recyclerviewdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(),OnItemClickedListener {

    private lateinit var rvMain:RecyclerView
    private lateinit var myAdapter:RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvMain=findViewById(R.id.rvMain)



        myAdapter= RVAdapter(Data.wordsList,this@MainActivity)

        rvMain.apply {
            adapter=myAdapter
            layoutManager=LinearLayoutManager(this@MainActivity)
        }

    }

    override fun onItemClicked(word: String) {
        Toast.makeText(this@MainActivity, word, Toast.LENGTH_SHORT).show()
        startActivity(Intent(this@MainActivity,SecondActivity::class.java)
            .putExtra("item",word))
    }

}