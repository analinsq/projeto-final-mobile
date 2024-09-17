package com.example.mobile.ui

import PostAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile.OlaUserPreferences
import com.example.mobile.Post
import com.example.mobile.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firestore: FirebaseFirestore
    private lateinit var postAdapter: PostAdapter
    private lateinit var auth: FirebaseAuth
    private val posts = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firestore = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()

        binding.btAdd.setOnClickListener{
            startActivity(Intent(this, PostActivity::class.java))
        }

        binding.btPerfiluser.setOnClickListener{
            startActivity(Intent(this, PerfilActivity::class.java))
        }

        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, "Bem-vindo(a), ${currentUser.email}", Toast.LENGTH_SHORT).show()
        }

        postAdapter = PostAdapter(posts)
        binding.recyclerView.adapter = postAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Recupera os posts do Firestore
        firestore.collection("adocoes")
            .addSnapshotListener { snapshots, e ->
                if (e != null) {
                    Toast.makeText(this@MainActivity, "Erro ao carregar posts: ${e.message}", Toast.LENGTH_SHORT).show()
                    return@addSnapshotListener
                }

                posts.clear()
                for (doc in snapshots!!) {
                    val post = doc.toObject<Post>()
                    posts.add(post)
                }
                postAdapter.notifyDataSetChanged()
            }

        handleUserName()
    }

    private fun handleUserName() {
        val name = OlaUserPreferences(this).getUserName("USER_NAME")
        binding.tvOlauser.text = "Olá, $name!"
    }
}

