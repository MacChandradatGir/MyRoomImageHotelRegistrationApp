package com.example.myroomimagehotelregistrationapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myroomimagehotelregistrationapp.R
import com.example.myroomimagehotelregistrationapp.adapter.MyListRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

     var roomList:MutableList<String> = mutableListOf("201", "202", "203")
     var priceList:MutableList<Int> = mutableListOf (75, 65, 55)
     var imageList:MutableList<Int> = mutableListOf(R.drawable.image_1, R.drawable.image_2, R.drawable.image_3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        displayRooms()

    }

     private fun displayRooms(){
        var adapter = MyListRecyclerAdapter( imageList, roomList, priceList)//list all data sources used by recycler view
         main_listview.adapter = adapter
        val layoutMgr = LinearLayoutManager(this)
        main_listview.layoutManager = layoutMgr

    }
}

