package com.example.mobile.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile.Post
import com.example.mobile.databinding.ActivityPostBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class PostActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPostBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("posts")

        binding.btPerfiluser.setOnClickListener{
            startActivity(Intent(this, PerfilActivity::class.java))
        }

        binding.btPubli.setOnClickListener{ view ->
            val etNomeAnimal = binding.etNomeanimal.text.toString()
            val etRaca = binding.etRacaanimal.text.toString()
            val etCor = binding.etCoranimal.text.toString()
            val etDescricao = binding.etDescricao.text.toString()

            if (etNomeAnimal.isNotEmpty() && etRaca.isNotEmpty() && etCor.isNotEmpty() && etDescricao.isNotEmpty()) {
                val postId = database.push().key
                val post = Post(etNomeAnimal, etRaca, etCor, etDescricao)

                if (postId != null) {
                    database.child(postId).setValue(post).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "Post salvo com sucesso!", Toast.LENGTH_SHORT).show()
                            finish() // Fechar a activity após o post
                        } else {
                            Toast.makeText(this, "Erro ao salvar o post", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}