package com.example.mobile.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile.R

class SplashScreamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_scream)

        Handler(Looper.getMainLooper()).postDelayed({
            finish()
            startActivity(Intent(this, LoginActivity::class.java))
        }, 1000)
    }
}