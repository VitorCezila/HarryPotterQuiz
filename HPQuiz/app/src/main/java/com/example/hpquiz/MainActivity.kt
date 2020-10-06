package com.example.hpquiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ma_startB.setOnClickListener{
            val intent2 = Intent(this, CategoryActivity::class.java)
            startActivity(intent2)
            finish()
        }
    }
}