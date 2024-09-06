package com.example.mobile.ui

import PostAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile.OlaUserPreferences
import com.example.mobile.Post
import com.example.mobile.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    private lateinit var postAdapter: PostAdapter
    private lateinit var auth: FirebaseAuth
    private val posts = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("posts")
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

        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                posts.clear()
                for (postSnapshot in snapshot.children) {
                    val post = postSnapshot.getValue(Post::class.java)
                    if (post != null) {
                        posts.add(post)
                    }
                }
                postAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@MainActivity,
                    "Erro ao carregar posts: ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }

        })

        handleUserName()
    }

    private fun handleUserName() {
        val name = OlaUserPreferences(this).getUserName("USER_NAME")
        binding.tvOlauser.text = "Olá, $name!"
    }
}
