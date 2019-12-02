package com.example.myroomimagehotelregistrationapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.myroomimagehotelregistrationapp.R
import com.example.myroomimagehotelregistrationapp.view.SecondActivity

class MyListRecyclerAdapter(private var imageList: List<Int>, private var roomList: List<String>,
                            private var priceList: List<Int>):
    RecyclerView.Adapter<MyListRecyclerAdapter.MyCustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            MyCustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.image_item_layout, parent, false)
        return MyCustomViewHolder(view)
    }

    override fun getItemCount(): Int {
       return imageList.size
    }

    override fun onBindViewHolder(holder: MyCustomViewHolder, position: Int) {
        holder.apply {
            image.setImageResource(imageList[position])
        }

       holder.itemView.setOnClickListener {view ->

           var pos: Int = holder.adapterPosition
           var roomNumber = roomList[pos]
           var roomPrice = priceList[pos].toString()

           var intent: Intent = Intent(view.context, SecondActivity::class.java)

           intent.putExtra("IMAGE", imageList[pos].toString())
           intent.putExtra("ROOM_NUMBER", roomNumber)
           intent.putExtra("ROOM_PRICE", roomPrice).toString()

           view.context.startActivity(intent)

           Toast.makeText(view.context,holder.adapterPosition.toString(),Toast.LENGTH_SHORT).show()
       }

    }

    inner class MyCustomViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        var image  = itemView.findViewById<ImageView>(R.id.image_imageview)

    }
}