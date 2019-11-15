package com.parfoismeng.navigation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvViewPager.setOnClickListener {
            startActivity(Intent(this@MainActivity, VPActivity::class.java))
        }
        tvFragment.setOnClickListener {
            startActivity(Intent(this@MainActivity, FGActivity::class.java))
        }
    }
}
