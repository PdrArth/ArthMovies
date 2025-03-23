package com.pdrarth.arthmovies.View

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.pdrarth.arthmovies.databinding.ActivityTelaLoginBinding

class Tela_Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityTelaLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.statusBarColor = Color.parseColor("#FF000000")
        binding.digitaremail.requestFocus()


        binding.botaodeentrar.setOnClickListener { v ->

            val senha = binding.digitarsenha.text.toString()
            val email = binding.digitaremail.text.toString()

            when {
                email.isEmpty() -> {
                    binding.containeremail.helperText = "Preencha o email"
                    binding.containeremail.boxStrokeColor = Color.parseColor("#FF9800")
                }
                senha.isEmpty() -> {
                    binding.containersenha.helperText = "Preencha a senha"
                    binding.containersenha.boxStrokeColor = Color.parseColor("#FF9800")
                }
                else -> {
                    Logar(v ,email , senha )
                }
            }
        }
        binding.botaocadastro.setOnClickListener {

            val intent = Intent(this, Tela_Cadastro::class.java)
            startActivity(intent)
            finish()



        }
    }
    private fun Logar(v :View, email: String, senha :String){

        FirebaseAuth.getInstance().signInWithEmailAndPassword(email,senha).addOnCompleteListener { login ->
            if(login.isSuccessful){
                val snackbar = Snackbar.make(v, "Login Realizado com sucesso", Snackbar.LENGTH_INDEFINITE)
                    .setAction("ok") { finish() }
                snackbar.show()
                NavegarTelaMovies()
            }
        }.addOnFailureListener { exception ->
            Log.e("LoginError", "Erro ao tentar logar: ${exception.message}")

            val snackbar = Snackbar.make(v, "Error ao logar usuario", Snackbar.LENGTH_INDEFINITE)
                .setAction("ok") { finish() }
            snackbar.show()
        }
    }
    private fun NavegarTelaMovies(){
        val intent = Intent(this, Movies::class.java)
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()

        val usuarioAtual = FirebaseAuth.getInstance().currentUser
        if(usuarioAtual !=null){
            NavegarTelaMovies()
        }
        else {
            FirebaseAuth.getInstance().signOut()  // Garante que o usuário seja desconectado, caso tenha sido excluído

        }
    }
}