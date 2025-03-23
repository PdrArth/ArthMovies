package com.pdrarth.arthmovies.View

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException
import com.pdrarth.arthmovies.databinding.ActivityTelaCadastroBinding


class Tela_Cadastro : AppCompatActivity() {
    private lateinit var binding: ActivityTelaCadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaCadastroBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)



        //VALIDACAO PARA EMAIL CRIAR SUA SENHA
        binding.botaoVamosLa.setOnClickListener {
            val email = binding.emailInputEdittext.text.toString()
            if(!email.isEmpty()){
                binding.secondScreen.visibility = View.VISIBLE
                binding.botaoVamosLa.visibility = View.GONE
                binding.textoInformativo.setText("Um mundo de series e filmes \n Ilimitados espera por voce")
                binding.textoemailinfomativo.setText("Crie uma conta para saber mais sobre \n O nosso APP de Filmes")
                binding.emailInputLayout.boxStrokeColor = Color.parseColor("#2196F3")
                binding.emailInputLayout.helperText = ""
                binding.textoHorizontal.visibility = View.VISIBLE
            }
            else{
                binding.emailInputLayout.boxStrokeColor = Color.parseColor("#F44336")
                binding.emailInputLayout.helperText = "O email é obrigatorio"
            }
        }

        //Com com email adicionado liberar o botao de senha
        binding.botaoContinuar.setOnClickListener { v ->
            val email = binding.emailInputEdittext.text.toString()
            val senha = binding.senhaInputEdittext.text.toString()

            if(!email.isEmpty() && !senha.isEmpty()){
                //chamando o firebase
                if (senha.length < 6){
                    Snackbar.make(v, "A senha deve ter pelo menos 6 caracteres", Snackbar.LENGTH_LONG).show()
                }
                else{
                    Cadastro(v,binding,email,senha)
                }
            }
            else if (senha.isEmpty()){
                binding.senhaInputLayout.boxStrokeColor = Color.parseColor("#F44336")
                binding.senhaInputLayout.helperText = "A senha eh obrigatoria"
                binding.emailInputLayout.boxStrokeColor = Color.parseColor("#2196F3")
            }
            else if (email.isEmpty()){
                binding.emailInputLayout.boxStrokeColor = Color.parseColor("#F44336")
                binding.emailInputLayout.helperText = "O email é obrigatorio"
            }

        }

        //Voltar para a tela de login
        binding.textoEntrar.setOnClickListener {

            val intent = Intent(this, Movies::class.java)
            startActivity(intent)
            finish()

        }

    }
    private fun Cadastro(v: View, binding: ActivityTelaCadastroBinding, email: String, senha: String) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { cadastro ->
                if (cadastro.isSuccessful) {
                    val snackbar = Snackbar.make(v, "Cadastro Realizado com Sucesso", Snackbar.LENGTH_INDEFINITE)
                        .setAction("ok") { finish() }
                    snackbar.show()
                    // Atualizando os campos visualmente
                    binding.emailInputLayout.helperText = ""
                    binding.senhaInputLayout.helperText = ""
                    binding.emailInputLayout.boxStrokeColor = Color.parseColor("#2196F3")
                    binding.senhaInputLayout.boxStrokeColor = Color.parseColor("#2196F3")
                    val intent =Intent(this, Tela_Login::class.java)
                    startActivity(intent)
                    finish()
                }
            }
            .addOnFailureListener { error ->
                when (error) {
                    is FirebaseAuthWeakPasswordException -> {
                        binding.senhaInputLayout.helperText = "Senha com menos de 6 caracteres"
                        binding.senhaInputLayout.boxStrokeColor = Color.parseColor("#F44336")
                    }
                    is FirebaseAuthInvalidCredentialsException -> {
                        binding.emailInputLayout.helperText = "Email Inválido"
                        binding.emailInputLayout.boxStrokeColor = Color.parseColor("#F44336")
                    }
                    is FirebaseAuthUserCollisionException -> {
                        binding.emailInputLayout.helperText = "Essa conta já foi criada"
                        binding.emailInputLayout.boxStrokeColor = Color.parseColor("#F44336")
                    }
                    is FirebaseNetworkException -> {
                        Toast.makeText(v.context, "Sem conexão com a Internet", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(v.context, "Erro ao Cadastrar", Toast.LENGTH_SHORT).show()
                    }
                }
            }
    }


}