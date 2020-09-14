package com.gamiphy.demo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.gamiphy.loyaltyStation.LoyaltyStation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.open).setOnClickListener {
            LoyaltyStation.getInstance().open(this)
        }
    }
}
