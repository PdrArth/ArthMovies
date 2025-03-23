package com.pdrarth.arthmovies

import com.pdrarth.arthmovies.Model.Categorias
import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET("/filmes")
    fun listadeCategorias(): Call<Categorias>
}