package com.pdrarth.arthmovies.View

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.pdrarth.arthmovies.R
import com.pdrarth.arthmovies.databinding.ActivityDetalhesdofilmeBinding

private lateinit var binding: ActivityDetalhesdofilmeBinding
class Detalhesdofilme : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetalhesdofilmeBinding.inflate(layoutInflater)
        setContentView(binding.root)
       val capa = intent.extras?.getString("capa")
       val nome = intent.extras?.getString("nome")
       val descricao = intent.extras?.getString("descricao")
       val elenco = intent.extras?.getString("elenco")


        Glide.with(this).load(capa).centerCrop().into(binding.movieImage)
        binding.movieTitle.setText(nome)
        binding.movieDescription.setText("Descricao: $descricao")
        binding.elenco.setText("Elenco: $elenco")
    }
}