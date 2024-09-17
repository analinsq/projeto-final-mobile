package com.example.mobile.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile.OlaUserPreferences
import com.example.mobile.Post
import com.example.mobile.databinding.ActivityPostBinding
import com.google.firebase.firestore.FirebaseFirestore

class PostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()

        binding.btPerfiluser.setOnClickListener{
            startActivity(Intent(this, PerfilActivity::class.java))
        }

        binding.arrowback.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding.btPubli.setOnClickListener {
            val nomeAnimal = binding.etNomeanimal.text.toString()
            val raca = binding.etRacaanimal.text.toString()
            val cor = binding.etCoranimal.text.toString()
            val descricao = binding.etDescricao.text.toString()

            if (nomeAnimal.isNotEmpty() && raca.isNotEmpty() && cor.isNotEmpty() && descricao.isNotEmpty()) {
                val post = Post(nomeAnimal, raca, cor, descricao)

                firestore.collection("adocoes").add(post)
                    .addOnSuccessListener {
                        Toast.makeText(this, "Post salvo com sucesso!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }
                    .addOnFailureListener {
                        Toast.makeText(this, "Erro ao salvar o post", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        handleUserName()
    }

    private fun handleUserName() {
        val name = OlaUserPreferences(this).getUserName("USER_NAME")
        binding.tvOlauser.text = "Olá, $name!"
    }
}
