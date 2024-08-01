package com.example.mobile.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile.OlaUserPreferences
import com.example.mobile.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btAdd.setOnClickListener {
//            startActivity((Intent(this, PostActivity::class.java)))
//        }

//        binding.btPerfiluser.setOnClickListener {
//            startActivity(Intent(this, PerfilActivity::class.java))
//        }

        handleUserName()

    }

    private fun handleUserName() {
        val name = OlaUserPreferences(this).getUserName("USER_NAME")
        binding.tvOlauser.text = "Olá, $name!"
    }
}
