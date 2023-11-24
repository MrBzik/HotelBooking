package com.example.hotelbooking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hotelbooking.ui.fragments.BookingFragment
import com.example.hotelbooking.ui.fragments.HotelFragment
import dagger.hilt.android.AndroidEntryPoint
import io.ktor.client.HttpClient
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    @Inject
    lateinit var client : HttpClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.flFragment, BookingFragment())
//            addToBackStack(null)
//            commit()
//        }


    }
}

