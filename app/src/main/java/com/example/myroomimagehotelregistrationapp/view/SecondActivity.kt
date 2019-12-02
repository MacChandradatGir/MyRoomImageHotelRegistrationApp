package com.example.myroomimagehotelregistrationapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myroomimagehotelregistrationapp.R
import com.example.myroomimagehotelregistrationapp.adapter.MyListRecyclerAdapter
import com.example.myroomimagehotelregistrationapp.adapter.MyRecyclerAdapter
import com.example.myroomimagehotelregistrationapp.database.MyDatabaseHelper
import com.example.myroomimagehotelregistrationapp.model.Guest
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity() : AppCompatActivity() {

    private lateinit var myDatabaseHelper: MyDatabaseHelper
    private var guestList = mutableListOf<Guest>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        myDatabaseHelper = MyDatabaseHelper(this)

        check_in_button.setOnClickListener(){
            addGuestToDatabase()
        }
        displaySelectedRoom()

    }

    private fun addGuestToDatabase() {
        val roomNumber = getIntent().getStringExtra("ROOM_NUMBER").toString()
        val price = getIntent().getStringExtra ("ROOM_PRICE").toString()
        val guestName = name_edittext.text.toString()
        val priceValue = price.toString()
        val newGuest = Guest(guestName, roomNumber, Integer.parseInt(priceValue))
        myDatabaseHelper.insertGuest(newGuest)

        Toast.makeText(this, "Guest has been added on Database", Toast.LENGTH_SHORT).show()
        clearFields()
        readFromDatabase()
    }

    private fun readFromDatabase() {
        //clear guestList
        guestList = mutableListOf()

        val cursor = myDatabaseHelper.readAllGuests()
        cursor.moveToFirst()

        if(cursor.count >0){
            val guest = Guest(
                cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_ROOMNUMBER)),
                        cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_PRICE))
            )
            guestList.add(guest)

        }

        while (cursor.moveToNext()){
            val guestName = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_NAME))
            val roomNumber = cursor.getString(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_ROOMNUMBER))
            val roomPrice = cursor.getInt(cursor.getColumnIndex(MyDatabaseHelper.COLUMN_PRICE))
            val readGuest = Guest(guestName, roomNumber, roomPrice)
            guestList.add(readGuest)

        }

        displayGuests()

    }

    private fun displayGuests() {
        val myRecyclerAdapter = MyRecyclerAdapter(guestList)
        second_recycler_view.adapter = myRecyclerAdapter
        val layoutMgr = LinearLayoutManager(this)
        second_recycler_view.layoutManager = layoutMgr
    }

    private fun clearFields() {
        name_edittext.text.clear()
    }

    private fun displaySelectedRoom() {

        if( getIntent().hasExtra("ROOM_NUMBER") &&
            getIntent().hasExtra("ROOM_PRICE")){
            Log.d("TAG_C", "You have incoming Extras: Get Intent")
        }

        val image = getIntent().getStringExtra("IMAGE")
        val roomNumber = getIntent().getStringExtra("ROOM_NUMBER")
        val price = getIntent().getStringExtra ("ROOM_PRICE")

         room_imageview.setImageResource(image.toInt())
         roomnumber_textview.text = roomNumber
         price_textview.text = price

    }

}
