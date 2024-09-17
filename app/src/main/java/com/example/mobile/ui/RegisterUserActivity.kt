package com.example.mobile.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile.OlaUserPreferences
import com.example.mobile.databinding.ActivityRegisterUserBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class RegisterUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterUserBinding
    private var auth = FirebaseAuth.getInstance()
    private lateinit var olaUserPreferences: OlaUserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btRegister.setOnClickListener { view ->
            val username = binding.etRegisterName.text.toString()
            val email = binding.etRegisterEmail.text.toString()
            val password = binding.etRegisterPassword.text.toString()
            registerUser(username, email, password)
        }

    }

    private fun registerUser(username: String, email: String, password: String) {
        if (validateData(username, email, password)) {
            auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val userId = auth.currentUser?.uid
                    if (userId != null) {
                        // Salvando o nome do usuário no Firebase Database
                        val database = FirebaseDatabase.getInstance().getReference("users")
                        val userMap = mapOf(
                            "name" to username,  // Salva o nome fornecido
                            "email" to email
                        )
                        database.child(userId).setValue(userMap).addOnCompleteListener { saveTask ->
                            if (saveTask.isSuccessful) {
                                // Salvando localmente
                                OlaUserPreferences(this).saveUserName(username)
                                Toast.makeText(this, "Conta cadastrada com sucesso", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this, LoginActivity::class.java))
                                finish()
                            } else {
                                Toast.makeText(this, "Erro ao salvar o usuário", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
                }
            }
        }
    }


//    private fun registerUser(username: String, email: String, password: String) {
//        if (validateData(username, email, password)) {
//            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password).addOnCompleteListener(){
//                if (it.isSuccessful) {
//                    startActivity(Intent(this, LoginActivity::class.java))
//                    OlaUserPreferences(this).saveUserName(username)
//                    Toast.makeText(this, "Conta cadastrada com sucesso", Toast.LENGTH_SHORT).show()
//                } else {
//                    Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
//
//                }
//            }
//        }
//    }


    private fun validateData(username:String, email: String, password: String): Boolean{
        return if (username.isNotEmpty()){
            if (email.isNotEmpty()){
                if(password.isNotEmpty()){
                    true
                } else {
                    Toast.makeText(this,"Preencha a senha", Toast.LENGTH_SHORT).show()
                    false
                }
            } else {
                Toast.makeText(this, "Preencha o email", Toast.LENGTH_SHORT).show()
                false
            }
        } else {
            Toast.makeText(this, "Preencha o nome", Toast.LENGTH_SHORT).show()
            false
        }
    }

}