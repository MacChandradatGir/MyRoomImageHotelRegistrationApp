package com.example.myroomimagehotelregistrationapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myroomimagehotelregistrationapp.R
import com.example.myroomimagehotelregistrationapp.model.Guest

class MyRecyclerAdapter(private val guestList: List<Guest>) :
    RecyclerView.Adapter<MyRecyclerAdapter.MyCustomViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.room_item_layout, parent, false)
        return MyCustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return guestList.size
    }

    override fun onBindViewHolder(holder: MyCustomViewHolder, position: Int) {
        holder.apply {
            roomNumber_textview.text = guestList[position].room_number
            guestName_textview.text = guestList[position].name
            price_textview.text = guestList[position].price.toString()

        }

    }

    inner class MyCustomViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val roomNumber_textview = itemView.findViewById<TextView>(R.id.room_number_textview)
        val guestName_textview = itemView.findViewById<TextView>(R.id.guest_name_textview)
        val price_textview = itemView.findViewById<TextView>(R.id.price_textview)

    }


}