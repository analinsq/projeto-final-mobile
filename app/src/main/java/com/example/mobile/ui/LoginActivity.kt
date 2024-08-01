package com.example.mobile.ui
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btLogin.setOnClickListener { view ->
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                val snackbar = Snackbar.make(
                    view, "Preencha todos os campos!",
                    Snackbar.LENGTH_SHORT
                )
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            } else {
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener {autenticacao ->
                    if(autenticacao.isSuccessful){
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                }
            }
        }



//                .addOnCompleteListener { autentication ->
//                    if (autentication.isSuccessful) {
//                        startActivity(Intent(this, MainActivity::class.java))
//                    }
//                }.addOnFailureListener { exception ->
//                    val Error = when (exception) {
//                        is FirebaseAuthWeakPasswordException -> "Digite uma senha com no mínimo 6 caracteres!"
//                        is FirebaseAuthInvalidCredentialsException -> "Digite um email válido!"
//                        is FirebaseAuthInvalidUserException -> "Email não cadastrado!"
//                        is FirebaseNetworkException -> "Sem conexão com a rede"
//                        else -> "Ops!, Algo deu errado!"
//                    }
//                    val snackbar = Snackbar.make(view, Error, Snackbar.LENGTH_SHORT)
//                    snackbar.setBackgroundTint(Color.RED)
//                    snackbar.show()
//                }
//            }
//        }

        binding.tvRegister.setOnClickListener{
            startActivity(Intent(this, RegisterUserActivity::class.java))
        }
        binding.tvForgotPassword.setOnClickListener {

        }
    }

    override fun onStart() {
        super.onStart()

        val atualUser = FirebaseAuth.getInstance().currentUser
        if(atualUser != null){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}

