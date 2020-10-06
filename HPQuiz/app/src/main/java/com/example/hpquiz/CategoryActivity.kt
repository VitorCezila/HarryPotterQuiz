package com.example.hpquiz

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_category.*


class CategoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Categories")
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == android.R.id.home) {
            val intent3 = Intent(this, MainActivity::class.java)
            startActivity(intent3)
            this.finish()
        }

        return super.onOptionsItemSelected(item)
    }

}