package com.pdrarth.arthmovies.View

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import retrofit2.converter.gson.GsonConverterFactory;
import com.pdrarth.arthmovies.API
import com.pdrarth.arthmovies.Adpter.AdpterCategoria
import com.pdrarth.arthmovies.Model.Categoria
import com.pdrarth.arthmovies.Model.Categorias
import com.pdrarth.arthmovies.Model.Filme
import com.pdrarth.arthmovies.databinding.ActivityMoviesBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit


private lateinit var binding: ActivityMoviesBinding
private lateinit var adapterCategoria: AdpterCategoria
private val listaCategoria: MutableList<Categoria> = mutableListOf()

class Movies : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)


        val recyclerview = binding.recyclerView
        recyclerview.layoutManager = LinearLayoutManager(this)
        adapterCategoria = AdpterCategoria(this, listaCategoria)
        recyclerview.setHasFixedSize(true)
        recyclerview.adapter = adapterCategoria

        binding.menuSair.setOnClickListener {
            Toast.makeText(this, "Usuario Deslogado", Toast.LENGTH_SHORT).show()
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, Tela_Login::class.java)
            startActivity(intent)
            finish()

        }
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://stackmobile.com.br/")
            .build()
            .create(API::class.java)
        retrofit.listadeCategorias().enqueue(object : Callback<Categorias>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Categorias>, response: Response<Categorias>) {
                if(response.code() == 200){
                    response.body()?.let {
                        adapterCategoria.listaCategorias.addAll(it.categorias)
                        adapterCategoria.notifyDataSetChanged()
                    }
                }
            }
            override fun onFailure(call: Call<Categorias>, t: Throwable) {
                Toast.makeText(applicationContext,"Error ao buscar todos os filmes",Toast.LENGTH_SHORT).show()

            }


        })
    }





}